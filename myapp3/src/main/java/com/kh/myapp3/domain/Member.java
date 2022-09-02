package com.kh.myapp3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long member_id;                 //회원아이디(내부관리용) MEMBER_ID	NUMBER(8,0)	No		1
    private String email;                   //이메일 EMAIL	VARCHAR2(40 BYTE)	Yes		2
    private String pw;                      //비밀번호 PW	VARCHAR2(10 BYTE)	Yes		3
    private String nickname;                //별칭 NICKNAME	VARCHAR2(30 BYTE)	No		4
    private LocalDateTime cdate;            //생성일 CDATE	TIMESTAMP(6)	No	systimestamp 	5
    private LocalDateTime udate;            //수정일 UDATE	TIMESTAMP(6)	No	systimestamp 	6

    public Member(String email, String pw, String nickname) {
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getMember_id() {
        return member_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public LocalDateTime getUdate() {
        return udate;
    }
}
