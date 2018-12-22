package elte.softwaretechnology.stockprices.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Article {
	private String snippet;
	private String title;
	private boolean kicker;
	private List<KeyWord> keyWords = new ArrayList<>();
	private LocalDateTime dateOfPublication;
	private Integer wordCount;
	private Integer score; //TODO: check if this should be excluded, or not

	public void addKeyWord(KeyWord keyWord) {
		keyWords.add(keyWord);
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

	public boolean isKicker() {
		return kicker;
	}

	public void setKicker(boolean kicker) {
		this.kicker = kicker;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
