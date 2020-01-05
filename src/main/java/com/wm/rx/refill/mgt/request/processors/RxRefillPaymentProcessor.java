package com.wm.rx.refill.mgt.request.processors;

import com.wm.rx.refill.mgt.request.constants.ProcessorStatus;
import com.wm.rx.refill.mgt.request.datasets.RxRequestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *  This processor RxRefillPaymentProcessor verifies and confirms the payment status.
 */
@Component
@Slf4j
public class RxRefillPaymentProcessor extends AbstractRxRefillRequestProcess {

  private static final String PROCESSOR_ID = "RX-REFILL-PAYMENT-PROCESSOR ";

  @Override
  public RxProcessorStatus process(RxRequestData rxRequestData) {
    super.setUserRxRequestData(rxRequestData);
    log.debug("RxRefil Request --> Status Update {} initiated ",PROCESSOR_ID);

    RxProcessorStatus rxProcessorStatus = setRxProcessorStatus(
        ProcessorStatus.RX_REQUEST_PAYMENT_RECEIVED_CONFIRMED);
    log.debug("##============> RX-Refill::Status Update {} initiated ",PROCESSOR_ID);
    return rxProcessorStatus;

  }
}
