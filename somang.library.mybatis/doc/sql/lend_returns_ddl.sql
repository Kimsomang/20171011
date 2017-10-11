/*
	## 대출반납 관리 테이블 상세 분석 : 테이블명 lend_returns
	1. 대출코드 :	PK		문자열(20)	varchar2(20)	lend_code
	2. 도서번호 :	FK,NN	문자열(10)	varchar2(10)	book_no
	3. 도서명 	:	NN		문자열(100)	varchar2(100)	book_title
	4. 아이디 	:	FK,NN	문자열(20)	varchar2(20)	member_id
	5. 대출일 	:	NN		날짜		date		lend_date
	6. 반납일 	:			날짜		date			return_date
	7. 반납예정일 :	NN		날짜		date			return_period
	8. 연장 	:			문자열(3)	varchar2(5)		extension
*/

-- 테이블 삭제
drop table lend_returns purge;

-- 대출반납관리 테이블 생성
create table lend_returns (
lend_code varchar2(20),
book_no varchar2(10) not null,
book_title varchar2(100) not null,
member_id varchar2(20) not null,
lend_date date not null,
return_date date,
return_period date not null,
extension varchar2(5) default '0/1'
);

-- 테이블 주석
comment on table lend_returns is '회원들의 도서 대출반납 관리 테이블';

-- 컬럼 주석
comment on column lend_returns.lend_code is '대출시 부여되는 코드번호, primary key';
comment on column lend_returns.book_no is '대출된 도서의 고유번호, not null';
comment on column lend_returns.book_title is '대출된 도서명, not null';
comment on column lend_returns.member_id is '도서를 대출한 회원의 아이디, not null';
comment on column lend_returns.lend_date is '대출한 날짜, not null';
comment on column lend_returns.return_date is '반납한 날짜';
comment on column lend_returns.return_period is '반납예정일, 대출일로부터 10일 후, not null';
comment on column lend_returns.extension is '반납일 연장 횟수, 최대 1번, 연장시 반납 예정일+7일';

-- 제약 추가 변경
alter table lend_returns
add constraint pk_lendreturns_code primary key(lend_code);

alter table lend_returns
add constraint fk_lendreturns_no foreign key(book_no) references books(book_no);

alter table lend_returns
add constraint fk_lendreturns_member_id foreign key(member_id) references member(member_id);

/*-------------------------------------------
	제약, 주석 DATA DICTIONARY 
	-- user_constraints
	-- user_cons_columns
	-- user_tab_comments
	-- user_col_comments
--------------------------------------------*/

-- 테이블 주석 조회
select table_name, comments from user_tab_comments
where table_name in ('MEMBERS', 'LEND_RETURNS')
order by table_name;

-- 회원 테이블 컬럼 주석
select column_name, comments
from user_col_comments
where table_name in ('MEMBERS');

-- 게시글 테이블 컬럼 주석
select column_name, comments
from user_col_comments
where table_name in ('LEND_RETURNS');