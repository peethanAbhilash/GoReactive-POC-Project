package com.wm.rx.refill.mgt.request.gateways.routerConfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
/**
 * To use later
 */
public class RxRefillRequestRouter {

  @Bean
  public RouterFunction<ServerResponse>  refillNewRequest(
      com.wm.rx.refill.mgt.request.gateways.routerHandlers.RxRefillRequestHandler rxRefillRequestRouterHandler){

    return null;

  }




}
