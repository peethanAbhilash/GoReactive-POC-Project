package com.wm.rx.refill.mgt.request.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Customized Exception Response
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 * Class holder for sending custom and organised message
 */
public class RxRefilInfoDataHolder {

    private String statusCode;
    private String status;
    private String message;
    private String details;
    private String requestDate;

}
