package library.model.dto;

import java.io.Serializable;

public class Member implements Serializable {

	/*
	 * member_id varchar2(20) primary key,
		member_pw varchar2(20) not null,
		member_name varchar2(20) not null,
		birth varchar2(20) not null,
		mobile varchar2(20) not null,
		address varchar2(100) not null,
		lend_book varchar2(10) default '0/5',
		condition varchar2(10) default '대출가능'
	 */
	
	/** 회원 아이디 : PK, 필수, 중복불가 */
	private String memberId;
	/** 회원 비밀번호 : 필수 */
	private String password;
	/** 회원 이름 : 필수 */
	private String name;
	/** 회원 생년월일 : 필수 */
	private String birth;
	/** 연락처 : 필수 (ex: 010-1234-1234) */
	private String mobile;
	/** 주소 : 필수 */
	private String address;
	/** 대출 권수 (ex: 0/5) */
	private String lendBook;
	/** 대출 가능 여부 상태 (ex: 대출가능 / 대출불가) */
	private String condition;
	
	public Member() {
	}

	/**
	 * @param memberId 회원아이디
	 * @param password 비밀번호
	 * @param name 회원의 이름
	 * @param birth 생년월일
	 * @param mobile 연락처
	 * @param address 주소
	 */
	public Member(String memberId, String password, String name, String birth, String mobile, String address) {
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.mobile = mobile;
		this.address = address;
	}

	/**
	 * @param memberId 회원아이디
	 * @param password 비밀번호
	 * @param name 회원의 이름
	 * @param birth 생년월일
	 * @param mobile 연락처
	 * @param address 주소
	 * @param lendBook 대출권수
	 * @param condition 대출가능여부
	 */
	public Member(String memberId, String password, String name, String birth, String mobile,
			String address, String lendBook, String condition) {
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.mobile = mobile;
		this.address = address;
		this.lendBook = lendBook;
		this.condition = condition;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the lendBook
	 */
	public String getLendBook() {
		return lendBook;
	}

	/**
	 * @param lendBook the lendBook to set
	 */
	public void setLendBook(String lendBook) {
		this.lendBook = lendBook;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n아이디 : ");
		builder.append(memberId);
		builder.append("\n비밀번호 : ");
		builder.append(password);
		builder.append("\n이름 : ");
		builder.append(name);
		builder.append("\n생년월일 : ");
		builder.append(birth);
		builder.append("\n연락처 : ");
		builder.append(mobile);
		builder.append("\n주소 : ");
		builder.append(address);
		builder.append("\n대출권수 : ");
		builder.append(lendBook);
		builder.append("\n대출가능여부 : ");
		builder.append(condition);
		return builder.toString();
	}
	
	
	
}
