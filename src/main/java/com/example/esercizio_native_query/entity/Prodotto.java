package com.example.esercizio_native_query.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prodotto")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "prezzo")
    private Double prezzo;
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoriaEnum;
    @Column(name = "quantita_disponibile")
    private Integer quantitaDisponibile;
    @Column(name = "data_creazione")
    private LocalDate dataCreazione;

    public Prodotto() {
    }

    public Prodotto(Long id, String nome, String descrizione, Double prezzo, CategoriaEnum categoriaEnum, Integer quantitaDisponibile, LocalDate dataCreazione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.categoriaEnum = categoriaEnum;
        this.quantitaDisponibile = quantitaDisponibile;
        this.dataCreazione = dataCreazione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public CategoriaEnum getCategoriaEnum() {
        return categoriaEnum;
    }

    public void setCategoriaEnum(CategoriaEnum categoriaEnum) {
        this.categoriaEnum = categoriaEnum;
    }

    public Integer getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    public void setQuantitaDisponibile(Integer quantitaDisponibile) {
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
}
