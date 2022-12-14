package com.kh.myapp3.domain.admin;

import com.kh.myapp3.domain.dao.Member;

import java.util.List;

//svc와 dao 사이에 뭘 주고 받을건지

public interface AdminMemberDAO {

    /**
     * 가입
     * @param member 가입정보
     * @return 가입건수
     */
    // 행의 수 반환
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
     * @param memberId 아이디
     * @return 삭제건수
     */
    int del(Long memberId);

    Long generateMemberId();

    /**
     * 목록
     * @return 회원전체
     */
    List<Member> all();

    /**
     * 이메일 중복 체크
     * @param email 회원 이메일
     * @return  존재시: true , false
     */
    Boolean dubChkOfMemberEmail(String email);
}
