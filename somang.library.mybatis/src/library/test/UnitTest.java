package library.test;

import java.text.DecimalFormat;
import java.text.ParseException;

public class UnitTest {
	public static void main(String[] args) throws ParseException {
		
		Utility u = new Utility();
		
		System.out.println(u.today());
		System.out.println(u.periodDate());
		
		System.out.println(u.lending());
		
//		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//		String todayString = dateformat.format(new Date());
//		System.out.println("today : " + todayString);
//		
//		Date todayDate = dateformat.parse(todayString);
//		
//		System.out.println("todayDate: " + todayDate.toString());
//		
//		LendReturnDao dao = LendReturnDao.getInstance();
//		
//		int bookno = 2;
//		String title = "¿·";
//		String membership = "20170903001";
//		
//		LendReturn lending = new LendReturn(bookno, title, membership);
//		dao.insertLend(lending);
//		
//		SimpleDateFormat dateprint = new SimpleDateFormat("yyyyMMdd");
//		Date date = new Date();
//		System.out.println(dateprint.format(date));
//
//		SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-dd");
//		String today = dateform.format(new Date());
//		System.out.println(today);
//		System.out.println(date);
//		
//		System.out.println(dateform.parse(today));
//		
//		System.out.println("==========================================");
//		
//		MemberDao dao = MemberDao.getInstance();
//		
//		boolean duplicate = dao.isMemberId("user01");
//		
//		System.out.println(duplicate);
//
//		System.out.println(toDFormat(3));
//		
//		System.out.println(secureFormat("password01"));

	}

	public static String toDFormat(int num) {
		DecimalFormat df = new DecimalFormat("000");
		return df.format(num);
	}

	public static StringBuffer secureFormat(String str) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		sb.append(str);

		for (int i = 4; i < sb.length(); i++) {
			sb2.append('*');
		}
		sb.replace(4, sb.length(), sb2.toString());
		return sb;
	}
}
