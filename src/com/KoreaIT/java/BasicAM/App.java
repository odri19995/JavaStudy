package com.KoreaIT.java.BasicAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.dto2.Article;
import com.KoreaIT.java.BasicAM.util.Util;

public class App {
	private List<Article> articles; // 접근지정자 private값과 Static 설정

	public App() {                                //Main() 생성자를 Static 생성자로 대체 Static은 Static 끼리
		articles = new ArrayList<>();// 메인 메서드에 있던거 전역변수로 뺌
	}

	public void start() { // static은 static끼리만 소통이 가능하다.
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요");
				continue;
			}
			if (cmd.equals("system exit")) {
				break;
			}

			if (cmd.equals("article write")) {
				int id = articles.size() +1;
				String regDate = Util.getNowDateStr();

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);

				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다\n", id);

			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				System.out.println("번호	|	제목    |   조회");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%4d	|	%4s   |   %4d\n", article.id, article.title, article.viewCnt);
				}

			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				foundArticle.increaseViewCount();

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회 : %d\n", foundArticle.viewCnt);

			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				int foundIndex = -1; // 배열에서 못찾았다.
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundIndex = i; // 해당 인덱스로 덮어쓰기
						break;
					}
				}

				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				// size() = 3
				// index = 0 1 2
				// id = 1 2 3
				articles.remove(foundIndex);

				System.out.printf("%d번 게시물이 삭제 되었습니다\n", id);

			} else if (cmd.startsWith("article modify")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf(" %d번 게시물이 수정되었습니다.\n", id);

			} else {
				System.out.println("없는 명령어입니다");
			}
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}

	private  void makeTestData() {
		System.out.println("test데이터를 생성합니다.");
		articles.add(new Article(1,Util.getNowDateStr(),"title 1","body 1",11));
		articles.add(new Article(2,Util.getNowDateStr(),"title 2","body 2",22));
		articles.add(new Article(3,Util.getNowDateStr(),"title 3","body 3",33));

	}
}