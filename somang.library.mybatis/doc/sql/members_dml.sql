-- 데이터 입력
insert into member(member_id, member_pw, member_name, birth, mobile, address)
values('user01', 'password01', '홍길동', '1991.01.01', '010-1111-2222', '서울시');

insert into member(member_id, member_pw, member_name, birth, mobile, address)
values('user02', 'password02', '길라임', '1992.02.02', '010-123-1234', '강릉시');

insert into member(member_id, member_pw, member_name, birth, mobile, address)
values('user03', 'password03', '김주원', '1993.03.03', '011-4545-4545', '인천시');

insert into member(member_id, member_pw, member_name, birth, mobile, address)
values('user04', 'password04', '이민정', '1994.02.04', '010-2345-2345','화성시');

insert into member(member_id, member_pw, member_name, birth, mobile, address)
values('user05', 'password05', '김소망', '1995.01.23', '010-5656-5656', '충주시');

insert into member(member_id, member_pw, member_name, birth, mobile,address)
values('user06', 'password06', '김유신', '1996.04.23', '011-5445-4545', '광주시');

insert into member(member_id, member_pw, member_name, birth, mobile, address)
values('user07', 'password07', '유관순','1997.07.07', '010-1010-1010', '천안시');

insert into member(member_id, member_pw, member_name, birth, mobile, address)
values('user08', 'password08', '이순신', '1998.08.08', '010-8520-8520', '속초시');

insert into member(member_id, member_pw, member_name, birth, mobile, address)
values('user09', 'password09', '차라임', '1999.03.19', '010-4566-4566', '제주시');

insert into member(member_id, member_pw, member_name, birth, mobile, address)
values('user10', 'password10', '한주원', '2000.01.01', '010-9876-9876', '안산시');

-- 회원가입
-- insert into members values (?, ?, ?, ?, ?, ?, ?, ?)

-- 로그인
-- select member_pw from members where member_id = ?

-- 아이디찾기
-- select member_id from members where member_name = ?, mobile = ?

-- 비밀번호찾기
-- select member_pw from members where member_id = ?, member_name = ?

-- 회원정보조회
-- select * from members where member_id = ?

-- 회원전체목록 조회
-- select * from members


-- 데이터 수정
update members
set lend_book = '1/5' where member_id = 'user10';

-- 회원 정보수정 > 비밀번호 변경
-- update members set member_pw = ? where member_id = ?

-- 회원 정보수정 > 연락처 변경
-- update members set mobile = ? where member_id = ?

-- 회원 정보수정 > 주소 변경
-- update members set address = ? where member_id = ?

-- 데이터 삭제
delete from members where member_id = 'user09';

-- 회원 탈퇴
-- delete from members where member_id = ?

