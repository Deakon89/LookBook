package com.lookbook.model;

public class Utente {
    private int id;
    private String nome;
    private String cognome;
    private String dataDiNascita;
    private String indirizzo;
    private String documentoID;

    /**
     * Costruisce un'istanza di Utente.
     *
     * @param id L'ID univoco dell'utente.
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param dataDiNascita La data di nascita dell'utente.
     * @param indirizzo L'indirizzo dell'utente.
     * @param documentoID L'ID del documento dell'utente.
     */
    public Utente(int id, String nome, String cognome, String dataDiNascita, String indirizzo, String documentoID) {
        if (id < 0) {
            throw new IllegalArgumentException("L'ID non può essere negativo.");
        }
        if (nome == null || nome.trim().isEmpty() || cognome == null || cognome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome e cognome non possono essere vuoti.");
        }
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.indirizzo = indirizzo;
        this.documentoID = documentoID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDocumentoId() {
        return documentoID;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoID = documentoId;
    }
    @Override
    public String toString() {
        return "Utente{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", cognome='" + cognome + '\'' +
               ", dataDiNascita='" + dataDiNascita + '\'' +
               ", indirizzo='" + indirizzo + '\'' +
               ", documentoID='" + documentoID + '\'' +
               '}';
    }
}

 