package com.KoreaIT.java.BasicAM.dto2;

public class Article {
	public int id;  //Data Transfer object 또는 Value object라고 한다. 왔다갔다하는 데이터
	public String title;
	public String body;
	public String regDate;
	public int viewCnt;
	
	public Article(int id, String regDate, String title, String body) {
		this(id,regDate,title,body,0); //다른 생성자를 호출해서 일을 시킨다. 값들이 매개변수값으로 들어간다.
	}

	public Article(int id, String regDate, String title, String body, int viewCnt) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.viewCnt = viewCnt; // 누군가 클릭하지 않으면 조회수는 0이다. //글을 상세보기할때 조회수가 같이 나오면 된다.
	}

		
		

	public void increaseViewCount() {
		viewCnt++;
	}
}

