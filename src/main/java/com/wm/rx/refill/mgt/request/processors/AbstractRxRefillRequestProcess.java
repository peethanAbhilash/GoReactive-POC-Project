package com.wm.rx.refill.mgt.request.processors;

import com.wm.rx.refill.mgt.request.constants.ProcessorStatus;
import com.wm.rx.refill.mgt.request.datasets.RxRequestData;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Setter
public abstract class AbstractRxRefillRequestProcess {

  private RxRequestData rxRequestData;

  @Autowired
  private RxProcessorStatus rxProcessorStatus;

  /**
   * Processor steps defined by all subclasses.
   * Each processor required its own process methods here..
   *
   * @param rxRequestData
   * @return
   */

  /*
   * Learning Notes :   For the current process DRY methodology can be applied here - To Do it later
  *
  *
  * */
  public abstract RxProcessorStatus process(RxRequestData rxRequestData);

  /**
   * Sets the Request Data received as part of fresh refill request
   * @param rxRequestData
   */
  public void setUserRxRequestData(RxRequestData rxRequestData){
    log.debug("##============> RX-Refill::request received for =================> {}",rxRequestData);
    this.rxRequestData=rxRequestData;
    this.rxProcessorStatus=new RxProcessorStatus(rxRequestData.getUserId(),ProcessorStatus.RX_REQUEST_INITIATED.name());
  }

  /**
   * Tracking of the process stored in In-memory table
   * @param processorStatus
   * @return
   */
  protected RxProcessorStatus setRxProcessorStatus(ProcessorStatus processorStatus){
      log.debug("##============> RX-Refill::Setting process status now {}",processorStatus.name());
      rxProcessorStatus.setLatestRxProcessorStatus(processorStatus.name());
      return rxProcessorStatus;
  }

}
