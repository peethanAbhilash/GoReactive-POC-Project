package com.wm.rx.refill.mgt.processor;

import com.wm.rx.refill.mgt.request.datasets.RxRequestData;
import com.wm.rx.refill.mgt.request.processors.AbstractRxRefillRequestProcess;
import com.wm.rx.refill.mgt.request.processors.RxProcessorStatus;
import com.wm.rx.refill.mgt.request.processors.RxRefillCompletedProcessor;
import com.wm.rx.refill.mgt.request.processors.RxRefillMedicinePrepProcessor;
import com.wm.rx.refill.mgt.request.processors.RxRefillPaymentProcessor;
import com.wm.rx.refill.mgt.request.processors.RxRefillValidationProcessor;
import com.wm.rx.refill.mgt.request.util.ProcessorUpdatableTrackerTable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")

public class TestProcessorCoreFunctionalities {

  public List<AbstractRxRefillRequestProcess> listOfRefillRequestProcessors;
  public RxRequestData rxRequestData;

  @Autowired
  private ProcessorUpdatableTrackerTable trackerTable;
//
//  @Autowired
//  ProcessManager processManager;

  @Before
  public void initSetUp() {
    listOfRefillRequestProcessors =
        Arrays.asList(
            new RxRefillValidationProcessor()
            , new RxRefillPaymentProcessor()
            , new RxRefillMedicinePrepProcessor()
            , new RxRefillCompletedProcessor()
        );

    rxRequestData = new RxRequestData("userid-1", "Rx-id-1", "Fever Med");

  }

  Function<AbstractRxRefillRequestProcess, RxProcessorStatus> rxRefillFunction =
      (rxProcessor) -> rxProcessor.process(rxRequestData);

  Function<RxProcessorStatus, String> updateProcessStatus =
      (rxProcessorStatus -> trackerTable.updateProcessStatus(rxProcessorStatus.getUserId(),
          rxProcessorStatus));

  @Test
  public void testDirectVanillaProcessCall_v0_SingleProcess() {
    listOfRefillRequestProcessors
        .stream()
        .map(proccssor -> proccssor.process(rxRequestData))
        .forEach(System.out::println);
  }


  @Test
  public void testFunctionalCombineUpdateCall_v1() {
    listOfRefillRequestProcessors
        .stream()
        .map(proccssor -> proccssor.process(rxRequestData))
        .map(updateProcessStatus)
        .forEachOrdered(System.out::println);

  }

  @Test
  public void testFunctionalCombineUpdateCall_v2_FunctionalCompose() {
    listOfRefillRequestProcessors
        .stream()
        .map(rxRefillFunction)
        .map(updateProcessStatus)
        .forEachOrdered(System.out::println);

  }

  @Test
  public void testFunctionalCombineUpdateCall_v3_FunctionalAndThenCompose() {
    listOfRefillRequestProcessors
        .stream()
        .map(rxRefillFunction.andThen(updateProcessStatus))
        .forEachOrdered(System.out::println);
  }



}
