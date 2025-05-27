package com.eams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.dtos.AssetDTO;
import com.eams.entity.Asset;
import com.eams.entity.User;
import com.eams.repository.AssetRepository;
import com.eams.repository.UserRepository;

@Service
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	public Asset createAsset(AssetDTO dto) {
		
		User user =userRepository.findById(dto.getAssignedTo()).orElseThrow();
		Asset asset = Asset.builder()
				.asset_name(dto.getAsset_name())
				.asset_type(dto.getAsset_type())
				.location(dto.getLocation())
				.thresholdTemp(dto.getThresholdTemp())
				.thresholdPressure(dto.getThresholdPressure())
				.assignedTo(user)
				.build();
		return assetRepository.save(asset);
	}
	
	public List<Asset> getAllAssets() {
		return assetRepository.findAll();
		
	}
	
	public Asset getAssetById(Long id) {
		return assetRepository.findById(id).orElseThrow();
	}
	
	public void deleteAsset(Long id) {
		assetRepository.deleteById(id);
	}
	
	public Asset updateAsset(Long id,AssetDTO dto) {
		Asset asset = assetRepository.findById(id).orElseThrow();
		User user = userRepository.findById(dto.getAssignedTo()).orElseThrow();
		
		asset.setAsset_name(dto.getAsset_name());
		asset.setAsset_type(dto.getAsset_type());
		asset.setLocation(dto.getLocation());
		asset.setThresholdTemp(dto.getThresholdTemp());
		asset.setThresholdPressure(dto.getThresholdPressure());
		asset.setAssignedTo(user);
		return assetRepository.save(asset);
		
	}
	
	public List <Asset> getAssetsByUser(Long userId) {
		
		return assetRepository.findByAssignedToId(userId);
	}
	

}
