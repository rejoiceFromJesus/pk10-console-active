package com.pk10.active.console.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pk10.active.console.common.bean.Result;
/**
* @ClassName: GlobalDefaultExceptionHandler 
* @Description: 全局异常处理
* @author rejoice  948870341@qq.com 
* @date 2016年9月27日 下午5:07:48 
*
 */
@ControllerAdvice
class GlobalExceptionHandler {
  public static final String DEFAULT_ERROR_VIEW = "error";
  private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
  
  @ExceptionHandler(value={InvalidParamException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Result paramError(HttpServletRequest req, Exception e) throws Exception {
	  logger.error("exception handler, exception occurs:",e);
	  return Result.paramError(e.getMessage());
  }
  
  @ExceptionHandler(value={Exception.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Result serverError(HttpServletRequest req, Exception e) throws Exception {
	  logger.error("exception handler, exception occurs:",e);
	  return Result.serverError(null);
  }
  
}