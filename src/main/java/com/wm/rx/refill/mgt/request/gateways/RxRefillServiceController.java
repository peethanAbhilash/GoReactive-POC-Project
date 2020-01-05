package com.wm.rx.refill.mgt.request.gateways;

import static com.wm.rx.refill.mgt.request.constants.URIConstants.callFreshRefillOrder_v1;

import com.wm.rx.refill.mgt.request.datasets.RxRequestData;
import com.wm.rx.refill.mgt.request.processors.ProcessManager;
import com.wm.rx.refill.mgt.request.util.TrackTime;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@Validated
/**
 * Gate Way for APIs
 */
public class RxRefillServiceController {

  @Autowired
  ProcessManager rxRefillRequestManager;

  @PostMapping(callFreshRefillOrder_v1)
  @TrackTime
  public Mono<String> acceptNewRefillRequest(@Valid @RequestBody RxRequestData rxRequestData) {
    log.debug("Refill Request received ");

    Mono<String> refillStatus = Mono
        .just(rxRefillRequestManager.startRefillProcess(rxRequestData))
        .onErrorReturn("Sorry your request cannot be processed now");
    return refillStatus;

  }

}
