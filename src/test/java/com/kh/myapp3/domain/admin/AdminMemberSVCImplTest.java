package com.kh.myapp3.domain.admin;

import com.kh.myapp3.domain.dao.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class AdminMemberSVCImplTest {
  @Autowired
  private AdminMemberSVC adminMemberSVC;

  @Test
  @DisplayName("회원가입")
  void insert() {
    Member member = new Member();
    member.setNickname("춘식이");
    member.setPw("1234");
    member.setEmail("test1@naver.com");

    Member joinedMember = adminMemberSVC.insert(member);

    log.info("joinedMember={}", joinedMember);

    Assertions.assertThat(joinedMember).isEqualTo(1);
  }

}