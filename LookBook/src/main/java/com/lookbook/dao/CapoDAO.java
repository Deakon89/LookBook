package com.lookbook.dao;

import com.lookbook.model.Capo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class CapoDAO {
    private List<Capo> capi = new ArrayList<>();
    
    public void loadCapi(String resourcePath) {
        System.out.println("Tentativo di caricare il file: " + resourcePath);
    
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
    
        if (inputStream == null) {
            File file = new File(resourcePath);
            if (!file.exists()) {
                System.err.println("ERRORE: File non trovato -> " + resourcePath);
                return;
            }
            try {
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                System.err.println("Errore nell'apertura del file: " + e.getMessage());
                return;
            }
        }
    
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 0;
    
            
            String header = reader.readLine();
            System.out.println("Header identificato: " + header);
    
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
    
                if (line.isEmpty() || line.replace(";", "").trim().isEmpty()) {
                    System.err.println("Riga vuota ignorata alla linea " + lineNumber);
                    continue;
                }
    
                System.out.println("Riga letta: " + line);
                String[] fields = line.split(";");
    
                if (fields.length < 7) {
                    System.err.println("Riga malformata alla linea " + lineNumber + ": " + line);
                    continue;
                }
    
                try {
                    int id = Integer.parseInt(fields[0].replaceAll("[^0-9]", ""));
                    String dataInserimento = fields[1].trim();
                    String tipologia = fields[2].trim();
                    String marca = fields[3].trim();
                    String taglia = fields[4].trim();
                    double prezzo = Double.parseDouble(fields[5].replaceAll("[^0-9.]", "").trim());
                    String disponibilita = fields[6].trim();
    
                    System.out.println("Capo caricato: " + id + ", " + tipologia + ", " + marca + ", " + taglia + ", " + prezzo + ", " + disponibilita);
                    capi.add(new Capo(id, dataInserimento, tipologia, marca, taglia, prezzo, disponibilita));
    
                } catch (NumberFormatException e) {
                    System.err.println("Errore di parsing alla linea " + lineNumber + ": " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file CSV: " + e.getMessage());
        }
    }
    
    
    public List<Capo> getCapi() {
        return new ArrayList<>(capi);
    }
}
    

