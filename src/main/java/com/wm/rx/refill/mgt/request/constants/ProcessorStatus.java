package com.wm.rx.refill.mgt.request.constants;

/**
 * Connstants used for tracking and auditing Process status
 */
public enum ProcessorStatus {
  RX_REQUEST_INITIATED,
  RX_REQUEST_VALIDATION_COMPLETED,
  RX_REQUEST_IN_MAKING,
  RX_REQUEST_INVALID_REFIILL,
  RX_REQUEST_PAYMENT_RECEIVED_CONFIRMED,
  RX_DOCTOR_RE_VERIFICATION_COMPLETED,
  RX_REQUEST_COMPLETED;

}
