package com.smepms.common.util.exceptionhandler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * GlobalExceptionHandler
 *
 * @author liujh
 * @since 2018/4/28
 */
@Component
@Aspect
public class GlobalExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
  @Pointcut("execution(* com.smepms.*(..))")
  private void globalExceptionPointCut(){}

  @AfterThrowing(value = "globalExceptionPointCut()",throwing = "throwable")
  public void exceptionCatch(Throwable throwable){

    logger.info("全局异常捕获",throwable);
  }


}
