package alert_testing;

import static org.junit.jupiter.api.Assertions.*;

import com.alerts.alert_factories.BloodPressureAlertFactory;
import com.alerts.alert_strategies.*;
import com.alerts.alert_types.Alert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

class AlertTest {

    @BeforeEach
    void resetStorage() {
        DataStorage.resetDataStorage();
    }

    @Test
    void testBPTrendAlertUpSystolicPressure() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(0, 100.0, "SystolicPressure", 1714376789050L);
        storage.addPatientData(0, 200.0, "SystolicPressure", 1714376789051L);
        storage.addPatientData(0, 300.0, "SystolicPressure", 1714376789052L);

        List<Alert> alerts = new ArrayList<>();

        BloodPressureTrendStrategy b = new BloodPressureTrendStrategy("SystolicPressure");

        alerts = b.checkAlert(storage.getAllPatients().get(0), alerts);

        Alert alert = alerts.get(0);

        assertEquals(alert.getPatientId(), 0);
        assertEquals(alert.getCondition(), "BP increase trend");
        assertEquals(alert.getTimestamp(), 1714376789052L);

    }

    @Test
    void testBPTrendAlertDownSystolicPressure() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(0, 120.0, "SystolicPressure", 1714376789050L);
        storage.addPatientData(0, 110.0, "SystolicPressure", 1714376789051L);
        storage.addPatientData(0, 100.0, "SystolicPressure", 1714376789052L);

        List<Alert> alerts = new ArrayList<>();

        BloodPressureTrendStrategy b = new BloodPressureTrendStrategy("SystolicPressure");

        alerts = b.checkAlert(storage.getAllPatients().get(0), alerts);

        Alert alert = alerts.get(0);

        assertEquals(alert.getPatientId(), 0);
        assertEquals(alert.getCondition(), "BP decrease trend");
        assertEquals(alert.getTimestamp(), 1714376789052L);

    }

    @Test
    void testBPTrendAlertUpDiastolicPressure() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(0, 100.0, "DiastolicPressure", 1714376789050L);
        storage.addPatientData(0, 200.0, "DiastolicPressure", 1714376789051L);
        storage.addPatientData(0, 300.0, "DiastolicPressure", 1714376789052L);

        List<Alert> alerts = new ArrayList<>();

        BloodPressureTrendStrategy b = new BloodPressureTrendStrategy("DiastolicPressure");

        alerts = b.checkAlert(storage.getAllPatients().get(0), alerts);

        Alert alert = alerts.get(0);

        assertEquals(alert.getPatientId(), 0);
        assertEquals(alert.getCondition(), "BP increase trend");
        assertEquals(alert.getTimestamp(), 1714376789052L);

    }

    @Test
    void testBPTrendAlertDownDiastolicPressure() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(0, 120.0, "DiastolicPressure", 1714376789050L);
        storage.addPatientData(0, 110.0, "DiastolicPressure", 1714376789051L);
        storage.addPatientData(0, 100.0, "DiastolicPressure", 1714376789052L);

        List<Alert> alerts = new ArrayList<>();

        BloodPressureTrendStrategy b = new BloodPressureTrendStrategy("DiastolicPressure");

        alerts = b.checkAlert(storage.getAllPatients().get(0), alerts);

        Alert alert = alerts.get(0);

        assertEquals(alert.getPatientId(), 0);
        assertEquals(alert.getCondition(), "BP decrease trend");
        assertEquals(alert.getTimestamp(), 1714376789052L);

    }


    @Test
    void testBPCriticalThreshold() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(0, 100.0, "DiastolicPressure", 1714376789050L);
        storage.addPatientData(0, 110.0, "DiastolicPressure", 1714376789051L);
        storage.addPatientData(0, 200.0, "DiastolicPressure", 1714376789052L);

        List<Alert> alerts = new ArrayList<>();

        BPCriticalThresholdStrategy b = new BPCriticalThresholdStrategy();

        alerts = b.checkAlert(storage.getAllPatients().get(0), alerts);

        Alert alert = alerts.get(0);

        assertEquals(alert.getPatientId(), 0);
        assertEquals(alert.getCondition(), "BP critical threshold");
        assertEquals(alert.getTimestamp(), 1714376789052L);

    }


    @Test
    void testLowBloodSaturation() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(0, 99.0, "Saturation", 1714376789050L);
        storage.addPatientData(0, 96.0, "Saturation", 1714376789051L);
        storage.addPatientData(0, 90.0, "Saturation", 1714376789052L);

        List<Alert> alerts = new ArrayList<>();

        LowBloodSaturationStrategy b = new LowBloodSaturationStrategy();

        alerts = b.checkAlert(storage.getAllPatients().get(0), alerts);

        Alert alert = alerts.get(0);

        assertEquals(alert.getPatientId(), 0);
        assertEquals(alert.getCondition(), "Low blood saturation");
        assertEquals(alert.getTimestamp(), 1714376789052L);

    }


    @Test
    void testRapidBloodSaturationDrop() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(0, 99.0, "Saturation", 1714376789050L);
        storage.addPatientData(0, 96.0, "Saturation", 1714376789051L);
        storage.addPatientData(0, 93.0, "Saturation", 1714376789052L);

        List<Alert> alerts = new ArrayList<>();

        RapidBloodSaturationDropStrategy b = new RapidBloodSaturationDropStrategy();

        alerts = b.checkAlert(storage.getAllPatients().get(0), alerts);

        Alert alert = alerts.get(0);

        assertEquals(alert.getPatientId(), 0);
        assertEquals(alert.getCondition(), "Rapid blood saturation drop");
        assertEquals(alert.getTimestamp(), 1714376789052L);

    }


    @Test
    void testRapidBloodSaturationDropNeg() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(0, 99.0, "Saturation", 1714376789050L - (1000 * 60 * 10) - 10);
        storage.addPatientData(0, 96.0, "Saturation", 1714376789051L);
        storage.addPatientData(0, 93.0, "Saturation", 1714376789052L);

        List<Alert> alerts = new ArrayList<>();

        RapidBloodSaturationDropStrategy b = new RapidBloodSaturationDropStrategy();

        alerts = b.checkAlert(storage.getAllPatients().get(0), alerts);

        assertEquals(alerts.size(), 0);

    }


    @Test
    void testHypotensiveHypoxemia() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(0, 100.0, "SystolicPressure", 1714376789050L);
        storage.addPatientData(0, 96.0, "Saturation", 1714376789051L);
        storage.addPatientData(0, 89.0, "SystolicPressure", 1714376789052L);
        storage.addPatientData(0, 91.0, "Saturation", 1714376789053L);

        List<Alert> alerts = new ArrayList<>();

        HypotensiveHypoxemiaStrategy b = new HypotensiveHypoxemiaStrategy();

        alerts = b.checkAlert(storage.getAllPatients().get(0), alerts);

        Alert alert = alerts.get(0);

        assertEquals(alert.getPatientId(), 0);
        assertEquals(alert.getCondition(), "Hypotensive hypoxemia detected");

    }

}
