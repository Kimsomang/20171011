package library.view;

import java.util.Scanner;

import library.controller.Controller;
import library.model.dto.Book;
import library.model.dto.LendReturn;
import library.model.dto.Member;

public class MenuView {
	
	Scanner scan = new Scanner(System.in);
	Controller controller = new Controller();
	
	/**
	 * 메인 메뉴 출력 메소드
	 * @return 메뉴 입력값
	 */
	public String mainMenu() {
		System.out.println("\n*****소망도서관*****");
		System.out.println(" 1. 회원가입");
		System.out.println(" 2. 로그인");
		System.out.println(" 3. 아이디찾기");
		System.out.println(" 4. 비밀번호찾기");
		System.out.println(" 5. 도서 검색");
		System.out.println(" 6. 도서목록조회");
		System.out.println(" 7. 종료");
		System.out.println("********************");
		System.out.print("메뉴를 선택하세요. : ");
		String num = scan.nextLine();
		
		if(!num.matches("[1-7]")) {
			System.out.println("잘못된 수를 입력하셨습니다.");
			num = "0";
		}
		return num;
	}
	
	/**
	 * 사용자메뉴 출력 메소드
	 * @param id 회원아이디
	 * @return 메뉴 입력값
	 */
	public String userMenu(String id) {
		System.out.println("\n********************");
		System.out.println(id + "님 환영합니다.");
		System.out.println("********************");
		System.out.println(" 1. 내정보 확인");
		System.out.println(" 2. 도서검색");
		System.out.println(" 3. 도서 목록 조회");
		System.out.println(" 4. 대출/반납 확인");
		System.out.println(" 5. 내정보 변경");
		System.out.println(" 6. 회원탈퇴");
		System.out.println(" 7. 로그아웃");
		System.out.println("********************");
		System.out.print("메뉴를 선택하세요. : ");
		String num = scan.nextLine();
		
		if(!num.matches("[1-7]")) {
			System.out.println("잘못된 수를 입력하셨습니다.");
			num = "0";
		}
			
		return num;
	}
	
	/**
	 * 관리자메뉴 출력 메소드
	 * @return 메뉴 입력값
	 */
	public String managerMenu() {
		System.out.println("\n********************");
		System.out.println(" 1. 회원정보 조회");
		System.out.println(" 2. 도서 등록하기");
		System.out.println(" 3. 도서 검색");
		System.out.println(" 4. 도서 목록 조회");
		System.out.println(" 5. 도서 대출/반납");
		System.out.println(" 6. 도서 위치 수정");
		System.out.println(" 7. 도서 자료 삭제");
		System.out.println(" 8. 비밀번호 변경");
		System.out.println(" 9. 로그아웃");
		System.out.println("********************");
		System.out.print("메뉴를 선택하세요. : ");
		String num = scan.nextLine();
		
		if(!num.matches("[1-9]")) {
			System.out.println("잘못된 수를 입력하셨습니다.");
			num = "0";
		}
		return num;
	}
	
	/**
	 * 회원가입 메뉴 출력 메소드
	 */
	public void joining() {
		System.out.println("\n********************");
		String id;
		do {
			System.out.print("아이디 : ");
			id = scan.nextLine();
		} while (controller.isMemberId(id));
		
		System.out.print("\n비밀번호 : ");
		String password = scan.nextLine();
		System.out.print("이름 : ");
		String name = scan.nextLine();
		System.out.print("생년월일(ex:1995.01.23) : ");
		String birth = scan.nextLine();
		String mobile;
		do {
			System.out.print("연락처('-'포함) : ");
			mobile = scan.nextLine();
		} while (controller.isMobile(mobile));
		
		System.out.print("\n주소 : ");
		String address = scan.nextLine();
		
		Member member = new Member(id, password, name, birth, mobile, address);
		System.out.println(controller.insert(member));
	}
	
	/**
	 * 로그인 메뉴 출력 메소드
	 * @return 로그인 된 아이디값
	 */
	public String logIn() {
		System.out.println("\n********************");
		System.out.print("아이디 : ");
		String id = scan.nextLine();
		System.out.print("비밀번호 : ");
		String password = scan.nextLine();
		
		if(controller.logIn(id, password)) {
			return id;
		} else {
			return null;
		}
	}
	
