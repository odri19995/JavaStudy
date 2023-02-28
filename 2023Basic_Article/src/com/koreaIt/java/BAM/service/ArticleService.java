package com.koreaIt.java.BAM.service;

import java.util.List;

import com.koreaIt.java.BAM.container.Container;
import com.koreaIt.java.BAM.dto.Article;

public class ArticleService {
	public List<Article> getPrintArticles(String searchKeyword){
		return Container.articleDao.getPrintArticles(searchKeyword);
	}

}
