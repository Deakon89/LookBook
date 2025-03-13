package com.lookbook;

import com.lookbook.controller.CommandController;
import com.lookbook.dao.CapoDAO;
import com.lookbook.dao.UtenteDAO;
import com.lookbook.dao.VenditaDAO;
import com.lookbook.service.CapoService;
import com.lookbook.service.UtenteService;

public class App {
    private static UtenteDAO utenteDAO = new UtenteDAO();
    private static CapoDAO capoDAO = new CapoDAO();
    private static VenditaDAO venditaDAO = new VenditaDAO();

    public static void main(String[] args) {
        utenteDAO.loadUtenti("/utenti.csv");
        capoDAO.loadCapi("capi.csv");
        venditaDAO.loadVendite("/vendite.csv");
        //load data
        capoDAO.getCapi();
        utenteDAO.getUtenti();
        venditaDAO.getVendite();

        // create service
        CapoService capoService = new CapoService(capoDAO);
        UtenteService utenteService = new UtenteService(utenteDAO);

        // create controller
        CommandController controller = new CommandController(capoService, utenteService);
        controller.start();
    }
}


