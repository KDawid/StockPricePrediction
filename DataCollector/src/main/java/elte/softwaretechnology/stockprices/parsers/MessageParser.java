package elte.softwaretechnology.stockprices.parsers;

import elte.softwaretechnology.stockprices.data.model.Article;
import elte.softwaretechnology.stockprices.data.model.Meta;

import java.util.List;

public interface MessageParser {
	public List<Article> parseResponeData(String message);

	public Meta parseResponeMeta(String message);
}
