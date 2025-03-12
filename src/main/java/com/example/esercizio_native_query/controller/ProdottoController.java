package com.example.esercizio_native_query.controller;

import com.example.esercizio_native_query.entity.CategoriaEnum;
import com.example.esercizio_native_query.entity.Prodotto;
import com.example.esercizio_native_query.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/search-categoria-prezzo")
    public ResponseEntity<List<Prodotto>> searchByCategoriaAndPrezzo(@RequestParam CategoriaEnum categoriaEnum, @RequestParam Double prezzo){
        List<Prodotto> list = prodottoService.searchByCategoriaAndPrezzo(categoriaEnum, prezzo);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/count-categoria")
    public ResponseEntity<Long> countCategoria(@RequestParam CategoriaEnum categoriaEnum){
        Long list = prodottoService.countCategoria(categoriaEnum);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/top5-disponibili")
    public ResponseEntity<List<Prodotto>> top5QuantitaDisponibile(){
        List<Prodotto> list = prodottoService.top5QuantitaDisponibile();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/recenti")
    public ResponseEntity<List<Prodotto>> prodottiUltimaSettimana(){
        List<Prodotto> list = prodottoService.prodottiUltimaSettimana();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/nome-or-descrizione")
    public ResponseEntity<List<Prodotto>> prodottiOrDescrizione(@RequestParam String nome, @RequestParam String descrizione){
        List<Prodotto> list = prodottoService.prodottiOrDescrizione(nome, descrizione);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/prezzo-medio-per-categoria")
    public ResponseEntity<Double> prezzoMedioCategoria(@RequestParam CategoriaEnum categoriaEnum){
       Double prezzoMedio = prodottoService.prezzoMedioByCategoria(categoriaEnum);
        return ResponseEntity.ok(prezzoMedio);
    }

    // Livello medio custom query

    // Prodotti creati dopo una certa data
    @GetMapping("/nuovi-arrivi")
    public ResponseEntity<List<Prodotto>> nuoviArrivi(@RequestParam LocalDate dataCreazione){
        List<Prodotto> prodottoList = prodottoService.prodottiNuoviArrivi(dataCreazione);
        return ResponseEntity.ok(prodottoList);
    }

    // range prezzo
    @GetMapping("/range-prezzo")
    public ResponseEntity<List<Prodotto>> rangePrezzo(@RequestParam Double prezzoMinimo, @RequestParam Double prezzoMassimo){
        List<Prodotto> prodottoList = prodottoService.prodottiRangePrezzo(prezzoMinimo, prezzoMassimo);
        return ResponseEntity.ok(prodottoList);
    }

    // Prodotti ordinati in modo decrescente
    @GetMapping("/prodotti-decrescente")
    public ResponseEntity<List<Prodotto>> decrescenteProdotti(){
        List<Prodotto> list = prodottoService.decrescenteProdotti();
        return ResponseEntity.ok(list);
    }

    // test per provare delete con void passando id
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteProdottoById(@PathVariable Long id) {
        prodottoService.deleteProdottoById(id);
    }

    // Prodotti di una categoria specifica ordinati per prezzo
    @GetMapping("/categoria-order-by-prezzo")
    public ResponseEntity<List<Prodotto>> categoriaOrderByPrezzoAsc(@RequestParam CategoriaEnum categoriaEnum){
        List<Prodotto> list = prodottoService.categoriaOrderByPrezzoAsc(categoriaEnum);
        return ResponseEntity.ok(list);
    }



}
