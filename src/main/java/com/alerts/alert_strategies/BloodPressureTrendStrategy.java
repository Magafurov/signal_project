package com.alerts.alert_strategies;

import com.alerts.alert_factories.BloodPressureAlertFactory;
import com.alerts.alert_types.Alert;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Strategy for checking blood pressure and generating relevant alert if appropriate.
 * Process data from 'patient' based on algorithm in 'checkAlert'.
 * If alert warranted, append it to 'alertList'
 */
public class BloodPressureTrendStrategy implements AlertStrategy {
    private String bpType; // Can be "SystolicPressure" or "DiastolicPressure"

    public BloodPressureTrendStrategy(String bpType) {
        this.bpType = bpType;
    }

    @Override
    public List<Alert> checkAlert(Patient patient, List<Alert> alertList) {

        BloodPressureAlertFactory bloodPressureAlertFactory = new BloodPressureAlertFactory();

        long currentTimeMinusOneHour = System.currentTimeMillis() - (1 * 60 * 60 * 1000); // 1 hour in milliseconds

        //having records from over 1hr ago should remove basis for alert
        //List<PatientRecord> patientRecords = patient.getRecords(currentTimeMinusOneHour, System.currentTimeMillis());
        List<PatientRecord> patientRecords = patient.getRecords(0, Long.MAX_VALUE);

        //filter for bp values
        List<PatientRecord> bpRecords = patientRecords.stream()
                .filter(record -> bpType.equals(record.getRecordType()))
                .collect(Collectors.toList());

        if (bpRecords.size() < 3) {
            return alertList; // do nothing because not enough data
        }

        //consistent increase in BP over 3 values
        boolean g1 = bpRecords.get(0).getMeasurementValue() + 10 <= bpRecords.get(1).getMeasurementValue();
        boolean g2 = bpRecords.get(1).getMeasurementValue() + 10 <= bpRecords.get(2).getMeasurementValue();

        if (g1 && g2) {
            alertList.add(bloodPressureAlertFactory.createAlert(patient.getId(), "BP increase trend", bpRecords.get(2).getTimestamp()));
        }

        //consistent decrease in BP over 3 values
        boolean l1 = bpRecords.get(0).getMeasurementValue() - 10 >= bpRecords.get(1).getMeasurementValue();
        boolean l2 = bpRecords.get(1).getMeasurementValue()  - 10 >= bpRecords.get(2).getMeasurementValue();

        if (l1 && l2) {
            alertList.add(bloodPressureAlertFactory.createAlert(patient.getId(), "BP decrease trend", bpRecords.get(2).getTimestamp()));
        }

        return alertList;
    }


}
