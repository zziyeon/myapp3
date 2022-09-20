package com.kh.myapp3.web.interciptor;

import com.kh.myapp3.web.session.LoginOkConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LogIn_Interceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String requestURI = request.getRequestURI();
    log.info("인증체크={}", requestURI);

    //세션정보 있으면 가져오고 없으면 세션 생성하지 않음.
    HttpSession session = request.getSession(false);

    if (session == null || session.getAttribute(LoginOkConst.LOGIN_MEMBER) == null) {
      log.info("미인증 사용자 요청");

      // 로그인 화면으로 redirect
      response.sendRedirect("/login?requestURI" + requestURI);
      return false;
    }

    return true;
  }
}
