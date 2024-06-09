package com.alerts.alert_types;

public class ManualTriggeredAlert extends Alert {

    public ManualTriggeredAlert(int patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
}
