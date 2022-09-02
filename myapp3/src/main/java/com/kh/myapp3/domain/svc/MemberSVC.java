package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.Member;

import java.util.List;

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
    void update(Long memberId, Member member);

    /**
     * 탈퇴
     * @param memberId 아이디
     * @return 삭제건수
     */
    void del(Long memberId);

    /**
     * 목록
     * @return 회원전체
     */
    List<Member> all();
}
