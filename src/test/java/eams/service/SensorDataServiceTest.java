package eams.service;

import com.eams.entity.AlertType;
import com.eams.entity.Asset;
import com.eams.entity.SensorData;
import com.eams.exception.InvalidSensorDataException;
import com.eams.repository.AssetRepository;
import com.eams.repository.SensorDataRepository;
import com.eams.service.AlertService;
import com.eams.service.SensorDataService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorDataServiceTest {

	    @Mock
	    private SensorDataRepository sensorDataRepository;

	    @Mock
	    private AssetRepository assetRepository;

	    @Mock
	    private AlertService alertService; 

	    @InjectMocks
	    private SensorDataService sensorDataService; 

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testSendSensorData_ValidWithAlerts() {
	        Long assetId = 1L;
	        Asset asset = new Asset();
	        asset.setAsset_id(assetId);
	        asset.setThresholdPressure(50.0);
	        asset.setThresholdTemp(30.0);

	        SensorData input = new SensorData(null, assetId, 60.0, 35.0, null);
	        when(assetRepository.findById(assetId)).thenReturn(Optional.of(asset));
	        when(sensorDataRepository.save(any(SensorData.class))).thenAnswer(inv -> inv.getArgument(0));
	        Boolean result = sensorDataService.sendSensorData(input);
	        assertTrue(result);
	        verify(alertService).createAlert(eq(assetId), eq(AlertType.TEMP_HIGH), anyString());

	    }
    @Test
    void testSendSensorData_ValidNoAlert() {
        Long assetId = 2L;
        Asset asset = new Asset();
        asset.setAsset_id(assetId);
        asset.setThresholdPressure(100.0);
        asset.setThresholdTemp(100.0);

        SensorData input = new SensorData(null, assetId, 50.0, 40.0, null);

        when(assetRepository.findById(assetId)).thenReturn(Optional.of(asset));
        when(sensorDataRepository.save(any(SensorData.class))).thenAnswer(inv -> inv.getArgument(0));

        Boolean result = sensorDataService.sendSensorData(input);

        assertTrue(result);
        verify(alertService, never()).createAlert(anyLong(), any(), anyString());
    }

    @Test
    void testSendSensorData_InvalidPressure() {
        SensorData input = new SensorData(null, 1L, -5.0, 20.0, null);

        InvalidSensorDataException exception = assertThrows(
                InvalidSensorDataException.class,
                () -> sensorDataService.sendSensorData(input)
        );

        assertEquals("Invalid Temperature Value !", exception.getMessage());
        verify(sensorDataRepository, never()).save(any());
    }

    @Test
    void testSendSensorData_InvalidTemperature() {
        SensorData input = new SensorData(null, 1L, 10.0, -10.0, null);

        InvalidSensorDataException exception = assertThrows(
                InvalidSensorDataException.class,
                () -> sensorDataService.sendSensorData(input)
        );

        assertEquals("Invalid Pressure Value !", exception.getMessage());
        verify(sensorDataRepository, never()).save(any());
    }
}
