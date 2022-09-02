package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberSVCImplTest {
    @Autowired
    private MemberSVC memberSVC;

    @Test
    @DisplayName("가입")
    void insert() {
        Member member = new Member("test9@test.com", "1234", "요리조리");

        Member insertedMember = memberSVC.insert(member);
        Assertions.assertThat(insertedMember.getEmail()).isEqualTo(member.getEmail());
        Assertions.assertThat(insertedMember.getPw()).isEqualTo(member.getPw());
        Assertions.assertThat(insertedMember.getNickname()).isEqualTo(member.getNickname());
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void del() {
    }

    @Test
    void all() {
    }
}