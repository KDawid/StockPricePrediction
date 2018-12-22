package elte.softwaretechnology.stockprices.parsers.implementations;

import elte.softwaretechnology.stockprices.data.Article;
import elte.softwaretechnology.stockprices.data.KeyWord;
import elte.softwaretechnology.stockprices.parsers.Parser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Locale.ENGLISH;

public class NewYorkTimesDataParser implements Parser {
	private DateTimeFormatter dateFormatOption1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", ENGLISH);
	private DateTimeFormatter dateFormatOption2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ", ENGLISH);

	@Override
	public List<Article> parseRespone(String message) {
		List<Article> articles = new ArrayList<>();
		JSONArray jsonDocs = new JSONObject(message).getJSONObject("response").getJSONArray("docs");
		for (int i = 0; i < jsonDocs.length(); ++i) {
			JSONObject jsonArticle = jsonDocs.getJSONObject(i);
			Article article = new Article();
			articles.add(article);
			article.setSnippet(jsonArticle.getString("snippet"));
			article.setWordCount(jsonArticle.getInt("word_count"));
			article.setScore(jsonArticle.getDouble("score"));
			try {
				article.setDateOfPublication(LocalDateTime.parse(jsonArticle.getString("pub_date"), dateFormatOption1));
			}
			catch (DateTimeParseException e) {
				article.setDateOfPublication(LocalDateTime.parse(jsonArticle.getString("pub_date"), dateFormatOption2));
			}
			article.setTitle(jsonArticle.getJSONObject("headline").getString("main"));
			JSONArray jsonKeyWords = jsonArticle.getJSONArray("keywords");
			for (int j = 0; j < jsonKeyWords.length(); ++j) {
				JSONObject jsonKeyword = jsonKeyWords.getJSONObject(j);
				KeyWord keyWord = new KeyWord();
				article.addKeyWord(keyWord);
				keyWord.setKeyWord(jsonKeyword.getString("value"));
				keyWord.setMajor(jsonKeyword.getString("major"));
			}
		}
		return articles;
	}
}
