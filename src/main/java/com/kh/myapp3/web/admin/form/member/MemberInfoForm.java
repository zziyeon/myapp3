package com.kh.myapp3.web.admin.form.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoForm {
    private Long MemberId;              //회원아이디
    private String email;               //이메일
    private String pw;                  //비밀번호
    private String nickname;            //별칭

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cdate;        //조회일
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime udate;        //수정일
}
