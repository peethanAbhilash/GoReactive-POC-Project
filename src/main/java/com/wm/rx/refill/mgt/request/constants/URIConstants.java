package com.wm.rx.refill.mgt.request.constants;

import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * URI Constants to store the different request URIs
 */
public class URIConstants {

  public static final String callFreshRefillOrder_v1="/v1/rx/refill/new";
  public static final String getRefillUpdate_v1="/v1/rx/refill/status/{id}";

}
