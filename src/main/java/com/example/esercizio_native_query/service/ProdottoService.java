package com.example.esercizio_native_query.service;

import com.example.esercizio_native_query.entity.CategoriaEnum;
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












}
