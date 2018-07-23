package com.personal.StockTracker;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
//import static com.github.tomakehurst.wiremock.client.WireMock.get;
//import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
//import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationTest")
public class StockTrackerApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089);


//	@Test
	public void testStockAPI() throws FileNotFoundException {
		stubFor(get("/1.0/stock/box/book")
				.willReturn(aResponse()
						.withStatus(HttpStatus.OK.value())
					//	.withBodyFile("classpath:response.txt")
						.withBodyFile("response.txt")
				));

		String response =
				testRestTemplate.getForObject("http://localhost:8089/1.0/stock/box/book",String.class);
		assertThat(response).isEqualTo("Hello, There");
	}

	@Test
	public void testStockPrice(){
		ResponseEntity<String> price =
			testRestTemplate.getForEntity("/stock/latest-price?sym=box",String.class);
		assertThat(price).isNotNull();
	}
}
