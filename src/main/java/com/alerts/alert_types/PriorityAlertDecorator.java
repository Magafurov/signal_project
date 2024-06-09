package com.alerts.alert_types;

public class PriorityAlertDecorator implements AlertInterface {
    @Override
    public int getPatientId() {
        return 0;
    }

    @Override
    public String getCondition() {
        return null;
    }

    @Override
    public long getTimestamp() {
        return 0;
    }
}
