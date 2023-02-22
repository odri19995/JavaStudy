package com.koreaIt.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIt.java.BAM.dto.Article;
import com.koreaIt.java.BAM.util.Util;

public class ArticleController extends Controller {

	private Scanner sc;
	private List<Article> articles;
	int lastArticleId;
	private String cmd;
	private String MethodName;

	public void doAction(String cmd, String MethodName) {
		this.cmd = cmd;
		this.MethodName = MethodName;

		switch (MethodName) {
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "write":
			doWrite();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		}
	}

	public ArticleController(List<Article> articles, Scanner sc) {
		this.sc = sc;
		this.articles = articles;
		this.lastArticleId = 3;
	}

	public void doWrite() {
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
	}

	public void showList() {

		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;// -> 리턴으로 함수를 종료시키되 넘겨주는 값은 없다.
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
				return; // continue는 위에 반복문 while문으로 간다.
			}

		}
		System.out.println("번호	|	제목    | 조회수");
		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			System.out.printf("%d	|	%s|   %d\n", article.id, article.title, article.viewCnt);
		}
	}

	public void showDetail() {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		System.out.printf("%d번 게시물은 존재합니다\n", id);
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate); // .subString(0,10)
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회수 : %d\n", foundArticle.viewCnt);
		foundArticle.increaseViewCount();

	}

	public void doDelete() {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		// size() = 3
		// index = 0 1 2
		// id = 1 2 3
		articles.remove(articles.indexOf(foundArticle));

		System.out.printf("%d번 게시물이 삭제 되었습니다\n", id);

	}

	public void doModify() {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);
		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d글이 수정되었습니다.\n", id);

	}

	private Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.id == id)
				return article;
		}

		return null;
	}

}
