package com.wm.rx.refill.mgt.request.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static net.logstash.logback.marker.Markers.append;

@RestControllerAdvice
@RestController
@Slf4j
/**
 * Global Exception Handler class
 */
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String CONSTRAINT_EXCEPTION_HEADER = "Constraint Violation Exception in BSS Request Input ";
  private static final String METHOD_ARGS_INVALID_EXCEPTION_HEADER = "400.RX-RFL-REQ.SEV1.ERR.01  RX-refill-request process stopped ";
  private static final String RX_RFL_REQUEST_UNEXPECTED_ERROR= "400.RX-RFL-REQ.SEV1.02  RX-refill-request process stopped.";
  private static final String RX_RFL_REQUEST = "400.RX-RFL-REQ.SEV1.03  RX-refill-requestProcessStopped. Service unreachable or may be down ";


  /**
   * Handles all generic exceptions her
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllUnknownException(Exception ex, WebRequest request)
      throws Exception {
    StringBuilder errorMessageCollection = new StringBuilder("RX-RFL service issues detected ");
    return getErrorResponseEntity(errorMessageCollection, HttpStatus.INTERNAL_SERVER_ERROR,
        RX_RFL_REQUEST_UNEXPECTED_ERROR, request);
  }


  /**
   * Handles unexpected error cases
   *
   * @param ex
   * @param webRequest
   * @return
   */

  @ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class})
  public ResponseEntity<Object> handleNoHandlerFoundException(HttpClientErrorException ex,
      WebRequest webRequest) {

    String errorPayload = ex.getResponseBodyAsString().replaceAll("\\\\", "");

    String errorHeader = "RX-RFL_REQUES_FAILED";

    if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
      errorHeader = RX_RFL_REQUEST_UNEXPECTED_ERROR;
    }

    StringBuilder errorMessageCollection = new StringBuilder("Please reach out RX-RFL support team");
    errorMessageCollection.append(errorPayload);

    return getErrorResponseEntity(errorMessageCollection, ex.getStatusCode(), errorHeader,
        webRequest);
  }


  /**
   * Invalid Method Args arguments exception handling
   */
  @Override
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    BindingResult errorResult = ex.getBindingResult();
    StringBuilder errorMessageCollection = new StringBuilder("");

    errorMessageCollection
        .append(errorResult.getAllErrors().stream().map(error -> error.getDefaultMessage())
            .collect(Collectors.joining(",\n")));

    return getErrorResponseEntity(errorMessageCollection, HttpStatus.BAD_REQUEST,
        METHOD_ARGS_INVALID_EXCEPTION_HEADER, request);
  }

  /**
   * ConstraintViolationException handling.
   *
   * @return HttpStatus.BAD_REQUEST
   */

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConstraintViolationExceptions
  (ConstraintViolationException ex, HttpServletResponse response, WebRequest request) {

    StringBuilder errorMessageCollection = new StringBuilder();
    errorMessageCollection
        .append(ex.getConstraintViolations().stream().map(error -> error.getMessage())
            .collect(Collectors.joining(",")));
    return getErrorResponseEntity(errorMessageCollection, HttpStatus.BAD_REQUEST,
        CONSTRAINT_EXCEPTION_HEADER, request);
  }


  private ResponseEntity getErrorResponseEntity(StringBuilder errorMessageCollection,
      HttpStatus httpStatusCode, String errorHeader, WebRequest request) {

    RxRefilInfoDataHolder genericExceptionResponse = new RxRefilInfoDataHolder
        ("400", "400.RX-RFL.ERR", errorHeader,
            errorMessageCollection.toString(),
            LocalDateTime.now().toString());
    return new ResponseEntity(genericExceptionResponse, httpStatusCode);
  }


}
