package com.kh.myapp3.domain.dao;

import com.kh.myapp3.domain.Member;

import java.util.List;

public interface MemberDAO {
    /**
     * 가입
     * @param member 가입정보
     * @return 가입건수
     */
    int insert(Member member);

    /**
     * 조회 by 회원아이디
     * @param memberId
     * @return 회원정보
     */
    Member findById(Long memberId);

    /**
     * 수정
     * @param memberId 회원아이디
     * @param member 수정정보     *
     * @return 수정건수
     */
    int update(Long memberId, Member member);

    /**
     * 탈퇴
     *
     * @param memberId 아이디
     * @param pw       비밀번호
     * @return 삭제건수
     */
    int del(Long memberId, String pw);

    /**
     * 목록
     * @return 회원전체
     */
    List<Member> all();

    /**
     * 신규 회원아이디(내부 관리용) 생성
     * @return 회원아이디
     */
    Long generateMemberId();
}