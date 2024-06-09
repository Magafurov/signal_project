package com.alerts.alert_factories;

import com.alerts.alert_types.BloodSaturationAlert;
import com.alerts.alert_types.HypotensiveHypoxemiaAlert;

/**
 * Factory that makes alerts for blood pressure conditions. Can be extended to include logic on what to do when such
 * an alert is generated
 */
public class HypotensiveHypoxemiaAlertFactory implements AlertFactory {

    @Override
    public HypotensiveHypoxemiaAlert createAlert(int patientId, String condition, long timestamp) {
        return new HypotensiveHypoxemiaAlert(patientId, condition, timestamp);
    }
}
