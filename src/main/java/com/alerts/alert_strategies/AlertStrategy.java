package com.alerts.alert_strategies;

import com.alerts.alert_types.Alert;
import com.data_management.Patient;

import java.util.List;

/**
 * Interface for strategies. Process data from 'patient' based on algorithm in 'checkAlert'.
 * If alert warranted, append it to 'alertList'
 */
public interface AlertStrategy {
    List<Alert> checkAlert(Patient patient, List<Alert> alertList);
}
