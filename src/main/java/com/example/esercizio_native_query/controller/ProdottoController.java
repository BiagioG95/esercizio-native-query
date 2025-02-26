package com.example.esercizio_native_query.controller;

import com.example.esercizio_native_query.entity.CategoriaEnum;
import com.example.esercizio_native_query.entity.Prodotto;
import com.example.esercizio_native_query.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/select-all")
    public List<Prodotto> getAllProdotti(){
        return prodottoService.getAllProdotti();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Prodotto> updateProdotto(@PathVariable Long id, @RequestBody Prodotto prodotto){
        Optional<Prodotto> updateProdotto = prodottoService.updateProdotto(id,prodotto);
        if(updateProdotto.isPresent()){
            return ResponseEntity.ok(updateProdotto.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search-categoria/{categoriaEnum}")
    public List<Prodotto> searchProdottoByCategoria(@PathVariable CategoriaEnum categoriaEnum){
        return prodottoService.searchCategoria(categoriaEnum);
    }

    @GetMapping("/search-nome-specifico")
    public List<Prodotto> searchNomeSpecifico(@RequestParam String nome){
        return prodottoService.searchNomeS(nome);
    }

}
