package com.koreaIt.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

		int lastArticleId = 3;
		int lastMemberId = 0;

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

			if (cmd.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				String regDate = Util.getNowDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);

				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다\n", id);

			} else if (cmd.equals("member join")) {
				int id = lastMemberId + 1;
				lastMemberId = id;
				String regDate = Util.getNowDateStr();

				String loginId = null; //지역변수들은 초기화가 안될수도 없으므로 기본값을 넣어주는게 좋다.

				while (true) {
					System.out.printf("로그인 아이디 : ");
					loginId = sc.nextLine();

					if (isJoinableLoginId(loginId) == false) {
						System.out.printf("%s는(은) 이미 사용중인 아이디입니다.\n", loginId);
						continue; //다시 입력받아야 하므로 break가 안실행되게 위로 올린다.
					}
					System.out.printf("%s는(은) 이미 사용가능한 아이디입니다.\n", loginId);
					break;
				}
				String loginPw = null;
				String loginPwConfirm = null; //밖에서 쓸때는 밖에 빼주면 된다. 

				while (true) {
					System.out.printf("로그인 비밀번호 : ");
					loginPw = sc.nextLine();
					System.out.printf("로그인 비밀번호 확인: ");
					loginPwConfirm = sc.nextLine();

					if (loginPw.equals(loginPwConfirm) == false) {
						System.out.println("비밀번호를 다시 입력해주세요.");
						continue;
					} 

					break;  //보통 브레이크가 continue보다 밑에 있는게 좋다. 
				}

				System.out.printf("이름 : ");
				String name = sc.nextLine();

				Member member = new Member(id, regDate, loginId, loginPw, name);
				members.add(member);

				System.out.printf("%d번 회원이 생성되었습니다. 환영합니다.\n", id);
					

			} else if (cmd.startsWith("article list")) {

				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}

				String searchKeyword = cmd.substring("article list".length()).trim();

				List<Article> printArticles = articles; // articles를 컨트롤 하기 위해서 리모컨을 넘겨줌
				if (searchKeyword.length() > 0) {
					System.out.println("검색어 :" + searchKeyword);

					printArticles = new ArrayList<>(); // 내용이 있을때만 리스트를 만들어준다. 진짜 객체 생성
					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							printArticles.add(article);
						}
					}
					if (printArticles.size() == 0) {
						System.out.println("검색결과가 없습니다.");
						continue; // continue는 위에 반복문 while문으로 간다.
					}

				}
				System.out.println("번호	|	제목    | 조회수");
				for (int i = printArticles.size() - 1; i >= 0; i--) {
					Article article = printArticles.get(i);
					System.out.printf("%d	|	%s|   %d\n", article.id, article.title, article.viewCnt);

				}

			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				System.out.printf("%d번 게시물은 존재합니다\n", id);
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regDate); // .subString(0,10)
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회수 : %d\n", foundArticle.viewCnt);
				foundArticle.increaseViewCount();

			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				// size() = 3
				// index = 0 1 2
				// id = 1 2 3
				articles.remove(articles.indexOf(foundArticle));

				System.out.printf("%d번 게시물이 삭제 되었습니다\n", id);

			} else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);
				Article foundArticle = getArticleById(id);

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

				System.out.printf("%d글이 수정되었습니다.\n", id);

			}

			else {
				System.out.println("없는 명령어입니다"); // 전부 따져봐야해서 밑으로 놓는다.
			}
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");

	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}
	
	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i; //false
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.id == id)
				return article;
		}

		return null;
	}

	private void makeTestData() {
		System.out.println("게시물 테스트 데이터를 생성합니다.");
		articles.add(new Article(1, Util.getNowDateStr(), "title 1", "body 1", 10));
		articles.add(new Article(2, Util.getNowDateStr(), "title 2", "body 2", 20));
		articles.add(new Article(3, Util.getNowDateStr(), "title 3", "body 3", 30));
	}

}
