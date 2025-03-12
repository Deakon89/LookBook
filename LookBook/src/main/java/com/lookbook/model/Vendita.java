package com.lookbook.model;
public class Vendita {
    
    private int id;
    private int idCapo;
    private int idUtente;

    /**
     * Costruisce un'istanza di una vendita.
     *
     * @param id L'ID univoco della vendita.
     * @param idCapo L'ID del capo venduto.
     * @param idUtente L'ID dell'utente che ha effettuato l'acquisto.
     */
    public Vendita(int id, int idCapo, int idUtente) {
        if (id < 0 || idCapo < 0 || idUtente < 0) {
            throw new IllegalArgumentException("Gli ID non possono essere negativi.");
        }
        this.id = id;
        this.idCapo = idCapo;
        this.idUtente = idUtente;
    }

    public int getId() {
        return id;
    }

    public int getIdCapo() {
        return idCapo;
    }

    public int getIdUtente() {
        return idUtente;
    }
}
