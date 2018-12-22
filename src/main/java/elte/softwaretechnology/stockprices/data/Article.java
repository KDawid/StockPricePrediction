package elte.softwaretechnology.stockprices.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Article {
	private String snippet;
	private String title;
	private List<KeyWord> keyWords = new ArrayList<>();
	private LocalDateTime dateOfPublication;
	private Integer wordCount;
	private Double score; //TODO: check if this should be excluded, or not

	public void addKeyWord(KeyWord keyWord) {
		keyWords.add(keyWord);
	}

	@Override
	public String toString() {
		return "Article{" +
						"dateOfPublication='" + dateOfPublication + '\'' +
						", title=" + title +
						'}';
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<KeyWord> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<KeyWord> keyWords) {
		this.keyWords = keyWords;
	}

	public LocalDateTime getDateOfPublication() {
		return dateOfPublication;
	}

	public void setDateOfPublication(LocalDateTime dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public Integer getWordCount() {
		return wordCount;
	}

	public void setWordCount(Integer wordCount) {
		this.wordCount = wordCount;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}
