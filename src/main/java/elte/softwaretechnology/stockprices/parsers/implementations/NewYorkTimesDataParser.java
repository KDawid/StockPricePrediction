package elte.softwaretechnology.stockprices.parsers.implementations;

import elte.softwaretechnology.exceptions.NotImplementedException;
import elte.softwaretechnology.stockprices.data.Article;
import elte.softwaretechnology.stockprices.parsers.Parser;

import java.util.List;

public class NewYorkTimesDataParser implements Parser {

	@Override
	public List<Article> parseRespone(String message) {
		throw new NotImplementedException();
	}
}
