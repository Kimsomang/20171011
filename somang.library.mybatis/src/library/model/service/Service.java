package library.model.service;

import java.util.HashMap;
import java.util.List;

import library.model.dao.BookDao;
import library.model.dao.LendReturnDao;
import library.model.dao.MemberDao;
import library.model.dto.Book;
import library.model.dto.LendReturn;
import library.model.dto.Member;

public class Service {

	/** 회원테이블 data access 위한 객체 생성 : DAO 객체 타입 */
	private MemberDao memberDao = MemberDao.getInstance();
	private BookDao bookDao = BookDao.getInstance();
	private LendReturnDao lendDao = LendReturnDao.getInstance();
	
	/**
	 * 아이디 중복 확인을 위한 메소드
	 * @param id 사용자가 가입시도한 아이디
	 * @return 등록되어 있는 아이디이면 true, 아니면 false 반환
	 */
	public boolean duplicateId(String id) {
		if(memberDao.isMemberId(id))
			return true;
		return false;
	}
	
	/**
	 * 연락처 중복 확인을 위한 메소드
	 * @param mobile 연락처
	 * @return 중복된 값이면 true, 아니면 false 반환
	 */
	public boolean duplicateMobile(String mobile) {
		if(memberDao.isMobile(mobile))
			return true;
		return false;
	}
	
	/**
	 * 회원 등록 요청 서비스
	 * @param member 등록할 회원객체 데이터
	 * @return 데이터 추가 성공 행수, 실패시 0
	 */
	public int insert(Member member) {
		if(!duplicateId(member.getMemberId())) {
			if(!duplicateMobile(member.getMobile())) {
				return memberDao.insert(member);
			}
		}
		return 0;
	}
	
	/**
	 * 로그인을 위한 아이디, 비밀번호 조회 메소드
	 * @param id 회원아이디
	 * @param pw 비밀번호
	 * @return 로그인 성공 여부 메세지
	 */
	public String logIn(String id, String pw) {
		String password = memberDao.isLogin(id);
		if(password != null) {
			if(pw.equals(password)) {
				return "1로그인 되셨습니다.";
			} else {
				return "2아이디와 비밀번호를 다시 확인하시기 바랍니다.";
			}
		}
		return null;
	}
	
	/**
	 * 아이디를 찾기 위한 이름, 연락처 조회 메소드
	 * @param name 회원 이름
	 * @param mobile 연락처
	 * @return 아이디
	 */
	public String selectId(String name, String mobile) {
		if(memberDao.selectId(name, mobile) != null)
			return memberDao.selectId(name, mobile);
		
		return null;
	}
	
