package library.model.dto;

import java.io.Serializable;

public class Book implements Serializable {

	/*
	 * book_no varchar2(10) not null,
		book_title varchar2(100) not null,
		author varchar2(20) not null,
		publisher varchar2(20) not null,
		publishing_date varchar2(10) not null,
		ISBN varchar2(13) not null,
		condition varchar2(10) default '대출가능',
		location varchar2(20) not null,
		type_code varchar2(20) not null,
		category varchar2(10) not null
	 */
	
	/** 도서의 고유 번호 : PK */
	private String bookNo;
	/** 도서명 : 필수 */
	private String bookTitle;
	/** 저자명 : 필수 */
	private String author;
	/** 출판사 : 필수 */
	private String publisher;
	/** 출판일 : 필수 */
	private String publishingDate;
	/** 도서의 국제표준분류번호 */
	private String isbn;
	/** 도서상태 (ex: 대출중 / 대출가능) */
	private String condition;
	/** 도서의 소장 위치 */
	private String location;
	/** 도서의 분야 코드(청구기호) (ex: 853.46ㅅ) */
	private String typeCode;
	/** 도서의 십진분류표에 의한 분류 */
	private String category;
	
	public Book() {
	}
	
	/**
	 * @param bookTitle 도서명
	 * @param author 저자
	 * @param publisher 출판사
	 * @param publishingDate 출판일
	 * @param condition 자료상태
	 * @param location 소장위치
	 * @param typeCode 분야코드(청구기호)
	 */
	public Book(String bookTitle, String author, String publisher, String publishingDate, String isbn, String location, String typeCode, String category) {
		this.bookTitle = bookTitle;
		this.author = author;
		this.publisher = publisher;
		this.publishingDate = publishingDate;
		this.isbn = isbn;
		this.location = location;
		this.typeCode = typeCode;
		this.category = category;
	}

	/**
	 * @param bookNo 도서번호
	 * @param bookTitle 도서명
	 * @param author 저자
	 * @param publisher 출판사
	 * @param publishingDate 출판일
	 * @param condition 자료상태
	 * @param location 소장 위치
	 * @param typeCode 분야코드(청구기호)
	 */
	public Book(String bookNo, String bookTitle, String author, String publisher, String publishingDate, String isbn, String condition,
			String location, String typeCode, String category) {
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.author = author;
		this.publisher = publisher;
		this.publishingDate = publishingDate;
		this.isbn = isbn;
		this.condition = condition;
		this.location = location;
		this.typeCode = typeCode;
		this.category = category;
	}

	/**
	 * @return the bookNo
	 */
	public String getBookNo() {
		return bookNo;
	}

	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the publishingDate
	 */
	public String getPublishingDate() {
		return publishingDate;
	}

	/**
	 * @param publishingDate the publishingDate to set
	 */
	public void setPublishingDate(String publishingDate) {
		this.publishingDate = publishingDate;
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

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookNo == null) ? 0 : bookNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookNo == null) {
			if (other.bookNo != null)
				return false;
		} else if (!bookNo.equals(other.bookNo))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(bookNo);
		builder.append(", ");
		builder.append(bookTitle);
		builder.append(", ");
		builder.append(author);
		builder.append(", ");
		builder.append(publisher);
		builder.append(", ");
		builder.append(publishingDate);
		builder.append(", ");
		builder.append(isbn);
		builder.append(", ");
		builder.append(condition);
		builder.append(", ");
		builder.append(location);
		builder.append(", ");
		builder.append(typeCode);
		builder.append(", ");
		builder.append(category);
		return builder.toString();
	}
	
}
