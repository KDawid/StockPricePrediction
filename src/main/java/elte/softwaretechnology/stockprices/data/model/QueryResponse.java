package elte.softwaretechnology.stockprices.data.model;

import java.util.ArrayList;
import java.util.List;

public class QueryResponse {
	private final List<Article> articles = new ArrayList<>();
	private final Meta meta;
	private StringBuilder parsedMessageBuilder = new StringBuilder();

	public QueryResponse(Meta meta) {
		this.meta = meta;
	}

	public void addArticles(List<Article> articles) {
		this.articles.addAll(articles);
	}

	public void addParsedMessage(String parsedMessage) {
		parsedMessageBuilder.append(parsedMessage);
	}

	public List<Article> getArticles() {
		return articles;
	}

	public Meta getMeta() {
		return meta;
	}

	public String getParsedMessage() {
		return parsedMessageBuilder.toString();
	}
}
