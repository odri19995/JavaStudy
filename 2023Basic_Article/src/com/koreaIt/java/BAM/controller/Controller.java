package com.koreaIt.java.BAM.controller;

import com.koreaIt.java.BAM.dto.Member;

public abstract class Controller {  //article controller와 member controller를 연결해주려고 만들었다.
	public static Member loginedMember; //

	public boolean isLogined() {
		return loginedMember != null;
	}	
	public abstract void doAction(String cmd, String MethodName);
	
	public abstract void makeTestData();
} 