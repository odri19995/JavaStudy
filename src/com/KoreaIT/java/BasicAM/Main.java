package com.KoreaIT.java.BasicAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		int count = 0;
		List<Article> articles = new ArrayList<>(); // article에 쓴글을 articles에 올리고 싶다.

		while (true) { // 무한루프안에서 명령을 받는 구조
			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();// trim : 양쪽의 공백을 절삭하겠다.

			if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				System.out.println("번호    |     내용 ");
				for (int i = articles.size()-1; i >=0 ; i--) {
					Article article = articles.get(i); // arrayList 추출시 get 사용
					System.out.printf("%d, %s\n", article.count, article.title);

				}

			} else if (cmd.equals("article write")) {
				count++;

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				System.out.printf("%d번글이 생성되었습니다.\n", count);

				// 객체화

				Article article = new Article(count, title, body);

				articles.add(article);

			} else if (cmd.equals("system exit")) {
				break;

			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		System.out.println("==프로그램 종료==");

		sc.close();
	}

}

class Article {
	int count;
	String title;
	String body;

	public Article(int count, String title, String body) {
		this.count = count;
		this.title = title;
		this.body = body;

	}// 외부의 정보들이 Article 필드에 채워진다.
}
