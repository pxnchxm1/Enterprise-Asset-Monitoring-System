package com.eams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eams.dtos.AssetDTO;
import com.eams.entity.Asset;
import com.eams.service.AssetService;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping
    public boolean create(@RequestBody AssetDTO dto) {
        return assetService.createAsset(dto);
    }

    @GetMapping
    public List<Asset> getAll() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public Asset getById(@PathVariable Long id) {
        return assetService.getAssetById(id);
    }


    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody AssetDTO dto) {

        return assetService.updateAsset(id, dto);
    }


    @DeleteMapping("/{id}/user/{userid}")
    public String delete(@PathVariable Long id,@PathVariable Long userid) {
        return assetService.deleteAsset(id,userid);
    }
}
