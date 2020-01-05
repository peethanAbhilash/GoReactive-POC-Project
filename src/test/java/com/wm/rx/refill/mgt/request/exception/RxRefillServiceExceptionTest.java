package com.wm.rx.refill.mgt.request.exception;

import com.wm.rx.refill.mgt.request.constants.MessageConstants;
import com.wm.rx.refill.mgt.request.constants.URIConstants;
import com.wm.rx.refill.mgt.request.datasets.RxRequestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest()
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class RxRefillServiceExceptionTest {

  @Autowired
  WebTestClient webTestClient;


  @Test
  public void testGlobalExceptionInRequest() {
    RxRequestData rxRequestData=rxRequestData = new RxRequestData("", "Test Rx Id ", "Allergy Relief");
    webTestClient.post().uri(URIConstants.callFreshRefillOrder_v1)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(rxRequestData,RxRequestData.class)
        .exchange()
        .expectStatus().is5xxServerError()
        .expectBody(String.class)
        .consumeWith(System.out::println);

  }


}
