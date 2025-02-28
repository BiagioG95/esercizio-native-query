package com.example.esercizio_native_query;

import com.example.esercizio_native_query.controller.ProdottoController;
import com.example.esercizio_native_query.entity.CategoriaEnum;
import com.example.esercizio_native_query.entity.Prodotto;
import com.example.esercizio_native_query.service.ProdottoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EsercizioNativeQueryApplicationTests {
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


	@BeforeEach
	public void setUp() {

		prodotto = new Prodotto();

	//	prodotto.setId(1L);

		prodotto.setNome("Test Prodotto");

		prodotto.setCategoriaEnum(CategoriaEnum.ELETTRONICA);

		prodotto.setPrezzo(100.0);

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
	void contextLoads() {
		assertThat(prodottoController).isNotNull();
	}

	@Test
	void restTemplateTest() {
		// this perché abbiamo fatto autowired
		String output = this.testRestTemplate.getForObject("http://localhost:" + port + "/prodotto" + "/saluto", String.class);
		assertThat(output).contains("Hello, world");

	}

	@Test
	public void testMockMvc() throws Exception {
		this.mockMvc.perform(get("/prodotto/saluto")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, world")));
	}




}
