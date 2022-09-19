package com.kh.myapp3.domain.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class MemberDAOImplTest {
  @Autowired
  MemberDAO memberDAO;


  @Test
  @DisplayName("회원이 존재하는지 테스트")
  void isExistMember() {
    String email = "test88@naver.com";
    String pw = "88";
    Optional<Member> login = memberDAO.login(email, pw);
//    long count = login.stream().filter(member -> member.getEmail().equals("test1@test.com"))
//                               .count();
//    Assertions.assertThat(count).isEqualTo(1);
    Assertions.assertThat(login.isPresent()).isTrue();
  }
  @Test
  @DisplayName("회원이 존재하지 않는지 테스트")
  void isNotExistMember() {
    String email = "test1@test.com";
    String pw = "12ssfe346";
    Optional<Member> login = memberDAO.login(email, pw);
//    long count = login.stream().filter(member -> member.getEmail().equals("test1@test.com"))
//                               .count();
//    Assertions.assertThat(count).isEqualTo(1);
    Assertions.assertThat(login.isEmpty()).isTrue();
  }
}