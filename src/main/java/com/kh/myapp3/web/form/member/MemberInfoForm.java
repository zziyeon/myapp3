package com.kh.myapp3.web.form.member;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoForm {
    private Long MemberId;              //회원아이디
    private String email;               //이메일
    private String pw;                  //비밀번호
    private String nickname;            //별칭
    private LocalDateTime cdate;        //조회일
    private LocalDateTime udate;        //수정일
}
