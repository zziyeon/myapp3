drop table member;

create table member (
    member_id number(8,0)
    ,email varchar2(40)
    ,pw varchar2(10)
    ,nickname varchar2(30) not null
    ,cdate timestamp default systimestamp not null
    ,udate timestamp default systimestamp not null
);

--�⺻Ű ����
ALTER TABLE member ADD CONSTRAINT member_member_id_pk PRIMARY key(member_id);

-- ����ũ Ű ����
ALTER TABLE member ADD CONSTRAINT member_email_uk UNIQUE(email);

--�ܷ�Ű ����
--ALTER TABLE member ADD CONSTRAINT member_p_o_num_fk FOREIGN key(OWNER_NUMBER) REFERENCES member(mem_number)on delete cascade;

--������Ű
DROP SEQUENCE MEMBER_MEMBER_ID_SEQ;
CREATE SEQUENCE MEMBER_MEMBER_ID_SEQ;

--��ü ����
INSERT INTO MEMBER (member_id, email, pw, nickname) VALUES (MEMBER_MEMBER_ID_SEQ.nextval, 'gildong@naver.com', 'asdf1234', '�丮����');

--��ü ����
update member set pw='abcd1234', nickname='�����丮', udate = systimestamp where member_id = 2;

-- ��ü ��ȸ
select member_id, email, pw, nickname, cdate, udate from member where member_id=2;

commit;

-- ��ü ����
delete from member where member_id=2;

-- ȸ�� ��ȣ ����
select member_member_id_seq.nextval from dual;
select member_member_id_seq.currval from dual;

rollback;