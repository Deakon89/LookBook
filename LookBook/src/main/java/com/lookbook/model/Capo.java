package com.lookbook.model;
public class Capo {
    private int id;
    private String dataInserimento;
    private String tipologia;
    private String marca;
    private String taglia;
    private double prezzo; // Cambiato da String a double
    private String disponibile; // Cambiato da 'Disponibile' a 'disponibile'

    /**
     * Costruisce un'istanza di un capo di abbigliamento.
     *
     * @param id L'ID univoco del capo.
     * @param dataInserimento La data di inserimento del capo nel sistema.
     * @param tipologia La tipologia del capo (es. pantaloni, maglietta).
     * @param marca La marca del capo.
     * @param taglia La taglia del capo.
     * @param prezzo Il prezzo del capo.
     * @param disponibile Stato della disponibilità del capo (es. "SI", "NO").
     */
    public Capo(int id, String dataInserimento, String tipologia, String marca, String taglia, double prezzo, String disponibile) {
        if (id < 0) {
            throw new IllegalArgumentException("L'ID non può essere negativo.");
        }
        if (taglia.isEmpty()) {
            throw new IllegalArgumentException("La taglia non può essere vuota.");
        }
        this.id = id;
        this.dataInserimento = dataInserimento;
        this.tipologia = tipologia;
        this.marca = marca;
        this.taglia = taglia;
        this.prezzo = prezzo;
        this.disponibile = disponibile;
    }

    // Metodi getter rimangono invariati

    public int getId() {
        return id;
    }   

    public String getDataInserimento() {
        return dataInserimento;    
    }

    public String getTipologia() {    
        return tipologia;
    }

    public String getMarca() {    
        return marca;
    }

    public String getTaglia() {    
        return taglia;
    }

    public double getPrezzo() {    
        return prezzo;
    }

    public String getDisponibile() {    
        return disponibile;
    }

    public void setDisponibile(String disponibile) {
        this.disponibile = disponibile;
    }
    @Override
    public String toString() {
        return "Capo{" +
               "id=" + id +
               ", dataInserimento='" + dataInserimento + '\'' +
               ", tipologia='" + tipologia + '\'' +
               ", marca='" + marca + '\'' +
               ", taglia='" + taglia + '\'' +
               ", prezzo='" + prezzo + '\'' +
               ", disponibile='" + disponibile + '\'' +
               '}';
    }
}
