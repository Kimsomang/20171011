/*
	## 희망도서 신청 게시판 엔티티 상세 분석 : 테이블명 wish_books
	1. 게시번호 	:	PK		number			wish_no
	2. 아이디 		:	FK,NN	varchar2(20)	member_id
	3. 제목(도서명) : 	NN		varchar2(100)	book_title
	4. 저자 		:	NN		varchar2(30)	author
	5. 출판사 		:	NN		varchar2(20)	publisher
	6. 출판일 		:			varchar2(10)	publishing_date
	7. 처리상태 	:	NN		varchar2(10)	condition
*/

-- 테이블 삭제
drop table wish_books purge;

-- 테이블 생성
create table wish_books(
wish_no number,
member_id varchar2(20) not null,
book_title varchar2(100) not null,
author varchar2(30) not null,
publisher varchar2(20) not null,
publishing_date varchar2(10),
condition varchar2(10) not null
);

-- 테이블 주석
comment on table wish_books is '희망도서 신청 게시판 테이블';

-- 컬럼 주석
comment on column wish_books.wish_no is '희망도서 신청 게시글 작성시 자동으로 부여되는 게시번호, primary key';
comment on column wish_books.member_id is '희망도서 신청 게시글을 작성한 회원의 아이디, not null';
comment on column wish_books.book_title is '희망도서 신청 게시글 제목, 도서명, not null';
comment on column wish_books.author is '희망도서의 저자, not null';
comment on column wish_books.publisher is '희망도서의 출판사, not null';
comment on column wish_books.publishing_date is '희망도서의 출판일';
comment on column wish_books.condition is '희망도서 신청 상태 (신청중, 신청완료 등)';

-- 제약 추가 변경
alter table wish_books
add constraint pk_wishbooks_no primary key(wish_no);