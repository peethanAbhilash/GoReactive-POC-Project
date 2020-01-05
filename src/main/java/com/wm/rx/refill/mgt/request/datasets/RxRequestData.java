package com.wm.rx.refill.mgt.request.datasets;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
/**
 *  Request Data Place holder.
 */

public class RxRequestData {

  @NotBlank(message = "400.RxFL.InBND.001.ERR- Userid is a mandatory field.")
  @Pattern(regexp="^(0|[1-9][0-9]*)$", message="Only numeric allowed in User Id field.")
  String userId;
  /**
   * Rx Id contains refill rx details
   */
  String rxId;

  /*
      Rx Id
   */
  String refilRequestDetails;
}
