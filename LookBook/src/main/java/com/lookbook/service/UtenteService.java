package com.lookbook.service;

import com.lookbook.dao.UtenteDAO;
import com.lookbook.model.Utente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UtenteService {
    private UtenteDAO utenteDAO;
    private Scanner scanner;

    public UtenteService(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
        this.scanner = new Scanner(System.in);
    }

    public void addNewUtente() {
        System.out.println("Inserisci il nome dell'utente:");
        String nome = scanner.nextLine();
        System.out.println("Inserisci il cognome dell'utente:");
        String cognome = scanner.nextLine();
        System.out.println("Inserisci la data di nascita (YYYY-MM-DD):");
        String dataDiNascita = scanner.nextLine();
        System.out.println("Inserisci l'indirizzo:");
        String indirizzo = scanner.nextLine();
        System.out.println("Inserisci il documento ID:");
        String documentoID = scanner.nextLine();
    
        int newId = utenteDAO.getUtenti().stream().mapToInt(Utente::getId).max().orElse(0) + 1;
        Utente newUtente = new Utente(newId, nome, cognome, dataDiNascita, indirizzo, documentoID);
        utenteDAO.getUtenti().add(newUtente);
        System.out.println("Nuovo utente aggiunto: " + newUtente);
    

        saveUtenteToFile(newUtente);
    }

       private static void saveUtenteToFile(Utente utente) {
        String filePath = "LookBook/src/main/resources/utenti.csv"; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { 
            String line = String.format("\n%d;%s;%s;%s;%s;%s\n",
                    utente.getId(),
                    utente.getNome(),
                    utente.getCognome(),
                    utente.getDataDiNascita(),
                    utente.getIndirizzo(),
                    utente.getDocumentoId());
            writer.write(line);
        } catch (IOException e) {
            System.err.println("Errore nella scrittura del file CSV: " + e.getMessage());
        }
    
    }
}
