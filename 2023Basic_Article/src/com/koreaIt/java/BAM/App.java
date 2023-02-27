package com.koreaIt.java.BAM;

import java.util.Scanner;

import com.koreaIt.java.BAM.controller.ArticleController;
import com.koreaIt.java.BAM.controller.Controller;
import com.koreaIt.java.BAM.controller.MemberController;

public class App {

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		memberController.makeTestData();
		while (true) {
			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요");
				continue;
			}
			if (cmd.equals("system exit")) {// 불필요한 명령을 거르기 위해서 특수한 명령어는 위로 빼준다.
				break;
			}
			String[] cmdBits = cmd.split(" "); // article list

			if (cmdBits.length == 1) {// article만 쳤을경우 cmdBit[1]이 없어서 오류나는 것을 막는다.
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			String controllerName = cmdBits[0]; // article
			String methodName = cmdBits[1]; // list

			String actionName = controllerName + "/" + methodName;

			switch (actionName) { // 로그인 검증을 조금더 앞에서 해준다. 불필요한 과정 생략 가능
			case "article/write":
			case "article/delete":
			case "article/modify":
			case "member/profile":
			case "member/logout":
//				if (controller.isLogined() == false) { //controller 는 articleController,memberController 중 하나에 연결되어 있는 islogined를 가져온 것이다.
				if (Controller.isLogined() == false) { // 상황에 따라 변화하는 메서드가 아닐경우 static을 사용하는게 좋다.
					System.out.println("로그인 후 이용해주세요");
					continue;// break가 아니라 continue로 while문으로 올라간다.
				}
				break;
			case "member/join":
			case "member/login":
				if (Controller.isLogined() == true) {
					System.out.println("로그아웃 후 이용해주세요");
					continue;
				}
				break;

			}

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("없는 명령어입니다"); // 전부 따져봐야해서 밑으로 놓는다.
				continue;
			}

			controller.doAction(cmd, methodName);
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");

	}

}
