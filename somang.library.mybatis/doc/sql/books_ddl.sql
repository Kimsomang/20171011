/*
	## 도서관리 테이블 상세 분석 : 테이블명 books
	1. 도서번호 :	PK	문자열(10)	varchar2(10)	book_no
	2. 도서명 	:	NN	문자열(100)	varchar2(100)	book_title
	3. 저자	 	:	NN	문자열(30)	varchar2(30)	author
	4. 출판사 	:	NN	문자열(20)	varchar2(20)	publisher
	5. 출판일 	:	NN	문자열(10)	varchar2(10)	publishing_date
	6. ISBN		:	NN	문자열(13)	varchar2(13)	ISBN
	7. 자료상태 :		문자열(8)	varchar2(10)	condition
	8. 소장위치 :	NN	문자열(9)	varchar2(20)	location
	9. 청구기호 :	NN	문자열(8)	varchar2(10)	type_code
	10.도서분류 :	NN	문자열(8)	varchar2(10)	category
*/

--테이블 삭제
drop table books purge;

-- 도서관리 테이블 생성
create table books (
book_no varchar2(10) not null,
book_title varchar2(100) not null,
author varchar2(30) not null,
publisher varchar2(20) not null,
publishing_date varchar2(10) not null,
ISBN	varchar2(13) not null,
condition varchar2(10) default '대출가능',
location varchar2(20) not null,
type_code varchar2(10) not null,
category varchar2(10) not null
);

-- 테이블 주석
comment on table books is '도서관에 등록되어 있는 도서들을 관리하는 테이블';

-- 컬럼 주석
comment on column books.book_no is '도서 등록시 부여되는 도서번호, primary key';
comment on column books.book_title is '도서명, 도서제목, not null';
comment on column books.author is '도서의 저자명, not null';
comment on column books.publisher is '도서의 출판사, not null';
comment on column books.publishing_date is '도서의 출판일, not null';
comment on column books.ISBN is '도서 국제표준도서번호, not null';
comment on column books.condition is '도서의 대출가능 상태, default 대출가능';
comment on column books.location is '도서의 소장위치, not null';
comment on column books.type_code is '도서의 분야별 청구기호, not null';
comment on column books.category is '도서의 십진표분류에 의한 분야 분류, not null';

-- 제약 추가 변경
alter table books
add constraint pk_books_no primary key(book_no);
