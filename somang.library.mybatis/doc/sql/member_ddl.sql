/*
	## 회원 엔티티 상세 분석 : 테이블명 members
	1. 아이디 		: 	PK	문자열(20)	varchar2(20)	member_id
	2. 비밀번호 	:	NN	문자열(20)	varchar2(20)	member_pw
	3. 이름 		:	NN	문자열(20)	varchar2(20)	member_name
	4. 생년월일 	:	NN	문자열(10)	varchar2(20)	birth
	5. 연락처 		:	NN	문자열(13)	varchar2(20)	mobile
	6. 주소 		:	NN	문자열(100)	varchar2(100)	address
	7. 대출권수 	:		문자열(6)	varchar2(10)	lend_book
	8. 대출가능여부 :	NN	문자열(8)	varchar2(10)	condition
	9. 선호도서 	:		문자열(24)	varchar2(30)	favorite
*/


-- 테이블 삭제
drop table member purge;

-- 회원관리 테이블 생성
create table member (
member_id varchar2(20),
member_pw varchar2(20) not null,
member_name varchar2(20) not null,
birth varchar2(20) not null,
mobile varchar2(20) not null,
address varchar2(100) not null,
lend_book varchar2(10) default '0/5',
condition varchar2(10) default '대출가능',
favorite varchar2(30)
);

-- 테이블 주석
comment on table member is '회원관리 테이블, 회원가입시 작성';

-- 컬럼 주석
comment on column member.member_id is '사용자의 아이디, primary key';
comment on column member.member_pw is '사용자의 비밀번호, not null';
comment on column member.member_name is '사용자의 이름, not null';
comment on column member.birth is '사용자의 생년월일, not null';
comment on column member.mobile is'사용자의 연락처, unique, not null';
comment on column member.address is '사용자의 주소, not null';
comment on column member.lend_book is '사용자가 대출한 책의 권수, 최대 5권까지 가능';
comment on column member.condition is '사용자의 대출가능여부, 연체시 대출불가';
comment on column member.favorite is '사용자의 선호 도서 분야, 최대 3가지 선택 가능';


-- 제약 추가 변경
alter table member
add constraint pk_member_id primary key(member_id);

alter table member
add constraint u_member_mobile unique(mobile);