package com.kh.myapp3.domain.admin;

import com.kh.myapp3.domain.dao.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
// ↓테스트하면 테이블에 데이터가 쌓이는데 마지막에 Rollback됨 => 쌓이지 않음
//@Transactional

class AdminMemberDAOImplTest {

  @Autowired
  private AdminMemberDAO adminMemberDAO;

  @Test
  @DisplayName("회원 가입")
//  @Transactional 테스트 환경에서는 메소드 종료전 롤백시킴
  // ↓테스트 대상에서 제외
  @Disabled
  void insert() {
    //예시)
    // String str1 = "hello";
    // String str2 = "world";
    // String str3 = str1 + str2;
    // log.info("{}, {}, {}", str1, str2, str3);
    Member member = new Member();
    member.setMemberId(1011l);
    member.setEmail("gildong1011@naver.com");
    member.setPw("1234");
    member.setNickname("요리조리길동");

    int affectedRow = adminMemberDAO.insert(member);

    log.info("affectedRow={}", affectedRow);
    Assertions.assertThat(affectedRow).isEqualTo(1);
  }

  @Test
  @DisplayName("회원아이디 중복")
  void insertThrow() {
    Member member = new Member();
    member.setMemberId(1011l);
    member.setEmail("gildong1011@naver.com");
    member.setPw("1234");
    member.setNickname("요리조리길동");

    org.junit.jupiter.api.Assertions.assertThrows(
            DuplicateKeyException.class,
            ()->adminMemberDAO.insert(member)
    );
  }

  @Test
  @DisplayName("회원 조회")
  void findById() {
    Member member = adminMemberDAO.findById(1011l);
    log.info("member={}", member);
    Assertions.assertThat(member.getNickname()).isEqualTo("요리조리길동");
    Assertions.assertThat(member.getEmail()).isEqualTo("gildong1011@naver.com");
  }

  @Test
  @DisplayName("회원조회-회원존재하는 경우")
  void findByIdWhenIsNotExitMember() {
    Member member = adminMemberDAO.findById(9999l);
    Assertions.assertThat(member).isNull();
  }

  @Test
  @DisplayName("회원 수정")
  void update() {
    Long memberId=1010l;
    Member member = new Member();
    member.setNickname("조리퐁길동");
    member.setPw("4567");
    adminMemberDAO.update(memberId,member);

    Member updatedMember = adminMemberDAO.findById(memberId);

    Assertions.assertThat(updatedMember.getNickname()).isEqualTo("조리퐁길동");
    Assertions.assertThat(updatedMember.getPw()).isEqualTo("4567");
  }

  @Test
  void del() {
  }

  @Test
  @DisplayName("회원 목록")
  void all() {
    List<Member> memberList = adminMemberDAO.all();
    log.info("회원수={}", memberList.size());
//    for (Member member : memberList) {
//      log.info(member.toString());
//    }
    memberList.stream().forEach(member -> log.info(member.toString()));
  }

  @Test
  void dubChkOfMemberEmail() {
  }
}