	/**
	 * 아이디찾기 메뉴 출력 메소드
	 */
	public void selectId() {
		System.out.println("\n********************");
		System.out.print("이름 : ");
		String name = scan.nextLine();
		System.out.print("연락처 : ");
		String mobile = scan.nextLine();
		
		String id = controller.selectId(name, mobile);
		
		if(id != null) {
			System.out.println("찾으시는 아이디는 " + id + " 입니다.");
		} else {
			System.out.println("찾으시는 아이디가 없습니다.");
		}
		
		System.out.println("메인으로 돌아갑니다.");
	}
	
	/**
	 * 비밀번호 찾기 메뉴 출력 메소드
	 */
	public void selectPw() {
		System.out.println("\n********************");
		System.out.print("아이디 : ");
		String id = scan.nextLine();
		System.out.print("이름 : ");
		String name = scan.nextLine();
		System.out.print("연락처 : ");
		String mobile = scan.nextLine();
		
		StringBuffer pw = controller.selectPw(id, name, mobile);
		
		if(pw != null) {
			System.out.println("찾으시는 비밀번호는 " + pw + " 입니다.");
		} else {
			System.out.println("아이디가 존재하지 않습니다.");
		}
		
		System.out.println("메인으로 돌아갑니다.");
	}
	
	/**
	 * 회원의 정보 조회 출력 메소드
	 * @param id 회원 아이디
	 */
	public void selectUser(String id) {
		System.out.println("\n********************");
		Member member = controller.selectUser(id);
		if(member != null)
			System.out.println(member.toString());
	}
	
	/**
	 * 회원정보 목록 조회 출력 메소드
	 */
	public void selectUsers() {
		System.out.println("\n********************");
		controller.selectAll();
	}
	
	/**
	 * 회원의 정보 수정 메뉴 출력 메소드
	 * @param id 회원 아이디
	 * @return 수정하고자 하는 메뉴 번호 입력값
	 */
	public String changeUser(String id) {
		System.out.println("\n********************");
		System.out.println(" 1. 비밀번호 변경");
		System.out.println(" 2. 연락처 변경");
		System.out.println(" 3. 주소 변경");
		System.out.println("\n********************");
		System.out.print("메뉴를 선택하세요. : ");
		String num = scan.nextLine();
		return num;
	}
	
	/**
	 * 비밀번호 변경 메소드
	 * @param id 회원 아이디
	 */
	public void changePw(String id) {
		boolean flag;
		
		do {
			System.out.println("\n********************");
			System.out.print("비밀번호 입력 : ");
			String pw = scan.nextLine();
		
			if(!controller.checkPw(id, pw)) {
				flag = true;
			} else {
				System.out.print("변경할 비밀번호 : ");
				String password = scan.nextLine();
				controller.changePw(id, password);
				flag = false;
			}
		} while(flag);
	}
	
	/**
	 * 연락처 변경 메소드
	 * @param id 회원 아이디
	 */
	public void changeMobile(String id) {
		System.out.println("\n********************");
		System.out.print("변경할 연락처 : ");
		String mobile = scan.nextLine();
		System.out.println(controller.changeMobile(id, mobile));
	}
	
	/**
	 * 주소 변경 메소드
	 * @param id 회원 아이디
	 */
	public void changeAddress(String id) {
		System.out.println("\n********************");
		System.out.print("변경할 주소 : ");
		String address = scan.nextLine();
		System.out.println(controller.changeAddress(id, address));
	}
	
	/**
	 * 회원 탈퇴 메소드
	 * @param id 회원 아이디
	 */
	public String withdrawUser(String id) {
		System.out.println("\n********************");
		System.out.print("정말 탈퇴하시겠습니까?(Y/N) : ");
		String answer = scan.nextLine();

		if(answer.equals("y") || answer.equals("Y")) {
			if(controller.withdrawUser(id)) {
				System.out.println("탈퇴되었습니다.");
				return "7";
			} else {
				System.out.println("회원탈퇴에 실패했습니다.");
				return "0";
			}
		} else {
			System.out.println("메뉴화면으로 돌아갑니다.");
			return "0";
		}
	}
	
	/**
	 * 도서등록 메뉴 출력 메소드
	 */
	public void insertBook() {
		System.out.println("\n********************");
		System.out.print("도서명 : ");
		String title = scan.nextLine();
		System.out.print("저자명 : ");
		String author = scan.nextLine();
		System.out.print("출판사 : ");
		String publisher = scan.nextLine();
		System.out.print("출판일(ex:1995.01.23) : ");
		String publishingDate = scan.nextLine();
		System.out.print("ISBN : ");
		String isbn = scan.nextLine();
		System.out.print("소장위치 : ");
		String location = scan.nextLine();
		System.out.print("청구기호 : ");
		String typeCode = scan.nextLine();
		System.out.print("분류번호 : ");
		String category = scan.nextLine();
		
		Book book = new Book(title, author, publisher, publishingDate, isbn, location, typeCode, category);
		System.out.println(controller.insertBook(book));
	}
	
