package com.alerts.alert_types;

public interface AlertInterface {
    int getPatientId();
    String getCondition();
    long getTimestamp();
}