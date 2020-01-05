package com.wm.rx.refill.mgt.request.processors;

import com.wm.rx.refill.mgt.request.constants.MessageConstants;
import com.wm.rx.refill.mgt.request.datasets.RxRequestData;
import com.wm.rx.refill.mgt.request.util.ProcessorUpdatableTrackerTable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Processor Manager controls the list of process from the top level. It got a list of processors -
 * stream each process and update in parallel the RxRequestStatus for each process.
 */
@Component
@Slf4j
public class ProcessManager {

  private List<AbstractRxRefillRequestProcess> listOfRefillRequestProcessors =
      Arrays.asList(
          new RxRefillValidationProcessor()
          , new RxRefillPaymentProcessor()
          , new RxRefillMedicinePrepProcessor()
          , new RxRefillCompletedProcessor()
      );

  @Autowired
  private RxRequestData rxRequestData;

  @Autowired
  private ProcessorUpdatableTrackerTable trackerTable;
  /**
   * Function defined for processing each Processor
   */
  private Function<AbstractRxRefillRequestProcess, RxProcessorStatus> processRxRefillFunction =
      (rxProcessor) -> rxProcessor.process(rxRequestData);

  /**
   * Function defined for tracking process status after each process completes the task
   */
  private Function<RxProcessorStatus, String> updateProcessStatus =
      (rxProcessorStatus -> trackerTable.updateProcessStatus(rxProcessorStatus.getUserId(),
          rxProcessorStatus));


  /**
   * Core function where in fresh refill process is started.
   * @param rxRequestData
   * @return
   */
  public String startRefillProcess(RxRequestData rxRequestData) {
    log.debug("Rx-Refil request==================>  startRefillProcess - Stage identified {}",
        listOfRefillRequestProcessors.size());
    this.rxRequestData = rxRequestData;

    listOfRefillRequestProcessors
        .stream()
        .map(processRxRefillFunction.andThen(updateProcessStatus))
        .forEachOrdered(result->log.info(result));

    return MessageConstants.DEFAULT_RESPONSE_MESSAGE;
  }


}
