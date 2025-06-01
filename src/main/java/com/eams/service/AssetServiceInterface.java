package com.eams.service;

import java.util.List;

import com.eams.dtos.AssetDTO;
import com.eams.entity.Asset;

public interface AssetServiceInterface {
	

	public boolean createAsset(AssetDTO dto, String reqPersonMail);
	
	public List<Asset> getAllAssets();
	
	
	public Asset getAssetById(Long id);
	
	
	public String deleteAsset(Long id,Long userid);
	

	public String updateAsset(Long assetId, Asset updatedAsset, String managerEmail);

}
