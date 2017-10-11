package library.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import library.model.dto.Book;

public class BookDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private static BookDao instance = new BookDao();
//	private ResourceBundle queryBundle;
	
	private BookDao() {
//		queryBundle = ResourceBundle.getBundle("conf/query");
	}
	
	public static BookDao getInstance() {
		return instance;
	}
	
	/**
	 * 도서 등록 메소드
	 * @param book 도서 정보
	 * @return 등록 성공 행수, 실패시 0
	 */
	public int insert(Book book) {
		SqlSession session = factory.getSqlSession(true);
		int count = 0;
		try {
			count = session.insert("book.insert", book);
		} finally {
			session.close();
		}
		return count;
	}

	/**
	 * 도서 통합검색 메소드
	 * @param searchInfo
	 * @return 검색 리스트
	 */
	public List<Book> selectKeyword(HashMap<String, String> searchInfo) {
		SqlSession session = factory.getSqlSession();
		List<Book> books = null;
		
		try {
			books = session.selectList("book.selectKeyword", searchInfo);
		} finally {
			session.close();
		}
		return books;
	}
	
	/**
	 * 도서 목록 조회 메소드
	 * @return 도서 정보 목록
	 */
	public List<Book> selectBooks(){
		SqlSession session = factory.getSqlSession();
		List<Book> books = null;
		try {
			books = session.selectList("book.selectBooks");
		} finally {
			session.close();
		}
		return books;
	}
	
	/**
	 * 도서 소장 위치 변경 메소드
	 * @param bookNo 도서번호
	 * @param location 소장 위치
	 * @return 성공시 변경 성공 행수 반환, 실패시 0
	 */
	public int updateLoc(String bookNo, String location) {
		SqlSession session = factory.getSqlSession(true);
		HashMap<String, String> bookMap = new HashMap<>();
		bookMap.put("bookNo", bookNo);
		bookMap.put("location", location);
		int count = 0;
		try {
			count = session.update("book.updateLocation", bookMap);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 도서 자료상태 변경 메소드
	 * @param bookNo 도서번호
	 * @param condition 자료상태
	 * @return 성공시 변경 성공 행수 반환, 실패시 0
	 */
	public int updateCondition(String bookNo, String condition) {
		SqlSession session = factory.getSqlSession(true);
		HashMap<String, String> bookMap = new HashMap<>();
		bookMap.put("bookNo", bookNo);
		bookMap.put("condition", condition);
		int count = 0;
		try {
			count = session.update("book.updateCondition", bookMap);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 도서 삭제 메소드
	 * @param bookNo 도서번호
	 * @return 성공시 삭제 성공 행수, 실패시 0
	 */
	public int deleteBook(String bookNo) {
		SqlSession session = factory.getSqlSession(true);
		int count = 0;
		try { 
			count = session.delete("book.delete", bookNo);
		} finally {
			session.close();
		}
		return count;
	}
}