	/**
	 * 도서검색 조회 출력 메소드
	 */
	public void selectBook() {
		System.out.println("\n********************");
		System.out.println(" 1. 도서명 검색");
		System.out.println(" 2. 저자 검색");
		System.out.println("\n********************");
		System.out.print("검색 설정 선택 : ");
		String select = scan.nextLine();
		
		if(select.equals("1")) {
			System.out.println("\n********************");
			System.out.print("검색하고자 하는 도서명을 입력하세요. : ");
			String title = scan.nextLine();
			controller.selectTitle(title);
		} else if(select.equals("2")) {
			System.out.println("\n********************");
			System.out.print("검색하고자 하는 저자를 입력하세요. : ");
			String author = scan.nextLine();
			controller.selectAuthor(author);
		} else {
			System.out.println("잘못된 값을 입력하셨습니다."
					+ "\n메뉴화면으로 돌아갑니다.");
		}
	}
	
	/**
	 * 도서 목록 조회 출력 메소드
	 */
	public void selectBooks() {
		System.out.println("\n********************");
		controller.selectBooks();
	}
	
	/**
	 * 도서 소장위치 변경 메뉴 출력 메소드
	 */
	public void updateLoc() {
		System.out.println("\n********************");
		System.out.print("위치 변경할 도서번호 : ");
		String bookNo = scan.nextLine();
		System.out.print("변경할 위치 : ");
		String location = scan.nextLine();
		System.out.println(controller.updateLoc(bookNo, location));
	}
	
	/**
	 * 도서 정보 삭제 메뉴 출력 메소드
	 */
	public void deleteBook() {
		System.out.println("\n********************");
		System.out.print("삭제할 도서번호 : ");
		String bookNo = scan.nextLine();
		scan.nextLine();
		System.out.println(controller.deleteBook(bookNo));
	}
	
	/**
	 * 도서 대출/반납 메뉴 출력 메소드
	 * @return
	 */
	public String lendingMenu() {
		System.out.println("\n********************");
		System.out.println(" 1. 도서대출");
		System.out.println(" 2. 도서반납");
		System.out.println(" 3. 기한연장");
		System.out.println(" 4. 대출취소");
		System.out.println(" 5. 목록조회");
		System.out.println("********************");
		System.out.print("메뉴 선택 : ");
		String select = scan.nextLine();
		return select;
	}
	
	/**
	 * 사용자의 대출/반납 이력 조회 메소드
	 * @param id 아이디
	 */
	public void selectLending(String id) {
		System.out.println("\n********************");
		controller.selectLending(id);
	}
	
	/**
	 * 모든 대출/반납 이력 조회 메소드
	 */
	public void selectAllLending() {
		System.out.println("\n********************");
		controller.selectAllLending();
	}
	
	/**
	 * 대출 메뉴 출력 메소드
	 */
	public void insertLend() {
		System.out.println("\n********************");
		System.out.print("도서번호 : ");
		String bookNo = scan.nextLine();
		System.out.print("도서명 : ");
		String title = scan.nextLine();
		System.out.print("회원아이디 : ");
		String id = scan.nextLine();
		
		LendReturn lending = new LendReturn(bookNo, title, id);
		System.out.println(controller.insertLending(lending));
	}
	
	/**
	 * 반납 메뉴 출력 메소드
	 */
	public void updateReturn() {
		System.out.println("\n********************");
		System.out.print("회원 아이디 : ");
		String id = scan.nextLine();
		System.out.print("대출코드 : ");
		String lendCode = scan.nextLine();
		
		System.out.println(controller.updateReturn(id, lendCode));
	}
	
	/**
	 * 기한 연장 메뉴 출력 메소드
	 */
	public void updatePeriod() {
		System.out.println("\n********************");
		System.out.print("대출코드 : ");
		String lendCode = scan.nextLine();
		
		System.out.println(controller.updatePeriod(lendCode));
	}
	
	/**
	 * 대출취소 메뉴 출력 메소드
	 */
	public void deleteLend() {
		System.out.println("\n********************");
		System.out.print("대출코드 : ");
		String lendCode = scan.nextLine();
		
		System.out.println(controller.deleteLend(lendCode));
	}
}
