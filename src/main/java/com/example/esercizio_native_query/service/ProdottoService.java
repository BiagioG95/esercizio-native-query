package com.example.esercizio_native_query.service;

import com.example.esercizio_native_query.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;
}
