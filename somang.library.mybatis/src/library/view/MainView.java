package library.view;

public class MainView {

	public static void main(String[] args) {
	
		MenuView menu = new MenuView();
		String mainNum;
		
		do {
			mainNum = menu.mainMenu();
			
			switch(mainNum) {
			case "1" : //회원가입
				menu.joining();
				break;
			
			case "2" : //로그인
				String id = menu.logIn();
				
				if(id == null) {
					System.out.println("메인으로 돌아갑니다.");
					break;
				}
				
				if(id.equals("Manager")) { //관리자 메뉴
					String manager;
					do {
						manager = menu.managerMenu();
						
						switch(manager) {
						case "1" : //회원 정보 조회
							menu.selectUsers();
							break;
						
						case "2" : //도서 등록하기
							menu.insertBook();
							break;
						
						case "3" : //도서 검색
							menu.selectBook();
							break;
						
						case "4" : //도서 목록 조회
							menu.selectBooks();
							break;
						
						case "5" : //도서 대출/반납
							String number = menu.lendingMenu();
							switch(number) {
							case "1" : //대출
								menu.insertLend();
								break;
							case "2" : //반납
								menu.updateReturn();
								break;
							case "3" : //연장
								menu.updatePeriod();
								break;
							case "4" : //취소
								menu.deleteLend();
								break;
							case "5" : //목록조회
								menu.selectAllLending();
								break;
							}
							break;
						
						case "6" : //도서 자료 수정
							menu.updateLoc();
							break;
						
						case "7" : //도서 자료 삭제
							menu.deleteBook();
							break;
						
						case "8" : //비밀번호 변경
							menu.changePw(id);
						}
					} while(!manager.equals("9"));
				
				} else if(!id.isEmpty()) { //사용자 메뉴
					String user;
					
					do {
						user = menu.userMenu(id);
						
						switch(user) {
						case "1" : //내정보 확인
							menu.selectUser(id);
							break;
						
						case "2" : //도서검색
							menu.selectBook();
							break;
						
						case "3" : //도서 목록 조회
							menu.selectBooks();
							break;
						
						case "4" : //대출/반납 확인
							menu.selectLending(id);
							break;
						
						case "5" : //내정보 변경
							String update = menu.changeUser(id);
							switch(update) {
							case "1" : //비밀번호 변경
								menu.changePw(id);
								break;
							case "2" : //연락처 변경
								menu.changeMobile(id);
								break;
							case "3" : //주소 변경
								menu.changeAddress(id);
							}
							break;
							
						case "6" : //회원탈퇴
							user = menu.withdrawUser(id);
						}
					} while(!user.equals("7"));
				}
				break;
			
			case "3" : //아이디찾기
				menu.selectId();
				break;
				
			case "4" : //비밀번호찾기
				menu.selectPw();
				break;
				
			case "5" : //도서검색
				menu.selectBook();
				break;
			case "6" : //도서 목록 조회
				menu.selectBooks();
			}
		
		} while(!mainNum.equals("7"));
		
		System.out.println("종료합니다.");
	}
	
}
