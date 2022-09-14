package com.kh.myapp3.web.admin.form.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditForm {
    private Long memberId;
    private String email;           //EMAIL	VARCHAR2(40 BYTE)	Yes		2
    @Size(min = 0, max=10)
    private String pw;              //PW	VARCHAR2(10 BYTE)	Yes		3
    @Size(min = 0, max=10)
    private String nickname;
}
