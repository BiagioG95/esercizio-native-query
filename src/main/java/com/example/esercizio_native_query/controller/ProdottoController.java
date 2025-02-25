package com.example.esercizio_native_query.controller;

import com.example.esercizio_native_query.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;
}
