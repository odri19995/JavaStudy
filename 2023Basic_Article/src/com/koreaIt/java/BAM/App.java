package com.koreaIt.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIt.java.BAM.controller.ArticleController;
import com.koreaIt.java.BAM.controller.Controller;
import com.koreaIt.java.BAM.controller.MemberController;
import com.koreaIt.java.BAM.dto.Article;
import com.koreaIt.java.BAM.dto.Member;
import com.koreaIt.java.BAM.util.Util;

public class App {


	public void run() {
		System.out.println("== 프로그램 시작 ==");


		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
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
			String MethodName = cmdBits[1]; // list
			
			Controller controller = null;



			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
				}
			else {
				System.out.println("없는 명령어입니다"); // 전부 따져봐야해서 밑으로 놓는다.
				continue;
			}
			
			controller.doAction(cmd, MethodName);
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");

	}



}
