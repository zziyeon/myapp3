drop table memer;

create table member (
    member_id number(8,0)
    ,email varchar2(40)
    ,pw varchar2(10)
    ,nickname varchar2(30) not null
    ,cdate timestamp default systimestamp not null
    ,udate timestamp default systimestamp not null
);

--기본키 설정
ALTER TABLE member ADD CONSTRAINT member_member_id_pk PRIMARY key(member_id);

-- 유니크 키 설정
ALTER TABLE member ADD CONSTRAINT member_email_uk UNIQUE(email);

--외래키 설정
--ALTER TABLE member ADD CONSTRAINT member_p_o_num_fk FOREIGN key(OWNER_NUMBER) REFERENCES member(mem_number)on delete cascade;

--시퀀스키
DROP SEQUENCE MEMBER_MEMBER_ID_SEQ;
CREATE SEQUENCE MEMBER_MEMBER_ID_SEQ;

--객체 생성
INSERT INTO MEMBER (member_id, email, pw, nickname) VALUES (MEMBER_MEMBER_ID_SEQ.nextval, 'gildong@naver.com', 'asdf1234', '요리조리');

--객체 수정
update member set pw='abcd1234', nickname='조리요리', udate = systimestamp where member_id = 2;

-- 객체 조회
select member_id, email, pw, nickname, cdate, udate from member where member_id=2;

commit;

-- 객체 삭제
delete from member where email='gildong@naver.com';

-- 회원 번호 생성
select member_member_id_seq.nextval from dual;
select member_member_id_seq.currval from dual;

rollback;