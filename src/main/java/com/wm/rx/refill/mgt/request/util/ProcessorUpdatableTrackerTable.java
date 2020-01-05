package com.wm.rx.refill.mgt.request.util;

import com.wm.rx.refill.mgt.request.processors.RxProcessorStatus;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * The Rx refill  request process stage is tracked here in the in-memory Cache.
 * For the purpose we have used popular Expiring Cache as the component for in-memory Cache.
 * Reference : https://github.com/jhalterman/expiringmap/
 */
@Configuration
@Slf4j
public class ProcessorUpdatableTrackerTable {

  private ConcurrentHashMap<String, RxProcessorStatus> refillProcessorTrackerTable = new ConcurrentHashMap(100);



  public ConcurrentHashMap<String, RxProcessorStatus> getRefillProcessorTrackerTable(){
    return refillProcessorTrackerTable;
  }

  public String  updateProcessStatus(String userId,
      RxProcessorStatus userRefillProcessStatusUpdate){
        refillProcessorTrackerTable.put(userId,userRefillProcessStatusUpdate);
        String statusUpdate=String.format("Refill Status :: for the user id %s %s ",userId,refillProcessorTrackerTable.get(userId));
        log.debug("##============> RX-Refill:: Status for the user id {} {} ",userId,refillProcessorTrackerTable.get(userId));
        return statusUpdate;
  }

}
