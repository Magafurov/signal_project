package com.data_management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The FileDataReader class is responsible for reading patient data from a specified file
 * and storing it in the provided DataStorage object. It is an implementation of the DataReader interface. The file should contain lines of data
 * in the following format:
 *
 * Patient ID: 10, Timestamp: 1717930858003, Label: ECG, Data: 0.6119830512965708
 *
 * or for percentage data:
 *
 * Patient ID: 12, Timestamp: 1717930858014, Label: Saturation, Data: 100.0%
 */
public class FileDataReader implements DataReader {

    private String sourceFile;

    /**
     * Constructs a FileDataReader with the specified source file path.
     *
     * @param sourcePath the path to the source file containing patient data
     */
    public FileDataReader(String sourcePath) {
        this.sourceFile = sourcePath;
    }

    /**
     * Reads data from the source file and adds it to the provided DataStorage object.
     * Each line in the file is expected to be in the format:
     *
     * Patient ID: <id>, Timestamp: <timestamp>, Label: <label>, Data: <data>
     *
     * Data can be either a numeric value or a percentage (e.g., 100.0%).
     *
     * @param dataStorage the DataStorage object to which the patient data will be added
     * @throws IOException if an I/O error occurs while reading the file
     */
    public void readData(DataStorage dataStorage) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse the line
                String[] parts = line.split(", ");
                int patientId = Integer.parseInt(parts[0].split(": ")[1]);
                long timestamp = Long.parseLong(parts[1].split(": ")[1]);
                String label = parts[2].split(": ")[1];
                String dataStr = parts[3].split(": ")[1];

                // Check if the data is a percentage; 95% stored as 95.0
                double data;
                if (dataStr.endsWith("%")) {
                    data = Double.parseDouble(dataStr.substring(0, dataStr.length() - 1));
                } else {
                    data = Double.parseDouble(dataStr);
                }

                // Add the parsed data to dataStorage
                dataStorage.addPatientData(patientId, data, label, timestamp);
            }
        }
    }
}
