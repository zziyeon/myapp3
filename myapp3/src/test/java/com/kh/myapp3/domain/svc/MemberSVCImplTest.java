package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.Member;
import com.kh.myapp3.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberSVCImplTest {
    @Autowired
    private MemberSVC memberSVC;
    private static Member member;

    @Test
    @DisplayName("가입")
    @Order(1)
    void insert() {
        member = new Member("test1@test.com", "1234", "요리조리");

        Member insertedMember = memberSVC.insert(member);
        Assertions.assertThat(insertedMember.getEmail()).isEqualTo(member.getEmail());
        Assertions.assertThat(insertedMember.getPw()).isEqualTo(member.getPw());
        Assertions.assertThat(insertedMember.getNickname()).isEqualTo(member.getNickname());
    }

    @Test
    @DisplayName("조회")
    @Order(2)
    void findById() {
        Member findedMember = memberSVC.findById(member.getMemberId());
        Assertions.assertThat(findedMember).isEqualTo(member);
    }

    @Test
    @DisplayName("수정")
    @Order(3)
    void update() {
        String pw = "ssss1234";
        String nickname = "둘리";

        //수정정보
        Member m = new Member();
        m.setPw(pw);
        m.setNickname(nickname);
        //수정
        memberSVC.update(member.getMemberId(), m);
        //조회
        Member findedMember = memberSVC.findById(member.getMemberId());
        //비교
        Assertions.assertThat(findedMember.getPw()).isEqualTo(pw);
        Assertions.assertThat(findedMember.getNickname()).isEqualTo(nickname);
    }

    @Test
    @DisplayName("삭제")
    @Order(5)
    void del() {

    }

    @Test
    @DisplayName("목록")
    @Order(4)
    void all() {

    }
}