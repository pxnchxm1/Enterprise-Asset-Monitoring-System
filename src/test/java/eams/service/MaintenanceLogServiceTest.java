package eams.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.eams.entity.MaintenanceLog;
import com.eams.exception.AssetNotFoundException;
import com.eams.repository.AssetRepository;
import com.eams.repository.MaintenanceLogRepository;
import com.eams.service.MaintenanceLogService;
import com.eams.entity.Asset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MaintenanceLogServiceTest {

    @InjectMocks
    private MaintenanceLogService service;

    @Mock
    private MaintenanceLogRepository repo;

    @Mock
    private AssetRepository assetRepo;

    private Asset dummyAsset;
    private MaintenanceLog log;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dummyAsset = new Asset();
        dummyAsset.setAsset_id(1L);

        log = new MaintenanceLog();
        log.setAsset_id(1L);
        log.setScheduledDate(LocalDate.now().plusDays(5));
        log.setCompletedDate(LocalDate.now().plusDays(10));
        log.setRemarks("Test remark");
    }

    @Test
    void testSchedule_ValidAsset_ShouldReturnSavedLog() {
        when(assetRepo.findById(1L)).thenReturn(Optional.of(dummyAsset));
        when(repo.save(any(MaintenanceLog.class))).thenReturn(log);

        MaintenanceLog result = service.schedule(1L, log);

        assertNotNull(result);
        assertEquals(log.getScheduledDate(), result.getScheduledDate());
        verify(repo, times(1)).save(any(MaintenanceLog.class));
    }

    @Test
    void testSchedule_InvalidAsset_ShouldThrowException() {
        when(assetRepo.findById(2L)).thenReturn(Optional.empty());

        assertThrows(AssetNotFoundException.class, () -> {
            service.schedule(2L, log);
        });
    }

    @Test
    void testGetByAssetId_ValidAsset_ShouldReturnLogs() {
        when(assetRepo.findById(1L)).thenReturn(Optional.of(dummyAsset));
        when(repo.findAll()).thenReturn(Arrays.asList(log));

        List<MaintenanceLog> result = service.getByAssetId(1L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getAsset_id());
    }

    @Test
    void testGetByAssetId_InvalidAsset_ShouldThrowException() {
        when(assetRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(AssetNotFoundException.class, () -> {
            service.getByAssetId(99L);
        });
    }

    @Test
    void testGetAllAssetId_ShouldReturnAllLogs() {
        when(repo.findAll()).thenReturn(Arrays.asList(log));

        List<MaintenanceLog> result = service.getAllAssetId();

        assertEquals(1, result.size());
        assertEquals(log, result.get(0));
    }
}
