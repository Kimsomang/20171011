package library.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import library.model.dto.Member;

public class MemberDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private static MemberDao instance = new MemberDao();
	
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	/**
	 * 회원 등록 메소드
	 * @param member 회원객체
	 * @return 실패시 0, 성공시 등록된 레코드 행수 반환
	 */
	public int insert(Member member) {
		SqlSession session = factory.getSqlSession(true);
		int count = 0;
		try {
			count = session.insert("member.insert", member);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 회원아이디 중복 조회 메소드
	 * @param id 회원아이디
	 * @return 존재하면 true, 존재하지 않으면 false 반환
	 */
	public boolean isMemberId(String memberId) {
		SqlSession session = factory.getSqlSession();
		boolean isId = false;
		String duplicate = null;
		try {
			duplicate = session.selectOne("member.isId", memberId);
			if(duplicate != null) {
				isId = true;
			}
		} finally {
			session.close();
		}
		return isId;
	}
	
	/**
	 * 연락처 중복 조회 메소드
	 * @param mobile 연락처
	 * @return 존재하면 true, 존재하지 않으면 false 반환
	 */
	public boolean isMobile(String mobile) {
		SqlSession session = factory.getSqlSession();
		boolean isTel = false;
		String duplicate = null;
		try {
			duplicate = session.selectOne("member.isMobile", mobile);
			if(duplicate != null) {
				isTel = true;
			}
		} finally {
			session.close();
		}
		return isTel;
	}
	
	/**
	 * 로그인시 아이디 비밀번호 일치 조회 메소드
	 * @param id 아이디
	 * @return 비밀번호
	 */
	public String isLogin(String memberId) {
		SqlSession session = factory.getSqlSession();
		String password = null;
		try {
			password = session.selectOne("member.isLogin", memberId);
		} finally {
			session.close();
		}
		return password;
	}
	
	/**
	 * 아이디 찾기 조회 메소드
	 * @param name 회원이름
	 * @param mobile 연락처
	 * @return 아이디
	 */
	public String selectId(String name, String mobile) {
		SqlSession session = factory.getSqlSession();
		HashMap<String, String> idMap = new HashMap<>();
		idMap.put("name", name);
		idMap.put("mobile", mobile);
		String memberId = null;
		try {
			memberId = session.selectOne("member.selectId", idMap);
		} finally {
			session.close();
		}
		return memberId;
	}
	
	/**
	 * 비밀번호 찾기 조회 메소드
	 * @param id 회원아이디
	 * @param name 회원이름
	 * @return 비밀번호
	 */
	public String selectPw(String memberId, String name, String mobile) {
		SqlSession session = factory.getSqlSession();
		HashMap<String, String> pwMap = new HashMap<>();
		pwMap.put("memberId", memberId);
		pwMap.put("name", name);
		pwMap.put("mobile", mobile);
		String password = null;
		try {
			password = session.selectOne("member.selectPw", pwMap);
		} finally {
			session.close();
		}
		return password;
	}
	
	/**
	 * 회원 정보 조회 메소드
	 * @param id 회원 아이디
	 * @return 회원 객체 정보
	 */
	public Member selectUser(String memberId) {
		SqlSession session = factory.getSqlSession();
		Member dto = null;
		try {
			dto = session.selectOne("member.selectUser", memberId);
		} finally {
			session.close();
		}
		return dto;
	}
	
	/**
	 * 회원 정보 전체 목록 조회 메소드
	 * @return 회원 정보 목록
	 */
	public List<Member> selectAll() {
		SqlSession session = factory.getSqlSession();
		List<Member> members = null;
		try {
			members = session.selectList("member.selectUsers");
		} finally {
			session.close();
		}
		return members;
	}
	
	/**
	 * 비밀번호 변경 메소드
	 * @param id 아이디
	 * @param pw 비밀번호
	 * @return 성공시 변경 성공 행수, 실패시 0 반환
	 */
	public int updatePw(String id, String pw) {
		SqlSession session = factory.getSqlSession(true);
		HashMap<String, String> memberMap = new HashMap<>();
		memberMap.put("memberId", id);
		memberMap.put("password", pw);
		int count = 0;
		try {
			count = session.update("member.updatePw", memberMap);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 연락처 변경 메소드
	 * @param id 아이디
	 * @param mobile 연락처
	 * @return 변경 성공 행수, 실패시 0 반환
	 */
	public int updateMobile(String id, String mobile) {
		SqlSession session = factory.getSqlSession(true);
		HashMap<String, String> memberMap = new HashMap<>();
		memberMap.put("memberId", id);
		memberMap.put("mobile", mobile);
		int count = 0;
		try {
			count = session.update("member.updateMobile", memberMap);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 주소 변경 메소드
	 * @param id 아이디
	 * @param address 주소
	 * @return 변경 성공 행수, 실패시 0 반환
	 */
	public int updateAddress(String id, String address) {
		SqlSession session = factory.getSqlSession(true);
		HashMap<String, String> memberMap = new HashMap<>();
		memberMap.put("memberId", id);
		memberMap.put("address", address);
		int count = 0;
		try {
			count = session.update("member.updateAddress", memberMap);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 회원 탈퇴 메소드
	 * @param id 아이디
	 * @return 탈퇴 성공 행수, 실패시 0
	 */
	public int withdrawUser(String memberId) {
		SqlSession session = factory.getSqlSession(true);
		int count = 0;
		try {
			count = session.delete("member.delete", memberId);
		} finally {
			session.close();
		}
		return count;
	}
	
	public int deleteUser(String memberId) {
		SqlSession session = factory.getSqlSession(true);
		int count = 0;
		try {
			count = session.update("member.deleteUser", memberId);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 회원 도서대출 가능 상태 조회 메소드
	 * @param id 아이디
	 * @return 대출가능이면 true, 대출불가면 false
	 */
	public boolean isCondition(String memberId) {
		SqlSession session = factory.getSqlSession();
		boolean isStatus = false;
		try {
			String condition = session.selectOne("member.isCondition", memberId);
			if(condition.equals("대출가능"))
				isStatus=true;
		} finally {
			session.close();
		}
		return isStatus;
	}
	
	/**
	 * 회원 대출가능 여부 상태 변경 메소드
	 * @param id 아이디
	 * @return 변경 성공 행수, 실패시 0
	 */
	public int updateCondition(String id) {
		SqlSession session = factory.getSqlSession(true);
		HashMap<String, String> memberMap = new HashMap<>();
		memberMap.put("memberId", id);
		memberMap.put("condition", "대출불가");
		int count = 0;
		try {
			count = session.update("member.updateCondition", memberMap);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 회원의 대출권수 조회 메소드
	 * @param id 아이디
	 * @return 대출권수
	 */
	public int selectLend(String memberId) {
		SqlSession session = factory.getSqlSession();
		int lend = 0;
		try {
			String lending = session.selectOne("member.selectLend", memberId);
			lend = Integer.parseInt(lending.substring(0, 1));
		} finally {
			session.close();
		}
		return lend;
	}
	
	/**
	 * 회원의 대출권수 증가 변경 메소드
	 * @param id 아이디
	 * @return 변경 성공 행수, 실패시 0
	 */
	public int updateLending(String id) {
		SqlSession session = factory.getSqlSession(true);
		int n = selectLend(id);
		int count = 0;
		if(n == 5) {
			System.out.println("대출가능 권수를 초과하였습니다.");
			return count;
		}
		HashMap<String, String> lendMap = new HashMap<>();
		lendMap.put("memberId", id);
		lendMap.put("lendBook", (++n)+"/5");
		try {
			count = session.update("member.updateLend", lendMap);
		} finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * 회원의 대출권수 감소 변경 메소드
	 * @param id 아이디
	 * @return 변경 성공 행수, 실패시 0
	 */
	public int updateReturn(String id) {
		SqlSession session = factory.getSqlSession(true);
		int n = selectLend(id);
		int count = 0;
		HashMap<String, String> returnMap = new HashMap<>();
		returnMap.put("memberId", id);
		returnMap.put("lendBook", (--n)+"/5");
		try {
			count = session.update("member.updateLend", returnMap);
		} finally {
			session.close();
		}
		return count;
	}
}
