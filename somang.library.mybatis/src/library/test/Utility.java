package library.test;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utility {

	/**
	 * 대출코드생성을 위한 날짜형식으로 출력하는 메소드
	 * @return 지정된 형식에 맞추어 날짜 출력
	 */
	public String dateNumber() {
		SimpleDateFormat codeformat = new SimpleDateFormat("yyyyMMdd");
		String number = codeformat.format(new java.util.Date());
		return number;
	}
	
	/**
	 * 시간형식으로 출력하는 메소드
	 * @return 지정된 형식에 맞추어 시간 출력
	 */
	public String lending() {
		GregorianCalendar calendar = new GregorianCalendar();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		String date = hour+":"+minute+":"+second;
		return date;
	}
	
	/**
	 * 날짜형식으로 출력하는 메소드
	 * @return 지정된 형식에 맞추어 날짜 출력
	 */
	public Date today() {
		return new Date();
	}
	
	/**
	 * 10일 뒤 날짜를 형식에 맞추어 출력하는 메소드
	 * @return 지정된 형식에 맞추어 10일 뒤 날짜 출력
	 */
	public Date periodDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 10);
		return calendar.getTime();
	}
}
