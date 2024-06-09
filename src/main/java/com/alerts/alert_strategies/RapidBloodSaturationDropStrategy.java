package com.alerts.alert_strategies;

import com.alerts.alert_factories.BloodSaturationAlertFactory;
import com.alerts.alert_types.Alert;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;
import java.util.stream.Collectors;

public class RapidBloodSaturationDropStrategy implements AlertStrategy {

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


        double largestValue = Double.MIN_VALUE; // Start with the largest possible integer

        for (int i = saturationRecords.size() - 1; i >= 0; i--) {
            PatientRecord p = saturationRecords.get(i);

            if (p.getTimestamp() + (1000 * 60 * 10) < saturationRecords.get(latestIndex).getTimestamp()) {
                break;
            }

            if (p.getMeasurementValue() > largestValue) {
                largestValue = p.getMeasurementValue();
            }

        }


        if (largestValue > 5 + saturationRecords.get(latestIndex).getMeasurementValue()) {
            alertList.add(bloodSaturationAlertFactory.createAlert(patient.getId(), "Rapid blood saturation drop", saturationRecords.get(latestIndex).getTimestamp()));
        }

        return alertList;

    }

}
