package com.wm.rx.refill.mgt.request.gateways.routerHandlers;

import com.wm.rx.refill.mgt.request.datasets.RxRequestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
/**
 * To use later
 */
public class RxRefillRequestHandler {

  @Autowired
  RxRequestDataRepository rxRequestDataRepository;

  public Mono<ServerResponse> refillRequest(ServerRequest serverRequest) {

    return null;

  }


}
