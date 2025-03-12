package com.lookbook.dao;

import com.lookbook.model.Capo;
import com.lookbook.utils.CSVReader;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Pattern;

public class CapoDAO {
    private List<Capo> capi = new ArrayList<>();
    
    public void loadCapi(String filePath) {
        List<String[]> rawData = CSVReader.readCSV(filePath);
        Pattern cleanPattern = Pattern.compile("[^\\d.]");
        for (String[] data : rawData) {
            if (data.length == 0) continue;
            try {
                String cleanedId = cleanPattern.matcher(data[0]).replaceAll("");
                int id = Integer.parseInt(cleanedId);
            String dataInserimento = data[1];
            String tipologia = data[2];
            String marca = data[3];
            String taglia = data[4];
            String cleanedPrice = cleanPattern.matcher(data[5]).replaceAll("");
            double prezzo = Double.parseDouble(cleanedPrice); // Correct parsing of double
            String disponibile = data[6]; // Correct index for 'disponibile'
            capi.add(new Capo(id, dataInserimento, tipologia, marca, taglia, prezzo, disponibile));
         } catch (NumberFormatException e) {
             System.err.println("Errore di formato nei dati CSV: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Dati mancanti nella riga, riga saltata.");
        }
     }
}
    
    public List<Capo> getCapi() {
        return capi;
    }
}
