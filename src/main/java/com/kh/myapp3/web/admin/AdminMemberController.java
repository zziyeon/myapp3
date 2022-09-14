package com.kh.myapp3.web.admin;

import com.kh.myapp3.domain.Member;
import com.kh.myapp3.domain.admin.AdminMemberSVC;
import com.kh.myapp3.web.admin.form.member.AddForm;
import com.kh.myapp3.web.admin.form.member.EditForm;
import com.kh.myapp3.web.admin.form.member.MemberInfoForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
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
        model.addAttribute("form", new AddForm());

        return "admin/member/addForm";        //회원 등록 view
    }

    //회원 등록 처리 (POST) /members/add
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("form") AddForm addForm, BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {  //리다이렉트할때 정보를 유지하기 위해 사용

        //model.addAttribute("addForm",addForm);   =>@ModelAttribute를 해주면 이걸 안 적어도됨
        log.info("addForm:{}",addForm);

        //검증
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "admin/member/addForm";
        }

        //회원아이디 중복 체크
        Boolean isExist = adminMemberSVC.dubChkOfMemberEmail(addForm.getEmail());
        if (isExist) {
            bindingResult.rejectValue("email","dub.email", "이메일이 이미 존재합니다.");

            return "admin/member/addForm";
        }

        //회원 등록
        Member member = new Member();

        member.setEmail(addForm.getEmail());
        member.setPw(addForm.getPw());
        member.setNickname(addForm.getNickname());
        Member insertedMember = adminMemberSVC.insert(member);

        Long id = insertedMember.getMemberId();
        redirectAttributes.addAttribute("id", id);
//        return "redirect:/admin/members/" +id;
        return "redirect:/admin/members/{id}";
    };       //회원 상세

    public String add2(@Valid @ModelAttribute AddForm addForm, BindingResult bindingResult) {
        //검증
        //model.addAttribute("addForm", addForm);
        log.info("addForm:{}",addForm);
//        if(addForm.getEmail() ==null || addForm.getEmail().trim().length()==0){
//            return "admin/member/addForm_old";
//        }
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "admin/member/addForm_old";
        }

        //비즈니스 규칙( 필드검증)
        //1) 이메일에 @가 없으면 오류
        if(!addForm.getEmail().contains("@")){
            bindingResult.rejectValue("email", "emailChk1","이메일 형식에 맞지 않습니다.");
            return "admin/member/addForm_old";
        }
        //2) 이메일 자리수 초과시 오류
        if(addForm.getEmail().length() > 5){
            bindingResult.rejectValue("email", "emailChk2", new String[]{"0","5"}, "이메일 자리수를 초과하였습니다.");
            return "admin/member/addForm_old";
        }
        //3) objectError 2개 이상의 필드 분석을 통해 오류 검출
        // 비밀번화, 별칭의 자리수가 모두 5미만인 경우
        if (addForm.getPw().trim().length() < 5 && addForm.getNickname().trim().length() < 5) {
            bindingResult.reject("memberChk", new String[]{"5"}, "비밀번호,별칭의 자리수가 모두 5미만입니다.");
            return "admin/member/addForm_old";
        }

        //회원 등록
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

        model.addAttribute("form", memberInfoForm);

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

        model.addAttribute("form", editForm);

        return "admin/member/editForm";
    }

    //수정 처리
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("form") EditForm editForm, BindingResult bindingResult) {
        //검증
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "admin/member/editForm";
        }

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

        List<Member> members = adminMemberSVC.all();
        List<MemberInfoForm> list = new ArrayList<>();
        //case1) 향상된 for문
//        for (Member member : members) {
//            MemberInfoForm memberInfoForm = new MemberInfoForm();
//            BeanUtils.copyProperties(member,memberInfoForm);        //이름과 타입이 같으면 set,get 안해줄수 있음
//            list.add(memberInfoForm);
//        }
        //case2) 고차함수 적용->람다표현식
        members.stream().forEach(member->{
            MemberInfoForm memberInfoForm = new MemberInfoForm();
            BeanUtils.copyProperties(member,memberInfoForm);
            list.add(memberInfoForm);
        });

        model.addAttribute("list", list);
        return "admin/member/all";               //전체 목록 view
    }
}
