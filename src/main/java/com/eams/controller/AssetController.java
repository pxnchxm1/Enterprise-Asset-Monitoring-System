package com.eams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/assets")

@Validated
public class AssetController {

    @Autowired
    private AssetService assetService;
//This method is to creating new asset
    @PostMapping("/creatingPerson/{person}")
    public boolean create(@Valid @RequestBody AssetDTO dto,@PathVariable String person) {
        return assetService.createAsset(dto, person);
    }
//This method is to get details of assets
    @GetMapping
    public List<Asset> getAll() {
        return assetService.getAllAssets();
    }
//This method is to get details by ID
    @GetMapping("/{id}")
    public Asset getById(@PathVariable Long id) {
        return assetService.getAssetById(id);
    }
//This method is is edit the details
    @PutMapping("/{id}/manager/{managerMail}")
    public String update(@PathVariable Long id, @Valid @RequestBody Asset asset,@PathVariable String managerMail) {
        return assetService.updateAsset(id, asset,managerMail);
    }
    @PostMapping("/resolve/{id}/manager/{managerMail}")
    public String resolveAlertAsAdmin(@PathVariable Long id,@PathVariable String managerMail) {
        return assetService.resolveAlertAsManager(id, managerMail);
    }
//This method is to delete to the asset by ID
    @DeleteMapping("/{id}/user/{userid}")
    public String delete(@PathVariable Long id,@PathVariable Long userid) {
        return assetService.deleteAsset(id,userid);
    }
}