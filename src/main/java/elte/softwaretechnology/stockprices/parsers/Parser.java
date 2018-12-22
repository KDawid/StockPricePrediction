package elte.softwaretechnology.stockprices.parsers;

import elte.softwaretechnology.stockprices.data.Article;

import java.text.ParseException;
import java.util.List;

public interface Parser {
	public List<Article> parseRespone(String message) throws ParseException;
}
