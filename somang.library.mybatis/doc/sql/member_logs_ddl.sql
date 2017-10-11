/*
	## 회원 로그정보 엔티티 상세 분석 : 테이블명 member_logs
	1. 로그번호 :	PK		number			log_no
	2. 아이디 	:	FK,NN	varchar2(20)	member_id
	3. 로그인시간 :			date			login_time
	4. 로그아웃시간 :		date			logout_time
	5. 로그 플래그 :		varchar2(20)	log_flag
*/

-- 테이블 삭제
drop table member_logs purge;

-- 테이블 생성
create table member_logs(
log_no number,
member_id varchar2(20) not null,
login_time date,
logout_time date,
log_flag varchar2(20)
);

-- 테이블 주석
comment on table member_logs is '회원들의 로그 인/아웃 관리 테이블';

-- 컬럼 주석
comment on column member_logs.log_no is '고유 로그 코드, primary key';
comment on column member_logs.member_id is '로그인 시도한 회원의 아이디, not null';
comment on column member_logs.login_time is '로그인 성공시 로그인 한 시간';
comment on column member_logs.logout_time is '로그아웃시 로그아웃 한 시간';
comment on column member_logs.log_flag is '로그인/아웃 실패 등 비정상적 접근/종료 기록';

-- 제약 추가 변경
alter table member_logs
add constraint pk_memberlogs_no primary key(member_no);

alter table member_logs
add constraint fk_memberlogs_id foreign key(member_id) references members(member_id);