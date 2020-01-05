package com.wm.rx.refill.mgt.request.processors;

import com.wm.rx.refill.mgt.request.constants.ProcessorStatus;
import com.wm.rx.refill.mgt.request.datasets.RxRequestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
@Slf4j
/**
 * Processing steps for RxRefillMedicinePrepProcessor is defined here
 */
public class RxRefillMedicinePrepProcessor extends AbstractRxRefillRequestProcess {

  private static final String PROCESSOR_ID = "RX-REFILL-MEDICINE_PREP-PROCESSOR ";


  /**
   * @param rxRequestData
   * @return
   * @see AbstractRxRefillRequestProcess#process(RxRequestData)
   */
  @Override
  public RxProcessorStatus process(RxRequestData rxRequestData) {
    super.setUserRxRequestData(rxRequestData);
    log.debug("RxRefil Request --> Status Update {} initiated ", PROCESSOR_ID);

    RxProcessorStatus rxProcessorStatus = setRxProcessorStatus(
        ProcessorStatus.RX_REQUEST_IN_MAKING);
    log.debug("#============> RX-Refill::  Status Update {} initiated ", PROCESSOR_ID);
    return rxProcessorStatus;

  }
}
