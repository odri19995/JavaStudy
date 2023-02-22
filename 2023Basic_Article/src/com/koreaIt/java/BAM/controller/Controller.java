package com.koreaIt.java.BAM.controller;

public abstract class Controller {  //article controller와 member controller를 연결해주려고 만들었다.
	public abstract void doAction(String cmd, String MethodName);
} 