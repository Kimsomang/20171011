-- 대출반납 레코드 추가
insert into lend_returns
value('20170826001', 2, '잠', '20170824002', to_char('2017/08/26', 'yyyy.mm.dd'), to_char('2017/08/31', 'yyyy.mm.dd'), to_char('2017/08/26'+10, 'yyyy.mm.dd'), '0/1');

insert into lend_returns
value('20170827001', 3, '언어의 온도', '20110620080', to_char('2017/08/27', 'yyyy.mm.dd'), to_char('2017/08/28', 'yyyy.mm.dd'), to_char('2017/08/27+10', 'yyyy.mm.dd'), '0/1');

insert into lend_returns
value('20170831001', 1, '나미야 잡화점의 기적', '20121212002', to_char('2017/08/31', 'yyyy.mm.dd'), null, to_char('2017/08/31'+10, 'yyyy.mm.dd'), '0/1');


-- insert into lend_returns(lend_code, book_no, book_title, membership, lend_date, return_period, extension)
-- value (?, ?, ?, ?, ?, ?, ?)

-- select * from lend_returns where member_id = ? order by lend_date

-- select * from lend_retruns order by lend_date

--select (return_period-return_date) from lend_returns where lend_code = ?

-- update lend_returns set return_date = ? where lend_code = ?

-- update lend_returns set return_period = ?, extension = ? where lend_code = ?

-- delete from lend_returns where lend_code = ?