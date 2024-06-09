package data_management;

import static org.junit.jupiter.api.Assertions.*;

import com.data_management.FileDataReader;
import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.PatientRecord;

import java.io.IOException;
import java.util.List;

class DataStorageTest {

    //basic test of DataStorage funcitonality
    @Test
    void testAddAndGetRecords() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(2, records.size()); // Check if two records are retrieved
        assertEquals(100.0, records.get(0).getMeasurementValue()); // Validate first record
    }

    @Test
    void testGetRecordsRange() {

        // DataReader reader
        DataStorage storage = DataStorage.getInstance();
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789052L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789053L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789054L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789055L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789051L, 1714376789054L);
        assertEquals(4, records.size()); // Check if two records are retrieved
        assertEquals(200.0, records.get(0).getMeasurementValue()); // Validate first record
    }

    @Test
    void testFileDataReader() {

//        FileDataReader f = new FileDataReader("myoutput/Saturation.txt");
//
//        DataStorage d = new DataStorage();
//
//        try {
//            f.readData(d);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        assert
//
            System.out.println("test");
    }
}
