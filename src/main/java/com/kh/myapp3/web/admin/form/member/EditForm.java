package com.kh.myapp3.web.admin.form.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditForm {
    private Long memberId;
    private String email;           //EMAIL	VARCHAR2(40 BYTE)	Yes		2
    private String pw;              //PW	VARCHAR2(10 BYTE)	Yes		3
    private String nickname;
}
