package com.alerts.alert_factories;

import com.alerts.alert_factories.AlertFactory;
import com.alerts.alert_types.BloodPressureAlert;

/**
 * Factory that makes alerts for blood pressure conditions. Can be extended to include logic on what to do when such
 * an alert is generated
 */
public class BloodPressureAlertFactory implements AlertFactory {

    @Override
    public BloodPressureAlert createAlert(int patientId, String condition, long timestamp) {
        return new BloodPressureAlert(patientId, condition, timestamp);
    }
}
