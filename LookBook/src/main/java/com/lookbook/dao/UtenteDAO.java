package com.lookbook.dao;

import com.lookbook.model.Utente;
import com.lookbook.utils.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
    private List<Utente> utenti = new ArrayList<>();

    public void loadUtenti(String filePath) {
        List<String[]> rawData = CSVReader.readCSV(filePath);
        for (String[] data : rawData) {
            try {
            int id = Integer.parseInt(data[0]);
            String nome = data[1];
            String cognome = data[2];
            String dataDiNascita = data[3];
            String indirizzo = data[4];
            String documentoID = data[5];
            utenti.add(new Utente(id, nome, cognome, dataDiNascita, indirizzo, documentoID));
        } catch (NumberFormatException e) {
            System.err.println("Errore di formato nei dati CSV: " + e.getMessage());
        }
        }
    }

    public List<Utente> getUtenti() {
        return utenti;
    }
}
