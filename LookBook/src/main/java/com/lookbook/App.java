package com.lookbook;

import com.lookbook.dao.CapoDAO;
import com.lookbook.dao.UtenteDAO;
import com.lookbook.dao.VenditaDAO;
import com.lookbook.model.Capo;
import com.lookbook.model.Utente;
import com.lookbook.model.Vendita;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class App {
    private static UtenteDAO utenteDAO = new UtenteDAO();
    private static CapoDAO capoDAO = new CapoDAO();
    private static VenditaDAO venditaDAO = new VenditaDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Caricamento iniziale dei dati
        utenteDAO.loadUtenti("LookBook/src/main/java/com/lookbook/resources/utenti.csv");
        capoDAO.loadCapi("LookBook/src/main/java/com/lookbook/resources/capi.csv");
        venditaDAO.loadVendite("LookBook/src/main/java/com/lookbook/resources/vendite.csv");
        boolean run = true;
        while (run) {
            System.out.println("Scegli un'opzione:");
            System.out.println("1 - Visualizza tutti i capi");
            System.out.println("2 - Comprare un capo");
            System.out.println("3 - Restituire un capo");
            System.out.println("4 - Aggiungere nuovo utente");
            System.out.println("5 - Esportare capi disponibili");
            System.out.println("0 - Uscire");

            if(scanner.hasNextInt()){
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    displayAllCapi();
                    break;
                case 2:
                    purchaseCapo();
                    break;
                case 3:
                    returnCapo();
                    break;
                case 4:
                    addNewUtente();
                    break;
                case 5:
                    exportAvailableCapi();
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Opzione non valida.");
            }
        }else{
            System.out.println("Inserisci un numero valido.");
            scanner.next(); // Consume the non-integer input
        }
        }
        scanner.close();
    }

    private static void displayAllCapi() {
        List<Capo> capi = capoDAO.getCapi();
        for (Capo capo : capi) {
            System.out.println(capo);
        }
    }
    private static void purchaseCapo() {
    System.out.println("Inserisci l'ID del capo che vuoi acquistare:");
    int capoId = scanner.nextInt();
    scanner.nextLine();  // Consuma la linea dopo l'input numerico
    Capo capo = capoDAO.getCapi().stream()
            .filter(c -> c.getId() == capoId && c.getDisponibile().equals("SI"))
            .findFirst()
            .orElse(null);

    if (capo != null) {
        // Assumi che tu abbia un modo per ottenere l'ID dell'utente (e.g., sessione utente, input, ecc.)
        System.out.println("Inserisci il tuo ID utente:");
        int idUtente = scanner.nextInt();
        scanner.nextLine();  // Consuma la linea dopo l'input numerico

        int newVenditaId = getProssimoIdVendita();  // Implementa questa logica come necessario
                Vendita nuovaVendita = new Vendita(newVenditaId, capoId, idUtente);
                saveVenditaToFile(nuovaVendita);  // Salva la nuova vendita nel file CSV
        
                capo.setDisponibile("NO");
                System.out.println("Hai acquistato il capo: " + capo);
            } else {
                System.out.println("Capo non disponibile o non esistente.");
            }
        }
        
        private static int lastVenditaId = 0;  // Assumi che parta da 0 o lo inizializzi dal file/databse all'avvio dell'app

        private static int getProssimoIdVendita() {
        return ++lastVenditaId;  // Incrementa e ritorna il nuovo ID
    }
        
        private static void saveVenditaToFile(Vendita vendita) {
        String filePath = "LookBook/src/main/java/com/lookbook/resources/vendite.csv"; // Percorso del file CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // 'true' per appendere al file esistente
        String line = String.format("%d;%d;%d\n",
                vendita.getId(),
                vendita.getIdCapo(),
                vendita.getIdUtente());
        writer.write(line);
    } catch (IOException e) {
        System.err.println("Errore nella scrittura del file CSV delle vendite: " + e.getMessage());
    }
}

    

    private static void returnCapo() {
         System.out.println("Inserisci l'ID del capo che vuoi restituire:");
         int capoId = scanner.nextInt();
         List<Capo> capi = capoDAO.getCapi();
         boolean found = false;
         for (Capo capo : capi) {
             if (capo.getId() == capoId && capo.getDisponibile().equals("NO")) {
                 capo.setDisponibile("SI");
                 System.out.println("Hai restituito il capo: " + capo);
                 found = true;
                 break;
             }
         }
         if (!found) {
             System.out.println("Capo non trovato o già disponibile.");
         }
    }

    private static void saveUtenteToFile(Utente utente) {
        String filePath = "LookBook/src/main/java/com/lookbook/resources/utenti.csv"; // Percorso del tuo file CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // 'true' per appendere dati al file esistente
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



private static void addNewUtente() {
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

    // Salva l'elenco aggiornato degli utenti nel file CSV
    saveUtenteToFile(newUtente);
}


private static void exportAvailableCapi() {
    String fileName = "capi_" + java.time.LocalDate.now() + ".csv";
    StringBuilder sb = new StringBuilder();
    
    for (Capo capo : capoDAO.getCapi()) {
        if (capo.getDisponibile().equals("SI")) {
            sb.append(capo.getId()).append(";")
              .append(capo.getDataInserimento()).append(";")
              .append(capo.getTipologia()).append(";")
              .append(capo.getMarca()).append(";")
              .append(capo.getTaglia()).append(";")
              .append(capo.getPrezzo()).append(";")
              .append(capo.getDisponibile()).append("\n");
        }
    }
    
    try (PrintWriter out = new PrintWriter(fileName)) {
        out.print(sb.toString());
        System.out.println("File esportato: " + fileName);
    } catch (FileNotFoundException e) {
        System.err.println("Errore durante la creazione del file: " + e.getMessage());
    }
}

}
