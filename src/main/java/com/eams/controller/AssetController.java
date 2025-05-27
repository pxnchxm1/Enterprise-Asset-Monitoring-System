package com.eams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eams.dtos.AssetDTO;
import com.eams.entity.Asset;
import com.eams.service.AssetService;

@RestController
@RequestMapping("/api/assets")

public class AssetController {
	
	@Autowired
	private AssetService assetService;
	
	@PostMapping
	public Asset create(@RequestBody AssetDTO dto) {
		return assetService.createAsset(dto);
	
	}
	
	@GetMapping
	public List<Asset> getAll(){
		return assetService.getAllAssets();	
		
	}
	
	@GetMapping("/{id}")
	public Asset getById(@PathVariable Long id) {
		return assetService.getAssetById(id);
	}
	//TODO: assets can only be edited by manager..add a condition checking the same in service layer.
	@PutMapping("/{id}")
	public Asset update(@PathVariable Long id,@RequestBody AssetDTO dto) {
		return assetService.updateAsset(id,dto);
	}
	//TODO :Only manager can delete asset
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		assetService.deleteAsset(id);
	}
	
	

}
