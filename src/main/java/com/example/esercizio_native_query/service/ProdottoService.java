package com.example.esercizio_native_query.service;

import com.example.esercizio_native_query.entity.CategoriaEnum;
import com.example.esercizio_native_query.entity.Prodotto;
import com.example.esercizio_native_query.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;

    public Prodotto createProdotto(Prodotto prodotto){
        return prodottoRepository.save(prodotto);
    }

    public Prodotto deleteProdotto(Prodotto prodotto){
        prodottoRepository.delete(prodotto);
        return prodotto;

    }

    public List<Prodotto> getAllProdotti(){
        return prodottoRepository.findAll();
    }

    public Optional<Prodotto> updateProdotto(Long id, Prodotto updateProdotto){
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(id);
        if(prodottoOptional.isPresent()){
            prodottoOptional.get().setNome(updateProdotto.getNome());
            prodottoOptional.get().setDescrizione(updateProdotto.getDescrizione());
            prodottoOptional.get().setPrezzo(updateProdotto.getPrezzo());
            prodottoOptional.get().setCategoriaEnum(updateProdotto.getCategoriaEnum());
            prodottoOptional.get().setQuantitaDisponibile(updateProdotto.getQuantitaDisponibile());
            prodottoOptional.get().setDataCreazione(updateProdotto.getDataCreazione());
            Prodotto prodotto = prodottoRepository.save(prodottoOptional.get());
            return Optional.of(prodotto);
        } else{
            return Optional.empty();
        }
    }

    public Optional<Prodotto> cercaPerId(Long id) {
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(id);
        if (prodottoOptional.isPresent()) {
            return prodottoOptional;
        } else {
            return Optional.empty();
        }
    }

    //metodo per cercare una categoria specifica
    public List<Prodotto> searchCategoria(CategoriaEnum categoriaEnum){
        return prodottoRepository.findByCategoriaEnum(categoriaEnum);
    }

    // metodo che cerca prodotti il cui nome contiene una parola chiave
    public List<Prodotto> searchNomeS(String nome){
        return prodottoRepository.findByNomeContaining(nome);
    }

    //metodo per trovare prodotti con prezzo minore di un valore dato
    public List<Prodotto> searchPrezzoMinimo(Double prezzo){
        return prodottoRepository.findByPrezzoLessThan(prezzo);
    }

    // metodo che restituisce prodotti ordinati per prezzo
    public List<Prodotto> orderByPrezzo(Double prezzo){
        return prodottoRepository.findByPrezzoOrderByPrezzoDesc(prezzo);
    }

    // metodo he trova prodotti di una categoria con prezzo sotto una soglia
    public List<Prodotto> searchByCategoriaAndPrezzo(CategoriaEnum categoriaEnum, Double prezzo){
        return prodottoRepository.findByCategoriaEnumAndPrezzoLessThan(categoriaEnum, prezzo);
    }

    // query-nativa che conta quanti prodotti ci sono per categoria
    public Long countCategoria(CategoriaEnum categoriaEnum){
        return prodottoRepository.findByCategoriaEnumCount(categoriaEnum.name());
    }

    // query-native che trova i 5 prodotti con la maggiore quantità disponibile
    public List<Prodotto> top5QuantitaDisponibile(){
        return prodottoRepository.findByTop5ByQuantitaDisponibile();
    }

    // query nativa che trova i prodotti aggiunti nell'ultima settimana
    public List<Prodotto> prodottiUltimaSettimana(){
        return prodottoRepository.findByProdottoUltimaSettimana();
    }

    // query nativa che cerca prodotti il cui nome o descrizione contiene una parola chiave
    public List<Prodotto> prodottiOrDescrizione(String nome, String descrizione){
        return prodottoRepository.findByNomeOrDescrizione(nome, descrizione);
    }

    // query nativa che calcola il prezzo medio dei prodotti per ogni categoria
    public Double prezzoMedioByCategoria(CategoriaEnum categoriaEnum){
        return prodottoRepository.findPrezzoMedio(categoriaEnum.name());
    }

    // Livello medio custom query

    // Prodotti creati dopo una certa data
    public List<Prodotto> prodottiNuoviArrivi(LocalDate dataCreazione){
        return prodottoRepository.findByDataCreazioneAfter(dataCreazione);
    }

    // Prodotti con prezzo compreso in un intervallo
    public List<Prodotto> prodottiRangePrezzo(Double prezzoMinimo, Double prezzoMassimo){
        return prodottoRepository.findByPrezzoBetween(prezzoMinimo, prezzoMassimo);
    }

    // Prodotti ordinati in modo decrescente
    public List<Prodotto> decrescenteProdotti() {
        return prodottoRepository.findAllByOrderByDataCreazioneDesc();
    }












}
