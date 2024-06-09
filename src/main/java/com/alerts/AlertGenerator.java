package com.alerts;

import com.alerts.alert_strategies.AlertStrategy;
import com.alerts.alert_types.Alert;
import com.data_management.DataStorage;
import com.data_management.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;





    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {

//        List<Alert> alerts = new ArrayList<>();
//
//        for (AlertStrategy alertStart : alertStartList) {
//            alertStart.checkAlert(patient, alerts);
//        }
////
//
//        for (Alert alert : alerts) {
//            triggerAlert(alert);
//        }

//        //alert definitions
//        //blood pressure data alerts
//        //make assumtion all 3 reading must be in past hour
//        patient.getRecords()
//
//
//        //we have to create alert
//        Alert alert = new Alert(patient.getId(), condition, timestamp);
//
//        //pass it to triggerAlert
//        triggerAlert(alert);

    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff
    }
}
