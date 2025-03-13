package com.lookbook.controller;

import com.lookbook.service.CapoService;
import com.lookbook.service.UtenteService;
import java.util.Scanner;

public class CommandController {
    private CapoService capoService;
    private UtenteService utenteService;
    private Scanner scanner;

    public CommandController(CapoService capoService, UtenteService utenteService) {
        this.capoService = capoService;
        this.utenteService = utenteService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
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
                        capoService.displayAllCapi();
                        break;
                    case 2:
                        capoService.purchaseCapo();
                        break;
                    case 3:
                        capoService.returnCapo();
                        break;
                    case 4:
                        utenteService.addNewUtente();
                        break;
                    case 5:
                        capoService.exportAvailableCapi();
                        break;
                    case 0:
                        run = false;
                        break;
                    default:
                        System.out.println("Opzione non valida.");
                }
            } else {
                System.out.println("Inserisci un numero valido.");
                scanner.next(); 
            }
        }
        scanner.close();
    }
}