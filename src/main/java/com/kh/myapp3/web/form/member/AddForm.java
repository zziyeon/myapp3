package com.kh.myapp3.web.form.member;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AddForm {
    private String email;           //EMAIL	VARCHAR2(40 BYTE)	Yes		2
    private String pw;              //PW	VARCHAR2(10 BYTE)	Yes		3
    private String pwchk;            //비밀번호 확인
    private String nickname;        //NICKNAME	VARCHAR2(30 BYTE)	No		4
}
