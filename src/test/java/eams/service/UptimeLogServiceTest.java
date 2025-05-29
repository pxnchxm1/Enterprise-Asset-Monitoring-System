package eams.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eams.entity.Asset;
import com.eams.entity.UpTimeLogStatus;
import com.eams.entity.UptimeLog;
import com.eams.exception.AssetNotFoundException;
import com.eams.repository.AssetRepository;
import com.eams.repository.UptimeLogRepository;
import com.eams.service.UptimeLogService;

class UptimeLogServiceTest {
	
	@InjectMocks
	private UptimeLogService service;
	
    @Mock
    private AssetRepository assetRepo;

    @Mock
    private UptimeLogRepository uptimeRepo;

    private Asset asset;
    private UptimeLog log;
    
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        
        asset = Asset.builder()
        		.asset_id(1L)
        		.build();
        
        log = new UptimeLog();
        log.setEndTime(LocalDateTime.now().plusHours(1));
        log.setUptimeLogStatus(UpTimeLogStatus.UP);
    }
    
	@Test
	void testCreateLog_whenAssetExists() {
		when(assetRepo.findById(1L)).thenReturn(Optional.of(asset));
		when(uptimeRepo.save(any(UptimeLog.class))).thenAnswer(inv -> inv.getArgument(0));

        UptimeLog result = service.createlog(1L, log);

        assertNotNull(result);
        assertEquals(asset, result.getAsset());
        assertEquals(UpTimeLogStatus.UP, result.getUptimeLogStatus());
        verify(uptimeRepo, times(1)).save(any(UptimeLog.class));
	}
	@Test
    void testCreateLog_whenAssetNotFound() {
        when(assetRepo.findById(5L)).thenReturn(Optional.empty());

        AssetNotFoundException ex = assertThrows(AssetNotFoundException.class, () ->
                service.createlog(5L, log));

        assertEquals("Asset with ID 5 not found", ex.getMessage());
    }

    @Test
    void testGetLogs_whenAssetExists() {
        UptimeLog log1 = new UptimeLog();
        log1.setAsset(asset);
        log1.setStartTime(LocalDateTime.now());
        log1.setEndTime(LocalDateTime.now().plusHours(1));
        log1.setUptimeLogStatus(UpTimeLogStatus.UP);

        List<UptimeLog> allLogs = List.of(log1);

        when(assetRepo.findById(1L)).thenReturn(Optional.of(asset));
        when(uptimeRepo.findAll()).thenReturn(allLogs);

        List<UptimeLog> result = service.getLogs(1L);
        assertEquals(1, result.size());
    }

    @Test
    void testGetLogs_whenAssetNotFound() {
        when(assetRepo.findById(10L)).thenReturn(Optional.empty());

        assertThrows(AssetNotFoundException.class, () -> service.getLogs(10L));
    }

    @Test
    void testGetAllLogs_shouldReturnAllLogs() {
        List<UptimeLog> logs = List.of(new UptimeLog(), new UptimeLog());
        when(uptimeRepo.findAll()).thenReturn(logs);

        List<UptimeLog> result = service.getAllLogs();
        assertEquals(2, result.size());
    }

}
