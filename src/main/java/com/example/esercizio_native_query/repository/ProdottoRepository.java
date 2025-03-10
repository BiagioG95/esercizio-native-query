package com.example.esercizio_native_query.repository;

import com.example.esercizio_native_query.entity.CategoriaEnum;
import com.example.esercizio_native_query.entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "select * from prodotto order by quantita_disponibile asc limit 5", nativeQuery = true)
    List<Prodotto> findByTop5ByQuantitaDisponibile();

    @Query(value = "select * from prodotto where data_creazione >= current_date - interval 7 day", nativeQuery = true)
    List<Prodotto> findByProdottoUltimaSettimana();

    @Query(value = "select * from prodotto where lower(nome) like %?1% or lower(descrizione) like %?2%", nativeQuery = true)
    List<Prodotto> findByNomeOrDescrizione(String nome, String descrizione);

    @Query(value = "select avg(prezzo) AS prezzo_medio from prodotto where prodotto.categoria = ?1", nativeQuery = true)
    Double findPrezzoMedio(String categoriaEnum);




}


