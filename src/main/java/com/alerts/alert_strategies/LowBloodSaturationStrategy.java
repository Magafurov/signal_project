package com.alerts.alert_strategies;

import com.alerts.alert_factories.BloodSaturationAlertFactory;
import com.alerts.alert_types.Alert;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;
import java.util.stream.Collectors;

public class LowBloodSaturationStrategy implements AlertStrategy {

    @Override
    public List<Alert> checkAlert(Patient patient, List<Alert> alertList) {


        BloodSaturationAlertFactory bloodSaturationAlertFactory = new BloodSaturationAlertFactory();

        List<PatientRecord> patientRecords = patient.getRecords(0, Long.MAX_VALUE);

        //filter for bp values
        List<PatientRecord> saturationRecords = patientRecords.stream()
                .filter(record -> "Saturation".equals(record.getRecordType()))
                .collect(Collectors.toList());

        if (saturationRecords.size() < 1) {
            return alertList;
        }

        int latestIndex = saturationRecords.size() - 1;

        if (saturationRecords.get(latestIndex).getMeasurementValue() < 92) {
            alertList.add(bloodSaturationAlertFactory.createAlert(patient.getId(), "Low blood saturation", saturationRecords.get(latestIndex).getTimestamp()));
        }

        return alertList;

    }

}
