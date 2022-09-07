package com.kh.myapp3.web;

import com.kh.myapp3.domain.Member;
import com.kh.myapp3.domain.svc.MemberSVC;
import com.kh.myapp3.web.form.member.AddForm;
import com.kh.myapp3.web.form.member.EditForm;
import com.kh.myapp3.web.form.member.MemberInfoForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSVC memberSVC;

    //회원 등록 양식
    @GetMapping("/add")
    public String addForm() {
        return "member/addForm";        //회원 등록 view
    }

    //회원 등록 처리 (POST) /members/add
    @PostMapping("/add")
    public String add(AddForm addForm) {
        log.info("addForm:{}",addForm);

        Member member = new Member();

        member.setEmail(addForm.getEmail());
        member.setPw(addForm.getPw());
        member.setNickname(addForm.getNickname());

        memberSVC.insert(member);

        return "login/loginForm";
    }

    //회원 개별 조회
    @GetMapping("/{id}")
    public String findById(
            @PathVariable("id") Long id,
            Model model
    ) {
        //db에서 회원 조회
        Member findedMember = memberSVC.findById(id);

        //Member = > MemberInfoForm 복사
        MemberInfoForm memberInfoForm = new MemberInfoForm();

        memberInfoForm.setMemberId(findedMember.getMemberId());
        memberInfoForm.setEmail(findedMember.getEmail());
        memberInfoForm.setPw(findedMember.getPw());
        memberInfoForm.setNickname(findedMember.getNickname());

        //view에서 참조하기 위한 model 객체에 저장
        model.addAttribute("memberInfoForm", memberInfoForm);

        return "member/memberForm";         //회원 상세화면
    }

    // 회원 정보 수정 양식
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {

        //db에서 회원 조회
        Member findedMember = memberSVC.findById(id);
//
//        //Member => EditForm 복사
//        EditForm editForm = new EditForm();
//
//        editForm.setEmail((findedMember.getEmail()));
//        editForm.setPw(findedMember.getPw());
//        editForm.setNickname(findedMember.getNickname());

//        model.addAttribute("editForm", editForm);

        model.addAttribute("member", findedMember);

        return "member/editForm";
    }

    //수정 처리
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, EditForm editForm) {
        Member member = new Member();
        member.setPw(editForm.getPw());
        member.setNickname(editForm.getNickname());

        int updatedRow = memberSVC.update(id, member);
        if(updatedRow == 0) {
            return "member/edit";
        }

        return "redirect:/members/{id}";
    }

    //탈퇴 화면(Get)
    @GetMapping("{id}/del")
    public String delForm(){
        return "member/delForm";        //회원 탈퇴 view
    }

    //탈퇴 처리(POST)
    @PostMapping("/{id}/del")
    public String del(@PathVariable("id") Long id, @RequestParam("pw") String pw) {
        int deletedRow = memberSVC.del(id, pw);
        if(deletedRow == 0){
            return "member/delForm";
        }
        return "redirect:/";         //삭제 후 전체 목록 url
    }

    // 목록 화면
    @GetMapping("")
    public String list(Model model) {
        log.info("Model:{}", model);

        List<Member> list = memberSVC.all();
        model.addAttribute("list", list);

        return "member/all";               //전체 목록 view
    }
}
