package com.koreaIt.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIt.java.BAM.dto.Article;

public class ArticleDao extends Dao {
	public List<Article> articles;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
	}
	
	public void add(Article article) {
		articles.add(article);
		lastId++;
	}
	



}
