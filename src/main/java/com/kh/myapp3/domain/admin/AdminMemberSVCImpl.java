package com.kh.myapp3.domain.admin;

import com.kh.myapp3.domain.Member;
import com.kh.myapp3.domain.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminMemberSVCImpl implements AdminMemberSVC {
    private final AdminMemberDAO adminMemberDAO;

    // 회원 등록
    @Override
    public Member insert(Member member) {
        //회원아이디 생성
        Long generateMemberId = adminMemberDAO.generateMemberId();
        member.setMemberId(generateMemberId);
        adminMemberDAO.insert(member);
        return adminMemberDAO.findById(generateMemberId);
    }

    // 회원 조회
    public Member findById(Long memberId) {
        return adminMemberDAO.findById(memberId);
    }

    // 회원 수정
    public int update(Long memberId, Member member) {
        int cnt = adminMemberDAO.update(memberId, member);
        return cnt;
    }

    // 회원 탈퇴
    public int del(Long memberId) {
        int cnt = adminMemberDAO.del(memberId);
        log.info("삭제건수{}", cnt);
        return cnt;
    }

    // 회원 목록
    public List<Member> all() {
        return adminMemberDAO.all();
    }

    @Override
    public Boolean dubChkOfMemberEmail(String email) {
        return adminMemberDAO.dubChkOfMemberEmail(email);
    }
}
