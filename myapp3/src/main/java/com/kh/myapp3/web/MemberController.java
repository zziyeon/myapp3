package com.kh.myapp3.web;

import com.kh.myapp3.domain.Member;
import com.kh.myapp3.domain.svc.MemberSVC;
import com.kh.myapp3.web.memberForm.AddForm;
import com.kh.myapp3.web.memberForm.MemberInfoForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSVC memberSVC;

    //회원 등록 양식
    @GetMapping("/add")
    public String addFrom(){
        return "member/addForm";        //회원 등록 view
    }

    //회원 등록 처리
    @PostMapping("/add")
    public String add(AddForm addForm){
        Member member = new Member();
        member.setPw(member.getEmail());
        member.setNickname(member.getNickname());

        Member addedMember = memberSVC.insert(member);

        return "redirect:/members/"+addedMember.getMemberId();
    }

    //회원 개별 조회
    @GetMapping("/{memberID}")
    public String findByMemberId(
            @PathVariable("memberId") Long memberId,
            Model model
    ){
        //db에서 회원 조회
        Member findedMember = memberSVC.findById(memberId);

        //Member = >
        MemberInfoForm memberInfoForm = new MemberInfoForm();
        memberInfoForm.setMemberId(MemberId.getMemberId());
    }
}
