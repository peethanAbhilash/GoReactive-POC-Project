package com.wm.rx.refill.mgt.request.processors;

import com.wm.rx.refill.mgt.request.constants.ProcessorStatus;
import com.wm.rx.refill.mgt.request.datasets.RxRequestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
/**
 *  Processing stage for RxRefillCompletedProcessor is defined here 
 */
public class RxRefillCompletedProcessor extends AbstractRxRefillRequestProcess {
  private static final String PROCESSOR_ID = "RX-REFILL-COMPLETED-PROCESSOR ";

  /**
   * @see AbstractRxRefillRequestProcess#process(RxRequestData) 
   * @param rxRequestData
   * @return
   */
  @Override
  public RxProcessorStatus process(RxRequestData rxRequestData) {
    super.setUserRxRequestData(rxRequestData);
    log.debug("RxRefil Request --> Status Update {} initiated ",PROCESSOR_ID);

    RxProcessorStatus rxProcessorStatus = setRxProcessorStatus(
        ProcessorStatus.RX_REQUEST_COMPLETED);
    log.debug("#============> RX-Refill::  Status Update {} initiated ",PROCESSOR_ID);
    return rxProcessorStatus;

  }
}
