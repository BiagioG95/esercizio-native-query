package com.example.esercizio_native_query.repository;

import com.example.esercizio_native_query.entity.CategoriaEnum;
import com.example.esercizio_native_query.entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    List<Prodotto> findByCategoriaEnum(CategoriaEnum categoriaEnum);
    List<Prodotto> findByNomeContaining(String nome);
    List<Prodotto> findByPrezzoLessThan(Double prezzo);
    List<Prodotto> findByPrezzoOrderByPrezzoDesc(Double prezzo);
    List<Prodotto> findByCategoriaEnumAndPrezzoLessThan(CategoriaEnum categoriaEnum, Double prezzo);

    @Query(value = "select count(*) from prodotto  where prodotto.categoria = ?1", nativeQuery = true)
    Long findByCategoriaEnumCount(String categoriaEnum);


}


