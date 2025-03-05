package com.example.esercizio_native_query;

import com.example.esercizio_native_query.controller.ProdottoController;
import com.example.esercizio_native_query.entity.CategoriaEnum;
import com.example.esercizio_native_query.entity.Prodotto;
import com.example.esercizio_native_query.service.ProdottoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.LessThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EsercizioNativeQueryApplicationTests {
    public static final double PREZZOMINIMO = 100.00;
    public static final double PREZZOMINIMOISEMPTY = 1.0;
    @Autowired
    private ProdottoController prodottoController;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProdottoService prodottoService;

    private Prodotto prodotto;
    private Prodotto prodottoNotFound;


    @BeforeEach
    public void setUp() {

        prodotto = new Prodotto();

        prodotto.setId(3L);

        prodotto.setNome("Test Prodotto");

        prodotto.setCategoriaEnum(CategoriaEnum.ELETTRONICA);

        prodotto.setPrezzo(99.00);

        prodotto.setDescrizione("Descrizione test");

        prodottoNotFound = new Prodotto();

        prodottoNotFound.setId(7L);

        prodottoNotFound.setNome("Test Prodotto0");

        prodottoNotFound.setCategoriaEnum(CategoriaEnum.ELETTRONICA);

        prodottoNotFound.setPrezzo(95.00);

        prodottoNotFound.setDescrizione("Descrizione test0");
    }





    @Test
    public void testCreateProdotto() throws Exception {
        // Step 1: prima di testare andiamo a memorizzare un prodotto di test utilizzando direttamente il service
        // per salvare qualsiasi oggetto si utilizza il when con questa chiamata
        when(prodottoService.createProdotto(any(Prodotto.class))).thenReturn(prodotto);
        // Step 2: tramite MockMvc andiamo a richiamare il controller usando il metodo create e controlliamo che il
        // risultato del controllerCreate sia uguale al risultato dell'oggetto di test che abbiamo memorizzato in precedenza
        mockMvc.perform(post("/prodotto/create")// chiamo l'URL del controller
                        .contentType(MediaType.APPLICATION_JSON) // setto il tipo
                        .content(objectMapper.writeValueAsString(prodotto))) // controlliamo che il Json di ritorno sia uguale
                // al json dell'oggetto che abbiamo creato
                .andDo(print())
                .andExpect(status().isOk())// controlliamo che il codice status sia ok
                .andExpect(jsonPath("$.nome").value("Test Prodotto")); // controllo opzionale

    }

    @Test
    public void testGetAllProdotti() throws Exception {
        when(prodottoService.getAllProdotti()).thenReturn(Collections.singletonList(prodotto));

        mockMvc.perform(get("/prodotto/select-all"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateProdottoOk() throws Exception {
        when(prodottoService.updateProdotto(anyLong(), any(Prodotto.class))).thenReturn(Optional.of(prodotto));

        mockMvc.perform(put("/prodotto/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Test Prodotto"));

    }

    @Test
    public void testUpdateProdottoNotFound() throws Exception {
        when(prodottoService.updateProdotto(anyLong(), any(Prodotto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/prodotto/update/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodottoNotFound)))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteProdotto() throws Exception {
        when(prodottoService.deleteProdotto(any(Prodotto.class))).thenReturn(prodotto);

        mockMvc.perform(delete("/prodotto/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Test Prodotto"));

    }

    @Test
    public void testCercaPerIdOk() throws Exception {
        when(prodottoService.cercaPerId(anyLong())).thenReturn(Optional.of(prodotto));
        mockMvc.perform(get("/prodotto/cerca-per-id/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Test Prodotto"));
    }

    @Test
    public void testCercaPerIdNotFound() throws Exception{
        when(prodottoService.cercaPerId(anyLong())).thenReturn(Optional.empty());
        mockMvc.perform(get("/prodotto/cerca-per-id/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto)))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void testSearchCategoriaIsOk() throws Exception {

        CategoriaEnum categoria = CategoriaEnum.ELETTRONICA;

        when(prodottoService.searchCategoria(categoria)).thenReturn(Collections.singletonList(prodotto));

        mockMvc.perform(get("/prodotto/search-categoria/ELETTRONICA"))
                .andDo(print())
                .andExpect(status().isOk())
                //Se ci sono spazi nascosti, equalToIgnoringWhiteSpace() li ignora; ho inserito questo perché sia il valore che si aspettava che quello attuale era uguale
                // Matchers(libreria)fornisce metodi per confrontare i valori nei test unitari in modo più flessibile
                .andExpect(jsonPath("$[0].categoriaEnum").value(Matchers.equalToIgnoringWhiteSpace(categoria.name())));

    }

    @Test
    public void testSearchCategoriaIsEmpty() throws Exception{
        CategoriaEnum categoria = CategoriaEnum.ELETTRONICA;
        when(prodottoService.searchCategoria(categoria)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/prodotto/search-categoria/ELETTRONICA"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));



    }

    @Test
    public void testSearchNomeSpecificoIsOk() throws Exception{
        when(prodottoService.searchNomeS("Test Prodotto")).thenReturn(Collections.singletonList(prodotto));
        mockMvc.perform(get("/prodotto/search-nome-specifico")
                        .param("nome", "Test Prodotto"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value(Matchers.equalToIgnoringWhiteSpace("Test Prodotto")));
    }

    @Test
    public void testSearchNomeSpecificoIsEmpty() throws Exception{
        when(prodottoService.searchNomeS("Test Prodotto")).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/prodotto/search-nome-specifico")
                        .param("nome", "Test Prodotto"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

    }

    // dobbiamo testare che il primo elemento della lista sia minore del prezzo che inseriamo
    // per fare questo dobbiamo confrontare che il primo valore della lista sia minore della costante PREZZOMINIMO
    @Test
    public void testSearchPrezzoMinimoIsOK() throws Exception{
        when(prodottoService.searchPrezzoMinimo(PREZZOMINIMO)).thenReturn(Collections.singletonList(prodotto));
        mockMvc.perform(get("/prodotto/search-prezzo-minimo")
                        .param("prezzo", String.valueOf(PREZZOMINIMO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].prezzo").value(Matchers.lessThan(PREZZOMINIMO)));
    }

    @Test
    public void testSearchPrezzoMinimoIsEmpty() throws Exception {
        // Simuliamo un risultato vuoto da prodottoService
        when(prodottoService.searchPrezzoMinimo(PREZZOMINIMO)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/prodotto/search-prezzo-minimo")
                        .param("prezzo", String.valueOf(PREZZOMINIMO)))
                .andDo(print())
                .andExpect(status().isOk())
                //in questo caso andiamo a verificare che la lunghezza della lista sia vuota
                .andExpect(jsonPath("$.length()").value(0));
    }


    @Test
    public void testOrderByPrezzoIsOk() throws Exception{
        when(prodottoService.orderByPrezzo(100.00)).thenReturn(Arrays.asList(prodotto, prodottoNotFound));
        mockMvc.perform(get("/prodotto/order-prezzo")
                .param("prezzo", "100.00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").value(7L))
                .andExpect(jsonPath("$[0].id").value(3L));

    }

    @Test
    public void testOrderByPrezzoIsEmpty() throws Exception{
        when(prodottoService.orderByPrezzo(100.00)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/prodotto/order-prezzo")
                        .param("prezzo", "100.00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));


    }








}
