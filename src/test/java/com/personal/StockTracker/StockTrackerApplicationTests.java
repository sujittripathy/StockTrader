package com.personal.StockTracker;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
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

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
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
	public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.wireMockConfig().port(8089));

	@Before
	public void setUp() {
		stubFor(get("/1.0/stock/box/book")
				.willReturn(aResponse()
						.withHeader("content-type", "application/json")
						.withStatus(HttpStatus.OK.value())
						.withBody("21.71")));
	}

	@Test
	public void testStockPrice(){
		ResponseEntity<String> price =
			testRestTemplate.getForEntity("/stock/latest-price?sym=box",String.class);
		//	assertThat(StringUtils.isNumber(price.getBody())).isTrue();
	}
}
