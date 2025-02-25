package com.example.esercizio_native_query.entity;

public enum CategoriaEnum {
    ELETTRONICA("elettronica"),
    ABBIGLIAMENTO("abbigliamento"),
    ALIMENTARI("alimentari"),
    CASA("casa");

    private String descrizioneC;

    CategoriaEnum(String descrizioneC){this.descrizioneC = descrizioneC;}

    public String getDescrizioneC() {
        return descrizioneC;
    }

    public void setDescrizioneC(String descrizioneC) {
        this.descrizioneC = descrizioneC;
    }
}
