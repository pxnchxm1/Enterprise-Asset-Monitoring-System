package eams.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.eams.dtos.AssetDTO;
import com.eams.entity.Asset;
import com.eams.entity.Role;
import com.eams.entity.User;
import com.eams.repository.AssetRepository;
import com.eams.repository.UserRepository;
import com.eams.service.AssetService;

public class AssetServiceTest {

    @Mock
    private AssetRepository assetRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AssetService assetService;

    private AssetDTO assetDTO;
    private User managerUser;
    private User assignedUser; 
    private Asset asset;
    private String managerMail;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        managerMail = "manager12@gmail.com";

        managerUser = new User();
        managerUser.setUser_id(1L);
        managerUser.setRole(Role.MANAGER);
        managerUser.setEmail(managerMail);

        assignedUser = new User(); 
        assignedUser.setUser_id(2L);
        assignedUser.setRole(Role.OPERATOR);
        assignedUser.setEmail("assigned@gmail.com");

        assetDTO = new AssetDTO();
        assetDTO.setAsset_name("Pump");
        assetDTO.setAsset_type("Hydraulic");
        assetDTO.setLocation("Plant A");
        assetDTO.setThresholdTemp(75.0);
        assetDTO.setThresholdPressure(30.0);
        assetDTO.setAssignedTo(assignedUser);

        asset = new Asset();
        asset.setAsset_name("Pump");
        asset.setAsset_type("Hydraulic");
        asset.setLocation("Plant A");
        asset.setThresholdTemp(75.0);
        asset.setThresholdPressure(30.0);
        asset.setAssignedTo(assignedUser);
    }

    @Test
    public void testCreateAsset_Success() {
    	when(userRepository.findByEmail(managerMail)).thenReturn(Optional.of(managerUser));
    	when(userRepository.findById(2L)).thenReturn(Optional.of(assignedUser));
    	when(assetRepository.save(any(Asset.class))).thenReturn(asset);


        boolean result = assetService.createAsset(assetDTO, managerMail);

        assertTrue(result);
        verify(assetRepository, times(1)).save(any(Asset.class));
    }

    @Test
    public void testGetAllAssets() {
        when(assetRepository.findAll()).thenReturn(List.of(asset));

        List<Asset> result = assetService.getAllAssets();

        assertEquals(1, result.size());
        assertEquals("Pump", result.get(0).getAsset_name());
    }

    @Test
    public void testGetAssetById() {
        when(assetRepository.findById(1L)).thenReturn(Optional.of(asset));

        Asset result = assetService.getAssetById(1L);

        assertNotNull(result);
        assertEquals("Pump", result.getAsset_name());
    }

    @Test
    public void testDeleteAsset_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(managerUser));
        when(assetRepository.findById(2L)).thenReturn(Optional.of(asset));

        String result = assetService.deleteAsset(2L, 1L);

        assertEquals("Asset deleted successfully.", result);
        verify(assetRepository, times(1)).deleteById(2L);
    }

    @Test
    public void testUpdateAsset_Success() {
        when(assetRepository.findById(2L)).thenReturn(Optional.of(asset));
        when(userRepository.findByEmail(managerMail)).thenReturn(Optional.of(managerUser));
        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        Asset updatedAsset = new Asset();
        updatedAsset.setAsset_name("Updated Pump");
        updatedAsset.setAsset_type("Hydraulic");
        updatedAsset.setLocation("Plant B");
        updatedAsset.setThresholdTemp(85.0);
        updatedAsset.setThresholdPressure(40.0);
        updatedAsset.setAssignedTo(assignedUser); // ✅ Now this won't throw error

        String result = assetService.updateAsset(2L, updatedAsset, managerMail);

        assertEquals("Asset updated successfully.", result);
        verify(assetRepository, times(1)).save(any(Asset.class));
    }
}
