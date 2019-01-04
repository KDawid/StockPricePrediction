package elte.softwaretechnology.stockprices.data.service;

import elte.softwaretechnology.stockprices.data.model.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class QueryResponseService {
	@Autowired
	private ArticleService articleService;

	public QueryResponse save(QueryResponse queryResponse) {
		articleService.saveAll(queryResponse.getArticles());
		return queryResponse;
	}
}
