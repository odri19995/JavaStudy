package com.koreaIt.java.BAM.dto;

public class Article extends Dto{  //public 이 없으면 import가 안된다. 
	
 
	public String title;
	public String body;
	public int viewCnt;

	public Article(int id,String regDate, String title, String body) {
		this(id,regDate,title,body,0);  //오버로딩

	}
	
	public Article(int id,String regDate, String title, String body,int viewCnt) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.viewCnt =viewCnt;
	}
	
	
	public void increaseViewCount() {
		viewCnt++;
	}
}