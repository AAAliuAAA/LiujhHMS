package com.smepms.common.exception;

/**
 * service异常处理（回滚）
 */
public class BizRuntimeException extends RuntimeException{
  public BizRuntimeException(String message) {
    super(message);
  }

  public BizRuntimeException(Throwable cause) {
    super(cause);
  }

  public BizRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }
}
