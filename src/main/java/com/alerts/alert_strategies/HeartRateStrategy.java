package com.alerts.alert_strategies;

import com.alerts.alert_types.Alert;
import com.data_management.Patient;

import java.util.List;

public class HeartRateStrategy implements AlertStrategy {

    @Override
    public List<Alert> checkAlert(Patient patient, List<Alert> alertList) {
        return null;
    }
}
