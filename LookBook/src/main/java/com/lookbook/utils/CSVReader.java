package com.lookbook.utils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (InputStream is = CSVReader.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                data.add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}


// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// public class CSVReader {
//     public static List<String[]> readCSV(String filePath) {
//         List<String[]> data = new ArrayList<>();
//         String line = "";
//         try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//             while ((line = br.readLine()) != null) {
//                 String[] fields = line.split(";");
//                 data.add(fields);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         return data;
//     }
// }


