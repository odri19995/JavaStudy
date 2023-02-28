package com.koreaIt.java.BAM.dao;

public abstract class Dao {

	protected int lastId;//전역변수는 생성자로 초기화를 주로 한다. 

	Dao() {
		lastId = 0;
	}


	public int getNewId() {
		return lastId + 1;
	}
}