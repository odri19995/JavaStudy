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

	private List<Article> articles; // 메인 메서드가 아니라서 static 변수를 할 필요가 없다. //여기서만 쓸거니까 private
	private List<Member> members;

	App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

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

			if (cmdBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			String controllerName = cmdBits[0]; // article
			String MethodName = cmdBits[1]; // list



			if (controllerName.equals("article")) {
				articleController.doAction(cmd,MethodName);
			} else if (controllerName.equals("member")) {
				memberController.doAction(cmd,MethodName);
				}

//			if (cmd.equals("article write")) {
//				articleController.doWrite();
//			} else if (cmd.equals("member join")) {
//				memberController.doJoin();
//			} else if (cmd.startsWith("article list")) {
//				articleController.showList(cmd);
//			}else if (cmd.startsWith("article detail ")) {
//				articleController.showDetail(cmd);
//			} else if (cmd.startsWith("article delete ")) {
//				articleController.doDelete(cmd);
//			} else if (cmd.startsWith("article modify ")) {
//				articleController.doModify(cmd);
//			}
			else {
				System.out.println("없는 명령어입니다"); // 전부 따져봐야해서 밑으로 놓는다.
			}
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");

	}

	private void makeTestData() {
		System.out.println("게시물 테스트 데이터를 생성합니다.");
		articles.add(new Article(1, Util.getNowDateStr(), "title 1", "body 1", 10));
		articles.add(new Article(2, Util.getNowDateStr(), "title 2", "body 2", 20));
		articles.add(new Article(3, Util.getNowDateStr(), "title 3", "body 3", 30));
	}

}
