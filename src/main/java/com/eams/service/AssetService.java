package com.eams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.dtos.AssetDTO;
import com.eams.entity.Asset;
import com.eams.entity.Role;
import com.eams.entity.User;
import com.eams.exception.AssetNotFoundException;
import com.eams.exception.InvalidUserRoleException;
import com.eams.exception.ManagerNotFoundException;
import com.eams.repository.AssetRepository;
import com.eams.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired 
	private UserRepository userRepository;

	public boolean createAsset(AssetDTO dto, String creatingPerson) {

	    User user = userRepository.findByEmail(creatingPerson)
	            .orElseThrow(() -> new InvalidUserRoleException("Manager with given mail is invalid. Doesnt exist!"));

	    User assigned = userRepository.findById(dto.getAssignedTo())
	            .orElseThrow(() -> new InvalidUserRoleException("User with ID " + dto.getAssignedTo() + " doesn't exist!"));


	    if (user.getRole() != Role.MANAGER) {
	        log.warn("User with mail {} is not a MANAGER", creatingPerson);
	        throw new InvalidUserRoleException("User with mail " + creatingPerson + " is not a MANAGER");
	    }

	    Asset asset = Asset.builder()
	            .asset_name(dto.getAsset_name())
	            .asset_type(dto.getAsset_type())
	            .location(dto.getLocation())
	            .thresholdTemp(dto.getThresholdTemp())
	            .thresholdPressure(dto.getThresholdPressure())
	            .assignedTo(assigned)
	            .build();

	    assetRepository.save(asset);


	    return true;
	}


	// get all assets from db
	public List<Asset> getAllAssets() {
		log.info("Fetching all assets from database");

		
		return assetRepository.findAll();
		
	}
	
	// get asset by ID
	public Asset getAssetById(Long id) {
		log.info("Fetching asset with ID {}", id);

		return assetRepository.findById(id).orElseThrow();
	}
	
	// delete asset
	public String deleteAsset(Long id,Long userid) {
		
	    try {
	    	 User user = userRepository.findById(userid)
		                .orElseThrow();
		    if(user.getRole()==Role.MANAGER) { 
	        assetRepository.deleteById(id);
	        log.info("Asset with ID {} deleted by user ID {}", id, userid);

	        return "Asset deleted successfully.";
		    }
		    else {
		    	log.warn("User ID {} attempted to delete asset without MANAGER role", userid);

		    	return "Only Manager can delete assests";
		    }
	    }  catch (Exception ex) {
	    	log.error("Error deleting asset: {}", ex.getMessage());

	        return "Error deleting asset: " + ex.getMessage();
	    }
	}
	
	// update asset
	public String updateAsset(Long assetId, Asset updatedAsset, String managerEmail) {
	    User manager = userRepository.findByEmail(managerEmail)
	                      .orElseThrow(() -> new ManagerNotFoundException("Manager not found"));
	    if (!Role.MANAGER.equals(manager.getRole())) {
	        throw new SecurityException("Only MANAGER can update assets.");
	    }

	    Asset existingAsset = assetRepository.findById(assetId)
	                              .orElseThrow(() -> new RuntimeException("Asset not found"));
	    
	    existingAsset.setAsset_name(updatedAsset.getAsset_name());
	    existingAsset.setAsset_type(updatedAsset.getAsset_type());
	    existingAsset.setLocation(updatedAsset.getLocation());
	    existingAsset.setThresholdTemp(updatedAsset.getThresholdTemp());
	    existingAsset.setThresholdPressure(updatedAsset.getThresholdPressure());
	    existingAsset.setAssignedTo(updatedAsset.getAssignedTo());

	    assetRepository.save(existingAsset);
	    return "Asset updated successfully.";
	}


}