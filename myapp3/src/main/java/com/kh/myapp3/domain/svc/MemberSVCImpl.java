package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.Member;
import com.kh.myapp3.domain.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        member.setMember_id(generateMemberId);
        memberDAO.insert(member);
        return memberDAO.findById(generateMemberId);
    }

    // 회원 조회
    public Member findById(Long memberId) {
        return memberDAO.findById(memberId);
    }

    // 회원 수정
    public void update(Long memberId, Member member) {
        memberDAO.update(memberId, member);
    }

    // 회원 탈퇴
    public void del(Long memberId) {
        memberDAO.del(memberId);
    }

    // 회원 목록
    public List<Member> all() {
        return memberDAO.all();
    }
}
