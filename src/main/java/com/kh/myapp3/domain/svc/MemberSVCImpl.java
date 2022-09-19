package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.dao.Member;
import com.kh.myapp3.domain.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSVCImpl implements MemberSVC{
    private final MemberDAO memberDAO;

    // 회원 등록
    @Override
    public Member insert(Member member) {
        //회원아이디 생성
        Long generateMemberId = memberDAO.generateMemberId();
        member.setMemberId(generateMemberId);
        memberDAO.insert(member);
        return memberDAO.findById(generateMemberId);
    }

    // 회원 조회
    public Member findById(Long memberId) {
        return memberDAO.findById(memberId);
    }

    // 회원 수정
    public int update(Long memberId, Member member) {
        int cnt = memberDAO.update(memberId, member);
        return cnt;
    }

    // 회원 탈퇴
    public int del(Long memberId, String pw) {
        int cnt =    memberDAO.del(memberId, pw);
        log.info("삭제건수{}", cnt);
        return cnt;
    }
    //로그인
    @Override
    public Optional<Member> login(String email, String pw) {
        return memberDAO.login(email, pw);
    }
}
