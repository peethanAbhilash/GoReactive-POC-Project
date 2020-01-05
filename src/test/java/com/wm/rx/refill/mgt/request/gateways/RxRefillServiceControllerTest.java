package com.wm.rx.refill.mgt.request.gateways;

import com.wm.rx.refill.mgt.RxRefillMagamentApplication;
import com.wm.rx.refill.mgt.request.constants.MessageConstants;
import com.wm.rx.refill.mgt.request.constants.URIConstants;
import com.wm.rx.refill.mgt.request.datasets.RxRequestData;
import com.wm.rx.refill.mgt.request.gateways.RxRefillServiceController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest()
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class RxRefillServiceControllerTest {

  @Autowired
  WebTestClient webTestClient;

  RxRequestData rxRequestData;


  @Before
  public void setUp() {
   rxRequestData = new RxRequestData("Test User Id", "Test Rx Id ", "Allergy Relief");
  }

  @Test
  public void exampleTest() {
    webTestClient.get().uri("/").exchange().expectStatus().isOk().expectBody(String.class).isEqualTo("Hello World");
  }

  @Test
  public void testAcceptNewRefillRequest() {
    RxRequestData rxRequestData=rxRequestData = new RxRequestData("Test User Id", "Test Rx Id ", "Allergy Relief");
    webTestClient.post().uri(URIConstants.callFreshRefillOrder_v1)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(rxRequestData,RxRequestData.class)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath(MessageConstants.DEFAULT_RESPONSE_MESSAGE);
  }


}
