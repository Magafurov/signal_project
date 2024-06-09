package alert_testing;

import static org.junit.jupiter.api.Assertions.*;

import com.alerts.alert_factories.BloodPressureAlertFactory;
import com.alerts.alert_strategies.BloodPressureTrendStrategy;
import com.alerts.alert_types.Alert;
import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

class AlertTest {

    @Test
    void testBPTrendAlertUp() {

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
    void testBPTrendAlertDown() {

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
    void testFileDataReader() {


    }
}
