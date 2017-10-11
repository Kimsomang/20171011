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
	 * ȸ���� ����/�ݳ� ���� ��ȸ
	 * @param membership ȸ���ڵ�
	 * @return ����/�ݳ� �̷� ���
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
	 * ��� ����/�ݳ� ���� ��ȸ
	 * @return ����/�ݳ� �̷� ���
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
	 * ���������ڵ带 �ο��ϱ� ���� �޼ҵ�
	 * @param num �������
	 * @return ���Ŀ� ���� �ڵ� ��ȯ
	 */
	public String toDFormat(int num){
		 DecimalFormat df = new DecimalFormat("000");
		 return df.format(num);
		}
	
	/**
	 * ���� ���� ���� �Է�
	 * @param lending �������� ����
	 * @return �Է� ���� ���, ���н� 0 ��ȯ
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
	 * ���� �ݳ� ���� �Է�
	 * @param lendCode �����ڵ�
	 * @return �Է� ���� ���, ���н� 0 ��ȯ
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
	 * ��ü ���� ��ȸ �޼ҵ�
	 * @param lendCode �����ڵ�
	 * @return ��ü�� ������ true, �ƴϸ� false
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
	 * ���� �ݳ��Ⱓ ����
	 * @param lendCode �����ڵ�
	 * @return ����(����) ���� ���, ���н� 0 ��ȯ
	 */
	public int updatePeriod(String lendCode) {
		SqlSession session = factory.getSqlSession(true);
		HashMap<String, String> periodMap = new HashMap<>();
		periodMap.put("lendCode", lendCode);
		periodMap.put("extension", "1/1");
		int count = 0;
		if(isExtension(lendCode)) {
			System.out.println("���� ���� Ƚ���� �ʰ��ϼ̽��ϴ�.");
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
	 * ���� ��뿩�� ��ȸ �޼ҵ�
	 * @param lendCode �����ڵ�
	 * @return ���Ƚ���� 1�̸� true, ���Ƚ���� 0�̸� false
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
	 * ���� ��� �� �����̷� ����
	 * @param lendCode �����ڵ�
	 * @return ����(���) ���� ���, ���н� 0 ��ȯ
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
	 * ������ȣ�� ���� ������ ��ȸ
	 * @param bookNo ������ȣ
	 * @return ������ ����Ʈ
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
