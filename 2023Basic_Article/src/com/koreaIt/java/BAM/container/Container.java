package com.koreaIt.java.BAM.container;

import com.koreaIt.java.BAM.dao.ArticleDao;
import com.koreaIt.java.BAM.dao.MemberDao;

public class Container {
	public static ArticleDao articleDao; //static 이어야 하는 이유 static이 아니면 또 만들어진다 공유자원이 아니면 다시 찍어낸다.
	public static MemberDao memberDao; //컨테이너는 일종의 보관소 db 역할을 대응 as ms를 넣어놓고 controller에서 가져다 쓰겠다. 
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}
}
