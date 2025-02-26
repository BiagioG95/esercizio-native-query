package com.example.esercizio_native_query.service;

import com.example.esercizio_native_query.entity.Prodotto;
import com.example.esercizio_native_query.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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








}
