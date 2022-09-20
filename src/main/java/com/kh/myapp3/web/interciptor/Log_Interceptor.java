package com.kh.myapp3.web.interciptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class Log_Interceptor implements HandlerInterceptor {

  public static final String TRANSACITION_ID="transactionId";

  //컨트롤러 호출 전
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.info("preHandle 호출!");
    String requestURI = request.getRequestURI();    //클라이언트의 요청 url

//    UUID uuid = UUID.randomUUID();
    String uuid = UUID.randomUUID().toString();
    request.setAttribute(TRANSACITION_ID, uuid);

    log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);
    return true;
  }

  //컨트롤러 수행 후 view가 랜더링 되기 전
  //컨트롤러에서 예외 발생시 호출되지 않음
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.info("postHandle 호출!");
    String requestURI = request.getRequestURI();      //클라이언트의 요청 url
    String uuid = (String)request.getAttribute(TRANSACITION_ID);
//    String uuid = String.valueOf(request.getAttribute(TRANSACITION_ID));
    log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);
  }

  //view가 랜더링되고 응답메시지가 클라이언트에 전송된 후
  //컨트롤러에서 예외 발생 유무에 상관없이 항상 호출됨
  //모델과 view에 대한건 가지고 있을 수 없다.
  //Exception은 예외가 발생했을시 잡을 수 있다.
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    log.info("afterCompletion 호출!");
    String requestURI = request.getRequestURI();      //클라이언트의 요청 url
    String uuid = (String) request.getAttribute(TRANSACITION_ID);
    log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);


    if (ex != null) {
      log.error("afterCompletion error !!", ex);
    }
  }
}
