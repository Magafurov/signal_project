package com.alerts.alert_types;

public class BloodSaturationAlert extends Alert {

    public BloodSaturationAlert(int patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
}
