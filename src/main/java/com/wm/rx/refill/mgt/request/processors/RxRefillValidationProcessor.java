package com.wm.rx.refill.mgt.request.processors;

import com.wm.rx.refill.mgt.request.constants.ProcessorStatus;
import com.wm.rx.refill.mgt.request.datasets.RxRequestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *  All Rx Refill get validated here.
 *  -- needs to be validated for multiple conditions
 *  a] Refill is permissible
 *  b] Doctor has approved etc
 *
 */
// Notes for now -we just send dummy assumptions -  validation is  clear and true;
@Component
@Slf4j
public class RxRefillValidationProcessor extends AbstractRxRefillRequestProcess {
  private static final String PROCESSOR_ID="RX-REFILL-VALIDATION-PROCESSOR ";
  @Override
  public RxProcessorStatus process(RxRequestData rxRequestData) {
    super.setUserRxRequestData(rxRequestData);
    log.debug("RxRefil Request --> Status Update {} initiated ",PROCESSOR_ID);

    RxProcessorStatus rxProcessorStatus = setRxProcessorStatus(
        ProcessorStatus.RX_REQUEST_VALIDATION_COMPLETED);
    log.debug("#============> RX-Refill:: Status Update {} completed ",PROCESSOR_ID);
    return rxProcessorStatus;
  }



}
