package com.lookbook.service;

import com.lookbook.dao.CapoDAO;
import com.lookbook.model.Capo;
import com.lookbook.model.Vendita;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class CapoService {
    private CapoDAO capoDAO;
    private Scanner scanner;

    public CapoService(CapoDAO capoDAO) {
        this.capoDAO = capoDAO;
        this.scanner = new Scanner(System.in);
    }

    public void displayAllCapi() {
        List<Capo> capi = capoDAO.getCapi();
        for (Capo capo : capi) {
            System.out.println(capo);
        }
    }

    public void saveVenditaToFile(Vendita vendita) {
        String filePath = "LookBook/src/main/resources/vendite.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { 
        String line = String.format("%d;%d;%d\n",
                vendita.getId(),
                vendita.getIdCapo(),
                vendita.getIdUtente());
        writer.write(line);
    } catch (IOException e) {
        System.err.println("Errore nella scrittura del file CSV delle vendite: " + e.getMessage());
    }
}
    
    public void purchaseCapo() {
         System.out.println("Inserisci l'ID del capo che vuoi acquistare:");
    int capoId = scanner.nextInt();
    scanner.nextLine();  
    Capo capo = capoDAO.getCapi().stream()
            .filter(c -> c.getId() == capoId && c.getDisponibile().equals("SI"))
            .findFirst()
            .orElse(null);
            
    if (capo != null) {
        System.out.println("Inserisci il tuo ID utente:");
        int idUtente = scanner.nextInt();
        scanner.nextLine();  

        int newVenditaId = getProssimoIdVendita();  
                Vendita nuovaVendita = new Vendita(newVenditaId, capoId, idUtente);
                saveVenditaToFile(nuovaVendita);  
        
                capo.setDisponibile("NO");
                System.out.println("Hai acquistato il capo: " + capo);
            } else {
                System.out.println("Capo non disponibile o non esistente.");
            }
        }

        public int lastVenditaId = 0;  

        public int getProssimoIdVendita() {
        return ++lastVenditaId;  
    }

    public void returnCapo() {
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

   public void exportAvailableCapi() {
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

