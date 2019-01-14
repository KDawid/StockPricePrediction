package elte.softwaretechnology.stockprices.data.service;

import elte.softwaretechnology.stockprices.data.model.Article;
import elte.softwaretechnology.stockprices.data.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ArticleService {
	@Autowired
	private ArticleRepository repository;

	public Article save(Article article) {
		return repository.save(article);
	}

	public Iterable<Article> saveAll(Iterable<Article> articles) {
		return repository.saveAll(articles);
	}
}
