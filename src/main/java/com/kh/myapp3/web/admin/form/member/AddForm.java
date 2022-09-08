package com.kh.myapp3.web.admin.form.member;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddForm {
    @NotBlank
    @Email(regexp = ".+@.+\\..+") //, message="이메일형식X")      //여기서 이메일 형식 관리하거나 'errors.properties'에서 관리하거나  //regexp: 정규 표현식
    private String email;           //이메일 EMAIL	VARCHAR2(40 BYTE)	Yes		2
    @NotBlank
    @Size(min = 0, max=10)
    private String pw;              //비밀번호 PW	VARCHAR2(10 BYTE)	Yes		3
    @Size(min = 0, max=10)
    private String nickname;        //닉네임 NICKNAME	VARCHAR2(30 BYTE)	No		4
}
