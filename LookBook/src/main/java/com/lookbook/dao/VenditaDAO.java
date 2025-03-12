package com.lookbook.dao;

import com.lookbook.model.Vendita;
import com.lookbook.utils.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class VenditaDAO {
    private List<Vendita> vendite = new ArrayList<>();
    
    public void loadVendite(String filePath) {
        List<String[]> rawData = CSVReader.readCSV(filePath);
        for (String[] data : rawData) {
            try {
                int id = Integer.parseInt(data[0]);
                int idCapo = Integer.parseInt(data[1]);
                int idUtente = Integer.parseInt(data[2]);
                vendite.add(new Vendita(id, idCapo, idUtente));
            } catch (NumberFormatException e) {
                System.err.println("Errore di formato nei dati CSV: " + e.getMessage());
            }
        }
    }
    
    public List<Vendita> getVendite() {
        return vendite;
    }
}
