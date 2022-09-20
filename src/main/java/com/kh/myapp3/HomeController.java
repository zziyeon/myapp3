package com.kh.myapp3;

import com.kh.myapp3.domain.dao.Member;
import com.kh.myapp3.domain.dao.MemberDAO;
import com.kh.myapp3.domain.svc.MemberSVC;
import com.kh.myapp3.web.form.LoginForm;
import com.kh.myapp3.web.session.LoginMember;
import com.kh.myapp3.web.session.LoginOkConst;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController{

    final MemberSVC memberSVC;

    @GetMapping
    public String home() {
        //세션이 존재하면 로그인 후 화면 이동
        //세션이 없으면 로그인 전 화면 이동
        return "beforeLogin";
    }

    //로그인 화면
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form") LoginForm loginForm){

        return "login";
    }

    //로그인 처리
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("form") LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        @RequestParam(value="requestURI", required= false, defaultValue = "/") String requestURI){    //세션 연결

        //기본 검증
        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}",bindingResult);
            return "login";
        }

        //회원유무
        Optional<Member> member = memberSVC.login(loginForm.getEmail(), loginForm.getPw());
        if(member.isEmpty()){
            bindingResult.reject("loginForm.login","회원정보가 없습니다.");
            return "login";
        }

        //회원인경우
        Member findedMember = member.get();

        //세션에 회원정보 저장
        LoginMember loginMember = new LoginMember(findedMember.getEmail(), findedMember.getNickname());
        //request.getSession(true): 세션정보가 있으면 가져오고 없으면 세션을 만듬
        HttpSession session = request.getSession(true);
        session.setAttribute(LoginOkConst.LOGIN_MEMBER, loginMember);

        if (requestURI.equals("/")) {
            return "afterLogin";
        }

        return "redirect:" +requestURI;
    }
    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        //request.getSession(false): 세션정보가 있으면 가져오고 없으면 세션을 만들지 않음
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();       //세션 정보를 지움
        }
        return "redirect:/";            //초기화면으로 이동
    }

}
