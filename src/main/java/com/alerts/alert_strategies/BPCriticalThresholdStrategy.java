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
public class BPCriticalThresholdStrategy implements AlertStrategy {
    int latestIndex;

    @Override
    public List<Alert> checkAlert(Patient patient, List<Alert> alertList) {


        BloodPressureAlertFactory bloodPressureAlertFactory = new BloodPressureAlertFactory();

        List<PatientRecord> patientRecords = patient.getRecords(0, Long.MAX_VALUE);

        //filter for bp values
        List<PatientRecord> bpSysRecords = patientRecords.stream()
                .filter(record -> "SystolicPressure".equals(record.getRecordType()))
                .collect(Collectors.toList());

        if (bpSysRecords.size() < 1) {
            //do nothing
        } else {
            latestIndex = bpSysRecords.size() - 1;

            if (bpSysRecords.get(latestIndex).getMeasurementValue() > 180 || bpSysRecords.get(latestIndex).getMeasurementValue() < 90) {
                alertList.add(bloodPressureAlertFactory.createAlert(patient.getId(), "BP critical threshold", bpSysRecords.get(latestIndex).getTimestamp()));
            }
        }


        //filter for bp values
        List<PatientRecord> bpDiaRecords = patientRecords.stream()
                .filter(record -> "DiastolicPressure".equals(record.getRecordType()))
                .collect(Collectors.toList());

        if (bpDiaRecords.size() < 1) {
            return alertList; // do nothing because not enough data
        }

        latestIndex = bpDiaRecords.size() -1;

        if (bpDiaRecords.get(latestIndex).getMeasurementValue() > 120 || bpDiaRecords.get(latestIndex).getMeasurementValue() < 60) {
            alertList.add(bloodPressureAlertFactory.createAlert(patient.getId(), "BP critical threshold", bpDiaRecords.get(latestIndex).getTimestamp()));
        }

        return alertList;

    }


}
