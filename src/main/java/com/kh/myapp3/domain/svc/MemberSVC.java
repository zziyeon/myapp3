package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.dao.Member;

import java.util.Optional;

public interface MemberSVC {
    /**
     * 가입
     * @param member 가입정보
     * @return 회원
     */
    Member insert(Member member);

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
     * @param memberId 아이디
     * @param pw 비밀멎ㄴ호
     * @return 삭제건수
     */
    int del(Long memberId, String pw);

    //로그인
    Optional<Member> login (String email, String pw);
}

