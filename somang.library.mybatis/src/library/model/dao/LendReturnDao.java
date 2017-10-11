package library.model.dao;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import library.model.dto.LendReturn;
import library.test.Utility;

public class LendReturnDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private static LendReturnDao instance = new LendReturnDao();
	private Utility utility = new Utility();
	int lend;
	
	private LendReturnDao() {}
	
	public static LendReturnDao getInstance() {
		return instance;
	}
	
	/**
	 * 회원의 대출/반납 정보 조회
	 * @param membership 회원코드
	 * @return 대출/반납 이력 목록
	 */
	public List<LendReturn> selectLending(String memberId) {
		SqlSession session = factory.getSqlSession();
		List<LendReturn> lending = null;
		try {
			lending = session.selectList("lendReturn.selectLend", memberId);
		} finally {
			session.close();
		}
		return lending;
	}
	
	/**
	 * 모든 대출/반납 정보 조회
	 * @return 대출/반납 이력 목록
	 */
	public List<LendReturn> selectAll() {
		SqlSession session = factory.getSqlSession();
		List<LendReturn> lending = null;
		try {
			lending = session.selectList("lendReturn.select");
		} finally {
			session.close();
		}
		return lending;
	}
	
	/**
	 * 도서대출코드를 부여하기 위한 메소드
	 * @param num 대출순번
	 * @return 형식에 맞춘 코드 반환
	 */
	public String toDFormat(int num){
		 DecimalFormat df = new DecimalFormat("000");
		 return df.format(num);
		}
	
	/**
	 * 도서 대출 정보 입력
	 * @param lending 도서대출 정보
	 * @return 입력 성공 행수, 실패시 0 반환
	 */
	public int insertLend(LendReturn lending) {
		SqlSession session = factory.getSqlSession(true);
		String lendCode = utility.dateNumber()+toDFormat(++lend);
		Date lendDate = utility.today();
		Date periodDate = utility.periodDate();
		lending.setLendCode(lendCode);
		lending.setLendDate(lendDate);
		lending.setReturnPeriod(periodDate);
		int count = 0;
		try {
			count = session.insert("lendReturn.insert", lending);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 도서 반납 정보 입력
	 * @param lendCode 대출코드
	 * @return 입력 성공 행수, 실패시 0 반환
	 */
	public int updateReturn(String id, String lendCode) {
		SqlSession session = factory.getSqlSession(true);
		int count = 0;
		Date returnDate = utility.today();
		HashMap<String, Object> returnMap = new HashMap<>();
		returnMap.put("memberId", id);
		returnMap.put("lendCode", lendCode);
		returnMap.put("returnDate", returnDate);
		try {
			count = session.update("lendReturn.updateReturn", returnMap);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 연체 여부 조회 메소드
	 * @param lendCode 대출코드
	 * @return 연체가 됐으면 true, 아니면 false
	 */
	public boolean isoverdue(String lendCode) {
		SqlSession session = factory.getSqlSession();
		boolean isOver = false;
		try {
			int overdue = session.selectOne("lendReturn.isOver", lendCode);
			if(overdue < 0) {
				isOver=true;
			}
		} finally {
			session.close();
		}
		return isOver;
	}
	
	/**
	 * 도서 반납기간 연장
	 * @param lendCode 대출코드
	 * @return 변경(연장) 성공 행수, 실패시 0 반환
	 */
	public int updatePeriod(String lendCode) {
		SqlSession session = factory.getSqlSession(true);
		HashMap<String, String> periodMap = new HashMap<>();
		periodMap.put("lendCode", lendCode);
		periodMap.put("extension", "1/1");
		int count = 0;
		if(isExtension(lendCode)) {
			System.out.println("연장 가능 횟수를 초과하셨습니다.");
			return count;
		}
		try {
			count = session.update("lendReturn.updatePeriod", periodMap);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 연장 사용여부 조회 메소드
	 * @param lendCode 대출코드
	 * @return 사용횟수가 1이면 true, 사용횟수가 0이면 false
	 */
	public boolean isExtension(String lendCode) {
		SqlSession session = factory.getSqlSession();
		boolean isExtense = false;
		try {
			String extension = session.selectOne("lendReturn.isExtension", lendCode);
			if(extension.equals("1/1"))
				isExtense = true;
		} finally {
			session.close();
		}
		return isExtense;
	}
	
	/**
	 * 대출 취소 및 대출이력 삭제
	 * @param lendCode 대출코드
	 * @return 삭제(취소) 성공 행수, 실패시 0 반환
	 */
	public int deleteLend(String lendCode) {
		SqlSession session = factory.getSqlSession(true);
		int count = 0;
		try {
			count = session.delete("lendReturn.delete", lendCode);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 도서번호에 대한 대출기록 조회
	 * @param bookNo 도서번호
	 * @return 대출기록 리스트
	 */
	public List<LendReturn> selectBook(String bookNo) {
		SqlSession session = factory.getSqlSession();
		List<LendReturn> list = null;
		try {
			list = session.selectList("lendReturn.selectBook", bookNo);
		} finally {
			session.close();
		}
		return list;
	}
}
