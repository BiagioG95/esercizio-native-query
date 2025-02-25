package com.example.esercizio_native_query.controller;

import com.example.esercizio_native_query.entity.Prodotto;
import com.example.esercizio_native_query.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @PostMapping("/create")
    public ResponseEntity<Prodotto> createProdotto(@RequestBody Prodotto prodotto){
        Prodotto creare = prodottoService.createProdotto(prodotto);
        return ResponseEntity.ok(creare);

    }

    @DeleteMapping("/delete")
    public Prodotto deleteProdotto(@RequestBody Prodotto prodotto){
        return prodottoService.deleteProdotto(prodotto);
    }
}
