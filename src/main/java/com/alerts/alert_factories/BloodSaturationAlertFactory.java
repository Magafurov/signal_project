package com.alerts.alert_factories;

import com.alerts.alert_types.BloodPressureAlert;
import com.alerts.alert_types.BloodSaturationAlert;

/**
 * Factory that makes alerts for blood pressure conditions. Can be extended to include logic on what to do when such
 * an alert is generated
 */
public class BloodSaturationAlertFactory implements AlertFactory {

    @Override
    public BloodSaturationAlert createAlert(int patientId, String condition, long timestamp) {
        return new BloodSaturationAlert(patientId, condition, timestamp);
    }
}
