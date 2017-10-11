package library.test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utility {

	/**
	 * �����ڵ������ ���� ��¥�������� ����ϴ� �޼ҵ�
	 * @return ������ ���Ŀ� ���߾� ��¥ ���
	 */
	public String dateNumber() {
		SimpleDateFormat codeformat = new SimpleDateFormat("yyyyMMdd");
		String number = codeformat.format(new java.util.Date());
		return number;
	}
	
	/**
	 * �ð��������� ����ϴ� �޼ҵ�
	 * @return ������ ���Ŀ� ���߾� �ð� ���
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
	 * ��¥�������� ����ϴ� �޼ҵ�
	 * @return ������ ���Ŀ� ���߾� ��¥ ���
	 */
	public Date today() {
		GregorianCalendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR)-1900;
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		
		Date date = new Date(year, month, day);
		return date;
	}
	
	/**
	 * 10�� �� ��¥�� ���Ŀ� ���߾� ����ϴ� �޼ҵ�
	 * @return ������ ���Ŀ� ���߾� 10�� �� ��¥ ���
	 */
	public Date periodDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 10);
		int year = calendar.get(Calendar.YEAR)-1900;
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		
		Date date = new Date(year, month, day);
		
		return date;
	}
}
