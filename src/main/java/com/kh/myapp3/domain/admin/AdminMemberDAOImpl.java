package com.kh.myapp3.domain.admin;

import com.kh.myapp3.domain.dao.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AdminMemberDAOImpl implements AdminMemberDAO {
    private final JdbcTemplate jt;

    //신규 회원 번호 생성
    public Long generateMemberId() {
        String sql = "select member_member_id_seq.nextval from dual ";
        Long memberId = jt.queryForObject(sql, Long.class);
        return memberId;
    }

    /**
     * 가입
     *
     * @param member 가입정보
     * @return 회원
     */
    @Override
    public int insert(Member member) {
        int result = 0;
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO MEMBER (member_id, email, pw, nickname) ");
        sql.append("VALUES (?, ?, ?, ?) ");

//        Long memberId = generateMemberId();
        result = jt.update(sql.toString(), member.getMemberId(), member.getEmail(), member.getPw(), member.getNickname());

        return result;
    }

    /**
     * 조회 by 회원 아이디
     *
     * @param memberId 회원 아이디
     * @return 회원정보
     */
    @Override
    public Member findById(Long memberId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select member_id, email, pw, nickname, cdate, udate from member where member_id=? ");

        Member findedMember = null;
        try {
            //BeanPropertyRowMapper는 매ㅠㅣㅇ되는 자바클래스에 디폴트생성자 필수
            findedMember = jt.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(Member.class), memberId);
        } catch (DataAccessException e) {
            log.info("찾고자하는 회원이 없습니다.=>{}", memberId);
        }
        return findedMember;
    }

    /**
     * 수정
     *
     * @param memberId 회원아이디
     * @param member   수정정보     *
     * @return
     */
    @Override
    public int update(Long memberId, Member member) {
        int result = 0;
        StringBuffer sql = new StringBuffer();
        sql.append("update member ");
        sql.append("set nickname=?, pw = ?, udate = systimestamp ");
        sql.append("where member_id = ? ");

        result = jt.update(sql.toString(), member.getNickname(), member.getPw(), memberId);
        return result;
    }


    //탈퇴
    @Override
    public int del(Long memberId) {
        int result = 0;
        String sql = "delete from member where member_id=? ";

        result = jt.update(sql, memberId);
        return result;
    }

    @Override
    public List<Member> all() {
        String sql = "select member_id, email, pw, nickname, cdate, udate from member ";

        List<Member> list = jt.query(sql, new BeanPropertyRowMapper<>(Member.class));
        return list;
    }

    @Override
    public Boolean dubChkOfMemberEmail(String email) {
        String sql = "select count(email) from member where email=? ";

        Integer rowCount = jt.queryForObject(sql, Integer.class, email);
        return rowCount == 1 ? true : false;
    }
}