	/**
	 * 비밀번호를 찾기 위한 아이디 조회 메소드
	 * @param id 아이디
	 * @return 비밀번호
	 */
	public StringBuffer selectPw(String id, String name, String mobile) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		if(memberDao.selectPw(id, name, mobile) != null) {
			String pw = memberDao.selectPw(id, name, mobile);
			sb.append(pw);

			for (int i = 4; i < sb.length(); i++) {
				sb2.append('*');
			}
			sb.replace(4, sb.length(), sb2.toString());
			return sb;
		}
		return null;
	}
	
	/**
	 * 회원정보 조회를 위한 메소드
	 * @param id 회원 아이디
	 * @return 회원 정보
	 */
	public Member selectUser(String id) {
		Member member = memberDao.selectUser(id);
		
		if(member != null) {
			return member;
		}
		return null;
	}
	
	/**
	 * 회원 정보 전체 조회를 위한 메소드
	 * @return 회원정보 리스트
	 */
	public List<Member> selectAll(){
		List<Member> member = memberDao.selectAll();
		return member;
	}
	
	/**
	 * 비밀번호 변경 요청을 위한 메소드
	 * @param id 아이디
	 * @param pw 비밀번호
	 * @return 비밀번호 변경 성공하면 true, 실패하면 false 반환
	 */
	public boolean updatePw(String id, String pw) {
		int update = memberDao.updatePw(id, pw);
		if(update != 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 비밀번호 일치 여부 조회를 위한 메소드
	 * @param id 아이디
	 * @param pw 비밀번호
	 * @return 일치하면 true, 일치하지 않으면 false;
	 */
	public boolean checkPw(String id, String pw) {
		String password = memberDao.isLogin(id);
		if(password != null) {
			if(password.equals(pw)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 연락처 변경 요청을 위한 메소드
	 * @param id 아이디
	 * @param mobile 연락처
	 * @return 성공시 true, 실패시 false 반환
	 */
	public boolean updateMobile(String id, String mobile) {
		int update = memberDao.updateMobile(id, mobile);
		if(update != 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 주소 변경 요청을 위한 메소드
	 * @param id 아이디
	 * @param address 주소
	 * @return 성공시 true, 실패시 false 반환
	 */
	public boolean updateAddress(String id, String address) {
		int update = memberDao.updateAddress(id, address);
		if(update != 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 회원 탈퇴 요청을 위한 메소드
	 * @param id 아이디
	 * @return 성공시 true, 실패시 false 반환
	 */
	public boolean withdrawUser(String id) {
		if(memberDao.isMemberId(id)) {
			List<LendReturn> list = lendDao.selectLending(id);
			if(list==null) {
				if(memberDao.withdrawUser(id)!=0) {
					return true;
				} else {
					return false;
				}
			}
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getReturnDate() == null) {
					System.out.println("대출하신 책을 반납하여주세요.");
					return false;
				}
				
				if(memberDao.deleteUser(id)!=0) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * 도서 등록 요청을 위한 메소드
	 * @param book 도서정보
	 * @return 등록 성공하면 true, 실패하면 false
	 */
	public boolean insertBook(Book book) {
		if(bookDao.insert(book) != 0)
			return true;
		
		return false;
	}

	/**
	 * 도서 통합검색을 위한 메소드
	 * @param searchInfo
	 * @return 검색한 리스트
	 */
	public List<Book> selectKeyword(HashMap<String, String> searchInfo) {
		return bookDao.selectKeyword(searchInfo);
	}
	/**
	 * 도서 목록 조회 요청을 위한 메소드
	 * @return 도서 정보 목록
	 */
	public List<Book> selectBooks(){
		List<Book> book = bookDao.selectBooks();
		return book;
	}
	
	/**
	 * 도서 소장위치 변경 요청을 위한 메소드
	 * @param bookNo 도서번호
	 * @param location 도서 소장위치
	 * @return 변경 성공시 true, 실패시 false 반환
	 */
	public boolean updateLoc(String bookNo, String location) {
		int update = bookDao.updateLoc(bookNo, location);
		if(update != 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 도서 삭제 요청을 위한 메소드
	 * @param bookNo 도서 번호
	 * @return 삭제 성공시 true, 실패시 false 반환
	 */
	public boolean deleteBook(String bookNo) {
		int delete = bookDao.deleteBook(bookNo);
		if(delete != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 사용자의 대출/반납 이력 조회 메소드
	 * @param memberId 아이디
	 * @return 대출/반납 이력 목록
	 */
	public List<LendReturn> selectLending(String memberId){
		List<LendReturn> lending = lendDao.selectLending(memberId);
		return lending;
	}
	
	/**
	 * 모든 대출/반납 이력 조회 메소드
	 * @return 대출/반납 이력 목록
	 */
	public List<LendReturn> selectAllLending(){
		List<LendReturn> lending = lendDao.selectAll();
		return lending;
	}
	
	/**
	 * 대출 정보 입력 메소드
	 * @param lending 대출정보 객체
	 * @return 성공시 true, 실패시 false
	 */
	public boolean insertLending(LendReturn lending) {
		if(memberDao.isCondition(lending.getMemberId())) {
			if(memberDao.updateLending(lending.getMemberId()) != 0) {
				lendDao.insertLend(lending);
				return true;
			} else { return false; }
		}
		return false;
	}
	
	/**
	 * 반납 정보 입력 메소드
	 * @param id 아이디
	 * @param lendCode 대출코드
	 * @return 반납 성공시 true, 실패시 false
	 */
	public boolean updateReturn(String id, String lendCode) {
		if(lendDao.updateReturn(id, lendCode) != 0) {
			memberDao.updateReturn(id);
			if(lendDao.isoverdue(lendCode)) {
				if(memberDao.updateCondition(id)!=0)
					System.out.println("연체기간만큼 대출불가 상태가 됩니다.");
				else
					System.out.println("대출불가 설정에 실패했습니다.");
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 반납기간 연장 메소드
	 * @param lendCode 대출코드
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updatePeriod(String lendCode) {
		if(lendDao.updatePeriod(lendCode) != 0)
			return true;
		return false;
	}
	
	/**
	 * 대출 취소 메소드
	 * @param lendCode 대출코드
	 * @return 성공시 true, 실패시 false
	 */
	public boolean deleteLend(String lendCode) {
		if(lendDao.deleteLend(lendCode) != 0)
			return true;
		return false;
	}
}
