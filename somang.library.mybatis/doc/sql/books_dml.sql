-- 도서자료 입력
insert into books(book_no, book_title, author, publisher, publishing_date, ISBN, location, type_code, category)
values(books_no_seq.nextval, '나미야 잡화점의 기적', '히가시노 게이고', '현대문학', '2012.12.19', '1234567891010', '3층 자료실', '853.27ㅎ', '800');

insert into books(book_no, book_title, author, publisher, publishing_date, ISBN, location, type_code, category)
values(books_no_seq.nextval, '잠', '베르나르 베르베르', '열린책들', '2017.06.08', '1234567891011', '3층 자료실', '853.80ㅂ', '800');

insert into books(book_no, book_title, author, publisher, publishing_date, ISBN, location, type_code, category)
values(books_no_seq.nextval, '언어의 온도', '이기주', '말글터', '2016.08.19', '1층 자료실', '216.52ㅇ', '200');

insert into books(book_no, book_title, author, publisher, publishing_date, ISBN, location, type_code, category)
values(books_no_seq.nextval, '자존감수업', '윤홍균', '심플라이프', '2016.09.01', '2층 자료실', '465.43ㅇ', '400');

insert into books(book_no, book_title, author, publisher, publishing_date, ISBN, location, type_code, category)
values(books_no_seq.nextval, '보노보노처럼 살다니 다행이야', '김신회', '놀', '2017.04.06', '1층 자료실', '217.81ㄱ', '200');

-- 데이터 입력시 book_no은 시퀀스 사용, condition은 '대출가능'

-- insert into books values(?,?,?,?,?,?,?,?,?,?);

-- select * from books order by book_no

-- select * from books where book_title like ('%?%')
-- select * from books where author like ('%?%')

-- 수정
update books
set condition='대출중' where book_no = 1;

-- update books set condition = ? where book_no = ?
-- update books set location = ? where book_no = ?

-- 삭제
delete from books where book_no = 2;

-- delete from books where book_no = ?