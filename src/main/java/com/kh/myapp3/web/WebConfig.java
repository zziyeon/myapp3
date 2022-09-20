package com.kh.myapp3.web;

import com.kh.myapp3.web.interciptor.LogIn_Interceptor;
import com.kh.myapp3.web.interciptor.Log_Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  //인터셉터 등록

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //모든 요청에 대한 log
    registry.addInterceptor(new Log_Interceptor())
            .order(1)
            .addPathPatterns("/**")       //**: 모든 하위 경로    블랙리스트_기본
            .excludePathPatterns("/error");        //화이트리스트_

    //로그인 인증 체크(세션체크)
    List<String> whiteList = new ArrayList<>();
    whiteList.add("/");
    whiteList.add("/login");
    whiteList.add("/logout");
    whiteList.add("/error");
    whiteList.add("/products/**");

    registry.addInterceptor(new LogIn_Interceptor())
        .order(2)
          .addPathPatterns("/**")       //**: 모든 하위 경로    블랙리스트_기본
          .excludePathPatterns(whiteList);        //화이트리스트_특정권한, 서비스, 이동, 접근에 대해 명시적으로 허가하는 목록
}
}
