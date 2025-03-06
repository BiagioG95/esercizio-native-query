package com.example.esercizio_native_query.entity;

public enum CategoriaEnum {
    ELETTRONICA("elettronica"),
    ABBIGLIAMENTO("abbigliamento"),
    ALIMENTARI("alimentari"),
    CASA("casa");

    private final String descrizione;

    CategoriaEnum(String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }


}
