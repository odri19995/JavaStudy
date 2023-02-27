package com.koreaIt.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIt.java.BAM.dto.Member;
import com.koreaIt.java.BAM.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private List<Member> members;
	private int lastMemberId;

	public MemberController(Scanner sc) {
		this.sc = sc;
		this.members = new ArrayList<>();
		this.lastMemberId = 3;
	}

	public void doAction(String cmd, String MethodName) {

		switch (MethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "profile":
			doProfile();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	private void doJoin() {

		int id = lastMemberId + 1;
		lastMemberId = id;
		String regDate = Util.getNowDateStr();

		String loginId = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (loginIdDupChk(loginId) == false) {
				System.out.printf("%s는(은) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}

			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인: ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}

			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 가입하였습니다\n", id);

	}

	private void doLogin() {
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		Member member = getMemberByLoginId(loginId); // loginId로 해당 member를 가져올것이다.

		if (member == null) {
			System.out.println("존재하지 않는 아이디 입니다.");
			return;
		}
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		} else {
			loginedMember = member; // 추가가능 기능 로그인시 중복로그인확인, 로그아웃기능
			System.out.printf("로그인 성공! %s님 안녕하세요\n", member.name);
		}

	}

	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
		return;
	}

	private void doProfile() {
		Member member = loginedMember;

		System.out.println(" === 내 정보 ===");
		System.out.printf("로그인 아이디 : %s\n", member.loginId);
		System.out.printf("이름 : %s\n", member.name);
		System.out.printf("가입날짜 : %s\n", member.regDate);

	}

	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	private boolean loginIdDupChk(String loginId) {
		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			return true;
		}
		return false;
	}

	public void makeTestData() {
		members.add(new Member(1, Util.getNowDateStr(), "ABC", "123", "minsu"));
		members.add(new Member(2, Util.getNowDateStr(), "title 2", "body 2", "simin"));
		members.add(new Member(3, Util.getNowDateStr(), "title 3", "body 3", "happy"));
	}

}