package com.wm.rx.refill.mgt.request.processors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 *  Rx Refill Process status holder. The user can come anytime and get details from here.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
/**
 * Stores Process stages for each User Id here.
 */
public class RxProcessorStatus {

  private String userId;
  private String latestRxProcessorStatus;

}
