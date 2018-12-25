package elte.softwaretechnology.stockprices.parsers;

import elte.softwaretechnology.stockprices.data.Article;
import elte.softwaretechnology.stockprices.data.Meta;

import java.util.List;

public interface Parser {
	public List<Article> parseResponeData(String message);

	public Meta parseResponeMeta(String message);
}
