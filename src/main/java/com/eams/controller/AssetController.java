package com.eams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.eams.dtos.AssetDTO;
import com.eams.entity.Asset;
import com.eams.service.AssetService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/assets")

@Validated
public class AssetController {

    @Autowired
    private AssetService assetService;
//This method is to creating new asset
    @PostMapping
    public boolean create(@Valid @RequestBody AssetDTO dto) {
    	log.info("New asset created sucessfully");
        return assetService.createAsset(dto);
    }
//This method is to get details of assets
    @GetMapping
    public List<Asset> getAll() {
    	log.info("get all asset details");
        return assetService.getAllAssets();
    }
//This method is to get details by ID
    @GetMapping("/{id}")
    public Asset getById(@PathVariable Long id) {
    	log.info("get asset details by ID");
        return assetService.getAssetById(id);
    }

//This method is to edit the details
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @RequestBody AssetDTO dto) {
    	log.info("edit the values for asset");
        return assetService.updateAsset(id, dto);
    }

//This method is to delete to the asset by ID
    @DeleteMapping("/{id}/user/{userid}")
    public String delete(@PathVariable Long id,@PathVariable Long userid) {
    	log.info("delete the asset by using ID");
        return assetService.deleteAsset(id,userid);
    }
}
