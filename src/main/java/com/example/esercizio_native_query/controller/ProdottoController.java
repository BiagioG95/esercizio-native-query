package com.example.esercizio_native_query.controller;

import com.example.esercizio_native_query.entity.CategoriaEnum;
import com.example.esercizio_native_query.entity.Prodotto;
import com.example.esercizio_native_query.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.ProtectionDomain;
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

    @GetMapping("/cerca-per-id/{id}")
    public ResponseEntity<Optional<Prodotto>> cercaPerId(@PathVariable Long id){
        Optional<Prodotto> prodottoOptional = prodottoService.cercaPerId(id);
        if (prodottoOptional.isPresent()){
            return ResponseEntity.ok(prodottoOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search-categoria/{categoriaEnum}")
    public ResponseEntity<List<Prodotto>> searchProdottoByCategoria(@PathVariable CategoriaEnum categoriaEnum){
        List<Prodotto> list = prodottoService.searchCategoria(categoriaEnum);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search-nome-specifico")
    public ResponseEntity<List<Prodotto>> searchNomeSpecifico(@RequestParam String nome){
        List<Prodotto> list = prodottoService.searchNomeS(nome);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search-prezzo-minimo")
    public ResponseEntity<List<Prodotto>> searchPrezzoMinimo(@RequestParam Double prezzo){
        List<Prodotto> list = prodottoService.searchPrezzoMinimo(prezzo);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/order-prezzo")
    public ResponseEntity<List<Prodotto>> ordinaPrezzo(@RequestParam Double prezzo){
        List<Prodotto> list = prodottoService.orderByPrezzo(prezzo);
        return ResponseEntity.ok(list);
    }



}
