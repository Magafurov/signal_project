package com.alerts.alert_strategies;

import com.alerts.alert_factories.BloodPressureAlertFactory;
import com.alerts.alert_factories.BloodSaturationAlertFactory;
import com.alerts.alert_factories.HypotensiveHypoxemiaAlertFactory;
import com.alerts.alert_types.Alert;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;
import java.util.stream.Collectors;

public class HypotensiveHypoxemiaStrategy implements AlertStrategy {

    @Override
    public List<Alert> checkAlert(Patient patient, List<Alert> alertList) {

        HypotensiveHypoxemiaAlertFactory hypotensiveHypoxemiaAlertFactory = new HypotensiveHypoxemiaAlertFactory();

        List<PatientRecord> patientRecords = patient.getRecords(0, Long.MAX_VALUE);

        double lastSaturation = -1;
        for (int i = patientRecords.size() - 1; i >= 0; i--) {
            PatientRecord p = patientRecords.get(i);

            if (p.getRecordType().equals("Saturation")) {
                lastSaturation = p.getMeasurementValue();
                break;
            }
        }

        if (lastSaturation < 0) {
            return alertList;
        }


        //find last systolic bloop pressure
        double lastSystolicBP = -1;
        for (int i = patientRecords.size() - 1; i >= 0; i--) {
            PatientRecord p = patientRecords.get(i);

            if (p.getRecordType().equals("SystolicPressure")) {
                lastSystolicBP = p.getMeasurementValue();
                break;
            }
        }

        if (lastSystolicBP < 0) {
            return alertList;
        }



        if (lastSaturation < 92 && lastSystolicBP < 90) {
            alertList.add(hypotensiveHypoxemiaAlertFactory.createAlert(patient.getId(), "Hypotensive hypoxemia detected", (long) System.currentTimeMillis()));
        }

        return alertList;

    }
}
