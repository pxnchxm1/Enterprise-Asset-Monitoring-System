package com.eams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.dtos.AssetDTO;
import com.eams.entity.Asset;
import com.eams.entity.Role;
import com.eams.entity.User;
import com.eams.repository.AssetRepository;
import com.eams.repository.UserRepository;

@Service
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired 
	private UserRepository userRepository;

	public boolean createAsset(AssetDTO dto) {
	    try {
	       
	        User user = userRepository.findById(dto.getAssignedTo())
	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + dto.getAssignedTo()));

	    
	        if (user.getRole() != Role.MANAGER) {
	            throw new RuntimeException("User with ID " + dto.getAssignedTo() + " is not a MANAGER");
	        }


	        Asset asset = Asset.builder()
	                .asset_name(dto.getAsset_name())
	                .asset_type(dto.getAsset_type())
	                .location(dto.getLocation())
	                .thresholdTemp(dto.getThresholdTemp())
	                .thresholdPressure(dto.getThresholdPressure())
	                .assignedTo(user)
	                .build();

	        assetRepository.save(asset);
	        return true;

	    } catch (Exception e) {
	
	        System.out.println("Error while creating asset: " + e.getMessage());
	        return false;
	    }
	}

	// get all assets from db
	public List<Asset> getAllAssets() {
		return assetRepository.findAll();
		
	}
	
	// get asset by ID
	public Asset getAssetById(Long id) {
		return assetRepository.findById(id).orElseThrow();
	}
	
	// delete asset
	public String deleteAsset(Long id,Long userid) {
		
	    try {
	    	 User user = userRepository.findById(userid)
		                .orElseThrow();
		    if(user.getRole()==Role.MANAGER) {
	        Asset as = assetRepository.findById(id)
	                .orElseThrow();
	        
	        assetRepository.deleteById(id);
	        return "Asset deleted successfully.";
		    }
		    else {
		    	return "Only Manager can delete assests";
		    }
	    }  catch (Exception ex) {
	        return "Error deleting asset: " + ex.getMessage();
	    }
	}
	
	// update asset
	public String updateAsset(Long id, AssetDTO dto) {
	    try {
	        Asset asset = assetRepository.findById(id)
	                .orElseThrow();

	        User user = userRepository.findById(dto.getAssignedTo())
	                .orElseThrow();
	
	        asset.setAsset_name(dto.getAsset_name());
	        asset.setAsset_type(dto.getAsset_type());
	        asset.setLocation(dto.getLocation());
	        asset.setThresholdTemp(dto.getThresholdTemp());
	        asset.setThresholdPressure(dto.getThresholdPressure());
	        asset.setAssignedTo(user);

	        assetRepository.save(asset);
	        return "Asset updated successfully.";
	        
	    }  catch (Exception ex) {
	        return "Error updating asset: " + ex.getMessage();
	    }
	}
}
