package library.controller;

import java.util.List;

import library.model.dto.Book;
import library.model.dto.LendReturn;
import library.model.dto.Member;
import library.model.service.Service;

public class Controller {
	
	private Service service = new Service();
	
	/**
	 * 회원가입시 아이디 중복 검증 메소드
	 * @param id
	 * @return 오류발생 및 중복시 true, 중복이 아니면 false 반환
	 */
	public boolean isMemberId(String id) {
		if(id == null || id.trim().length() < 5 || id.trim().length() > 20) {
			System.out.println("Error : 아이디 길이 오류(영문/숫자 5~20까지)\n");
			return true;
		}
		if(id.matches("[0-9a-zA-Z]")) {
			System.out.println("Error : 아이디 오류(영문/숫자만 가능)\n");
			return true;
		}
		if(service.duplicateId(id)) {
			System.out.println("이미 등록되어 있는 아이디입니다.");
			return true;
		} else {
			System.out.println("사용 가능한 아이디입니다.");
			return false;
		}
	}
	
	/**
	 * 연락처 중복 검증 메소드
	 * @param mobile
	 * @return 오류발생 및 중복시 true, 중복이 아니면 false 반환
	 */
	public boolean isMobile(String mobile) {
		if(mobile == null || mobile.trim().length() == 0) {
			System.out.println("Error : 연락처가 입력되지 않음");
			return true;
		}
		if(service.duplicateMobile(mobile)) {
			System.out.println("이미 등록되어 있는 연락처입니다.");
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 회원가입 등록 데이터 검증 메소드
	 * @param member 회원 객체
	 * @return 데이터 요청처리에 대한 메시지 반환(형식에 맞지 않을 시 Error 메시지 반환)
	 */
	public String insert(Member member) {
		StringBuilder value = new StringBuilder();
		
		if(member == null) {
			value.append("Error : 변수가 존재하지 않음");
			return value.toString();
		}
		
		String password = member.getPassword();
		String name = member.getName();
		String birth = member.getBirth();
		String address = member.getAddress();
		
		if(password == null || password.trim().length() == 0 || password.trim().length() < 5
				|| password.trim().length() > 20) {
			value.append("Error : 비밀번호 길이 오류(영문/숫자 5~20까지)\n");
		} else if (password.matches("[0-9a-zA-Z]")) {
			value.append("Error : 비밀번호 오류(영문/숫자만 가능)");
		}
		
		if(name == null || name.trim().length() == 0 || name.trim().length() > 10)
			value.append("Error : 이름 길이 초과(한글 10자리까지 입력 가능)\n");
		
		if(birth == null || birth.trim().length() == 0)
			value.append("Error : 생년월일이 입력되지 않음\n");
		
		if(address == null || address.trim().length() == 0)
			value.append("Error : 주소가 입력되지 않음\n");
		
		if(value.length() != 0)
			return value.toString();
		
		int result = service.insert(member);
		
		if(result == 1) {
			return "Success : 회원가입이 정상 완료되었습니다.";
		} else {
			return "Fail : 회원가입이 정상적으로 이루어지지 않았습니다.";
		}
	}
	
	/**
	 * 로그인을 위한 아이디 조회 메소드
	 * @param id 회원아이디
	 * @param pw 비밀번호
	 * @return 아이디가 존재하면 true, 존재하지않으면 false
	 */
	public boolean logIn(String id, String pw) {
		if(service.logIn(id, pw) == null) {
			System.out.println("아이디와 비밀번호를 다시 확인하시기 바랍니다.");
			return false;
		} else {
			if(service.logIn(id, pw).startsWith("1")) {
				System.out.println(service.logIn(id, pw).substring(1));
				return true;
			} else {
				System.out.println(service.logIn(id, pw).substring(1));
				return false;
			}
		}
	}
	
	/**
	 * 아이디찾기를 위한 조회 메소드
	 * @param name 이름
	 * @param mobile 연락처
	 * @return 아이디
	 */
	public String selectId(String name, String mobile) {
		return service.selectId(name, mobile);
	}
	
	/**
	 * 비밀번호 찾기를 위한 조회 메소드
	 * @param id 아이디
	 * @param name 이름
	 * @return 비밀번호 앞4자리 + secure코드
	 */
	public StringBuffer selectPw(String id, String name, String mobile) {
		return service.selectPw(id, name, mobile);
	}
	
	/**
	 * 회원 정보 조회 메소드
	 * @param id 아이디
	 */
	public Member selectUser(String id) {
		return service.selectUser(id);
	}
	
	/**
	 * 회원 정보 전체 조회 메소드
	 */
	public void selectAll() {
		List<Member> member = service.selectAll();
		if(member != null) {
			for(int i = 0; i < member.size(); i++) {
				System.out.println(member.get(i));
			}
		} else {
			System.out.println("회원이 존재하지 않습니다.");
		}
	}
	
	/**
	 * 비밀번호 변경 메소드
	 * @param id 회원 아이디
	 * @param pw 변경할 비밀번호
	 * @return 비밀번호 변경 여부 메세지
	 */
	public String changePw(String id, String pw) {
		if(service.updatePw(id, pw)) {
			return "비밀번호가 변경되었습니다.";
		} else {
			return "비밀번호 변경에 실패하였습니다.";
		}
	}
	
	/**
	 * 비밀번호 확인 메소드
	 * @param id 회원아이디
	 * @param pw 비밀번호
	 * @return 일치하면 true, 일치하지 않으면 false 반환
	 */
	public boolean checkPw(String id, String pw) {
		if(service.checkPw(id, pw)) {
			return true;
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return false;
		}
	}
	
	/**
	 * 연락처 변경 메소드
	 * @param id 회원 아이디
	 * @param mobile 변경할 연락처
	 * @return 연락처 변경 여부 메세지
	 */
	public String changeMobile(String id, String mobile) {
		if(service.duplicateMobile(mobile)) {
			return "이미 등록되어 있는 연락처입니다.\n연락처는 중복 등록할 수 없습니다.";
		} else {
			if(service.updateMobile(id, mobile)) {
				return "연락처가 변경되었습니다.";
			} else {
				return "연락처 변경에 실패하였습니다.";
			}
		}
	}
	
	/**
	 * 주소 변경 메소드
	 * @param id 회원 아이디
	 * @param address 변경할 주소
	 * @return 주소 변경 여부 메세지
	 */
	public String changeAddress(String id, String address) {
		if(service.updateAddress(id, address)) {
			return "주소가 변경되었습니다.";
		} else {
			return "주소 변경에 실패하였습니다.";
		}
	}
	
	/**
	 * 회원 탈퇴 메소드
	 * @param id 회원 아이디
	 * @return 회원탈퇴 성공시 true, 실패시 false
	 */
	public boolean withdrawUser(String id) {
		if(service.withdrawUser(id)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 도서 등록 데이터 검증 메소드
	 * @param book 도서 객체
	 * @return 데이터 요청처리에 대한 메시지 반환(형식에 맞지 않을 시 Error 메시지 반환)
	 */
	public String insertBook(Book book) {
		StringBuilder value = new StringBuilder();
		
		if(book == null) {
			value.append("Error : 변수가 존재하지 않음");
			return value.toString();
		}
		
		String title = book.getBookTitle();
		String author = book.getAuthor();
		String publisher = book.getPublisher();
		String publishingDate = book.getPublishingDate();
		String isbn = book.getIsbn();
		String location = book.getLocation();
		String typeCode = book.getTypeCode();
		String category = book.getCategory();
		
		if(title == null || title.trim().length() == 0)
			value.append("Error : 도서명이 입력되지 않음\n");
		
		if(author == null || author.trim().length() == 0 || author.trim().length() > 10)
			value.append("Error : 저자명 길이 초과(한글 15자리까지 입력 가능)\n");
		
		if(publisher == null || publisher.trim().length() == 0)
			value.append("Error : 출판사가 입력되지 않음\n");
		
		if(publishingDate == null || publishingDate.trim().length() == 0)
			value.append("Error : 출판일이 입력되지 않음\n");
		
		if(isbn == null || isbn.trim().length() == 0)
			value.append("Error : isbn이 입력되지 않음\n");
		
		if(location == null || location.trim().length() == 0)
			value.append("Error : 소장 위치가 입력되지 않음\n");
		
		if(typeCode == null || typeCode.trim().length() == 0)
			value.append("Error : 청구기호가 입력되지 않음\n");
		
		if(category == null || category.trim().length() == 0)
			value.append("Error : 분류번호가 입력되지 않음\n");
		
		if(value.length() != 0)
			return value.toString();
		
		boolean result = service.insertBook(book);
		
		if(result) {
			return "Success : 도서등록이 정상 완료되었습니다.";
		} else {
			return "Fail : 도서등록이 정상적으로 이루어지지 않았습니다.";
		}
	}
	
	/**
	 * 도서명 검색 메소드
	 * @param title 도서명
	 */
	public void selectTitle(String title) {
		List<Book> book = service.selectTitle(title);
		if(book != null) {
			for(int i = 0; i < book.size(); i++) {
			System.out.println(book.get(i));
			}
		} else {
			System.out.println("찾으시는 책이 없습니다.");
		}
	}
	
	/**
	 * 저자 검색 메소드
	 * @param author 저자
	 */
	public void selectAuthor(String author) {
		List<Book> book = service.selectAuthor(author);
		if(book != null) {
			for(int i = 0; i < book.size(); i++) {
				System.out.println(book.get(i));
			}
		} else {
			System.out.println("찾으시는 저자가 존재하지 않습니다.");
		}
	}
	
	/**
	 * 도서 목록 조회 메소드
	 */
	public void selectBooks() {
		List<Book> book = service.selectBooks();
		if(book != null) {
			for(int i = 0; i < book.size(); i++) {
				System.out.println(book.get(i));
			}
		} else {
			System.out.println("도서정보가 존재하지 않습니다.");
		}
	}
	
	/**
	 * 도서 소장위치 변경 메소드
	 * @param bookNo 도서번호
	 * @param location 소장위치
	 * @return 소장위치 변경 여부 메세지
	 */
	public String updateLoc(String bookNo, String location) {
		if(service.updateLoc(bookNo, location)) {
			return "소장위치가 변경되었습니다.";
		} else {
			return "소장위치 변경에 실패하였습니다.";
		}
	}
	
	/**
	 * 도서 정보 삭제 메소드
	 * @param bookNo 도서번호
	 * @return 도서 정보 삭제 여부 메세지
	 */
	public String deleteBook(String bookNo) {
		if(service.deleteBook(bookNo)) {
			return "도서 정보가 삭제되었습니다.";
		} else {
			return "도서 정보 삭제에 실패하였습니다.";
		}
	}
	
	/**
	 * 사용자의 대출/반납 이력 조회 메소드
	 * @param memberId 아이디
	 */
	public void selectLending(String memberId) {
		List<LendReturn> lending = service.selectLending(memberId);
		for(int i = 0; i < lending.size(); i++) {
			System.out.println(lending.get(i));
		}
	}
	
	/**
	 * 모든 대출/반납 이력 조회 메소드
	 */
	public void selectAllLending() {
		List<LendReturn> lending = service.selectAllLending();
		for(int i = 0; i < lending.size(); i++) {
			System.out.println(lending.get(i));
		}
	}
	
	/**
	 * 대출 요청 메소드
	 * @param lending 대출정보 객체
	 * @return 도서 대출 입력 성공 여부 메세지
	 */
	public String insertLending(LendReturn lending) {
		if(service.insertLending(lending))
			return "대출 요청이 성공적으로 처리되었습니다.";
		else
			return "대출 요청에 실패했습니다.";
	}
	
	/**
	 * 반납 요청 메소드
	 * @param id 아이디
	 * @param lendCode 대출코드
	 * @return 반납 요청 성공 여부 메세지
	 */
	public String updateReturn(String id, String lendCode) {
		if(service.updateReturn(id, lendCode)) {
			return "반납 요청이 성공적으로 처리되었습니다.";
		} else {
			return "반납 요청에 실패했습니다.";
		}
	}
	
	/**
	 * 연장 요청 메소드
	 * @param lendCode 대출코드
	 * @return 연장 요청 성공 여부 메세지
	 */
	public String updatePeriod(String lendCode) {
		if(service.updatePeriod(lendCode)) {
			return "반납 기한이 연장되었습니다.";
		} else {
			return "기한 연장에 실패했습니다.";
		}
	}
	
	/**
	 * 대출 취소 요청 메소드
	 * @param lendCode 대출코드
	 * @return 대출 취소 성공 여부 메세지
	 */
	public String deleteLend(String lendCode) {
		if(service.deleteLend(lendCode)) {
			return "대출이 취소되었습니다.";
		} else {
			return "대출 취소에 실패했습니다.";
		}
	}
}
