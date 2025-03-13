package com.lookbook.dao;

import com.lookbook.model.Utente;


import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
    private List<Utente> utenti = new ArrayList<>();

    public void loadUtenti(String resourcePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resourcePath)))) {
            reader.lines()
                .skip(1) // Skip the header line
                .map(line -> line.split(";"))
                .filter(data -> data.length > 5 && !data[0].isEmpty())
                .map(data -> {
                    return new Utente(
                        Integer.parseInt(data[0]), // ID
                        data[1], // Nome
                        data[2], // Cognome
                        data[3], // Data di nascita
                        data[4], // Indirizzo
                        data[5]  // Documento ID
                    );
                })
                .forEach(utenti::add);
        } catch (IOException e) {
            throw new UncheckedIOException("Errore durante la lettura del file CSV", e);
        }
    }

    public List<Utente> getUtenti() {
        return utenti;
    }
}
