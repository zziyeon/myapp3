package com.kh.myapp3.web.admin;

import com.kh.myapp3.domain.Member;
import com.kh.myapp3.domain.admin.AdminMemberSVC;
import com.kh.myapp3.web.admin.form.member.AddForm;
import com.kh.myapp3.web.admin.form.member.EditForm;
import com.kh.myapp3.web.admin.form.member.MemberInfoForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/members")
@RequiredArgsConstructor
public class AdminMemberController {
    private final AdminMemberSVC adminMemberSVC;

    //회원 등록 양식
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("addForm", new AddForm());
        return "admin/member/addForm";        //회원 등록 view
    }

    //회원 등록 처리 (POST) /members/add
    @PostMapping("/add")
    public String add(@ModelAttribute AddForm addForm) {
        //검증
        //model.addAttribute("addForm", addForm);
        log.info("addForm:{}",addForm);
        if(addForm.getEmail().trim().length()==0){
            return "admin/member/addForm";
        }

        Member member = new Member();

        member.setEmail(addForm.getEmail());
        member.setPw(addForm.getPw());
        member.setNickname(addForm.getNickname());

        Member insertedMember = adminMemberSVC.insert(member);

        return "redirect:/admin/members/"+insertedMember.getMemberId();       //회원 상세
    }

    //회원 개별 조회
    @GetMapping("/{id}")
    public String findById(
            @PathVariable("id") Long id,
            Model model
    ) {
        //db에서 회원 조회
        Member findedMember = adminMemberSVC.findById(id);

        //Member = > MemberInfoForm 복사
        MemberInfoForm memberInfoForm = new MemberInfoForm();

        memberInfoForm.setMemberId(findedMember.getMemberId());
        memberInfoForm.setEmail(findedMember.getEmail());
        memberInfoForm.setNickname(findedMember.getNickname());
        memberInfoForm.setPw(findedMember.getPw());
        memberInfoForm.setUdate(findedMember.getUdate());
        memberInfoForm.setCdate(findedMember.getCdate());

        //view에서 참조하기 위한 model 객체에 저장

        model.addAttribute("memberInfoForm", memberInfoForm);

        return "admin/member/memberForm";         //회원 상세화면
    }

    // 회원 정보 수정 양식
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {

        //db에서 회원 조회
        Member findedMember = adminMemberSVC.findById(id);

        //Member => EditForm 복사
        EditForm editForm = new EditForm();

        editForm.setMemberId(findedMember.getMemberId());
        editForm.setEmail(findedMember.getEmail());
        editForm.setPw(findedMember.getPw());
        editForm.setNickname(findedMember.getNickname());

        model.addAttribute("editForm", editForm);

        return "admin/member/editForm";
    }

    //수정 처리
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, EditForm editForm) {
        Member member = new Member();
        member.setPw(editForm.getPw());
        member.setNickname(editForm.getNickname());

        int updatedRow = adminMemberSVC.update(id, member);
        if(updatedRow == 0) {
            return "admin/member/editForm";
        }
        return "redirect:/admin/members/{id}";
    }

    //탈퇴 처리(POST)
    @GetMapping("/{id}/del")
    public String del(@PathVariable("id") Long id) {
        int deletedRow = adminMemberSVC.del(id);
        if(deletedRow == 0){
            return "redirect:/admin/member/"+id;
        }
        return "redirect:/admin/members";         //삭제 후 전체 목록 url
    }

    // 목록 화면
    @GetMapping("")
    public String all(Model model) {
        log.info("Model:{}", model);

        List<Member> list = adminMemberSVC.all();
        model.addAttribute("list", list);

        return "admin/member/all";               //전체 목록 view
    }
}
