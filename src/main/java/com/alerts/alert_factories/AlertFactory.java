package com.alerts.alert_factories;

import com.alerts.alert_strategies.AlertStrategy;
import com.alerts.alert_types.Alert;
import com.data_management.Patient;

/**
 * Interface for a factory that creates a condition alert based on some arguments
 */
public interface AlertFactory {
    Alert createAlert(int patientId, String condition, long timestamp);

}
