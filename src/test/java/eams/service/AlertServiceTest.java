package eams.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eams.entity.Alert;
import com.eams.entity.AlertStatus;
import com.eams.entity.AlertType;
import com.eams.entity.Asset;
import com.eams.repository.AlertRepository;
import com.eams.repository.AssetRepository;
import com.eams.service.AlertService;

public class AlertServiceTest {
	@Mock
	private AlertRepository alertrepo;
	@Mock
	private AssetRepository assetrepo;
	@InjectMocks
	private AlertService alertservice;
	private Asset asset;
	private Alert alert;
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		asset = new Asset();
        asset.setAsset_id(1L);

        alert = new Alert();
        alert.setId(1L);
        alert.setAsset_id(1L);
        alert.setType(AlertType.TEMP_HIGH);
        alert.setMessage("Overheat warning");
        alert.setStatus(AlertStatus.ACTIVE);
    }

    @Test
    public void testCreateAlert_Success() {
        when(assetrepo.findById(1L)).thenReturn(Optional.of(asset));
        when(alertrepo.save(any(Alert.class))).thenReturn(alert);

        Alert created = alertservice.createAlert(1L, AlertType.TEMP_HIGH, "Overheat warning");

        assertNotNull(created);
        assertEquals(AlertType.TEMP_HIGH, created.getType());
        assertEquals("Overheat warning", created.getMessage());
    }

    @Test
    public void testGetAllAlerts() {
        when(alertrepo.findAll()).thenReturn(List.of(alert));
        List<Alert> alerts = alertservice.getAll();

        assertEquals(1, alerts.size());
        assertEquals("Overheat warning", alerts.get(0).getMessage());
    }

    @Test
    public void testResolveAlert_Success() {
        when(alertrepo.findById(1L)).thenReturn(Optional.of(alert));
        when(alertrepo.save(any(Alert.class))).thenReturn(alert);

        Alert resolved = alertservice.resolveAlert(1L);

        assertEquals(AlertStatus.RESOLVED, resolved.getStatus());
    }

}
