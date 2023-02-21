package com.koreaIt.java.BAM.dto;

public class Member extends Dto{

	public String loginId;//import를 해도 변수에 public 이 없으면 오류가 난다.
	public String loginPw;
	public String name;
	
	public Member(int id,String regDate, String loginId, String loginPw,String name) {
		this.id = id;
		this.regDate=regDate;
		this.loginId=loginId;
		this.loginPw=loginPw;
		this.name=name;

	}

}
