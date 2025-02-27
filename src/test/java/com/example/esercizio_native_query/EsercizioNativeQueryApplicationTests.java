package com.example.esercizio_native_query;

import com.example.esercizio_native_query.controller.ProdottoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
