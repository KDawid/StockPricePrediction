package elte.softwaretechnology.utils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.join;

public class QueryParameters {
	private LocalDate startDate;
	private LocalDate endDate;
	private Set<String> keyWords = new HashSet<>();

	public QueryParameters(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public QueryParameters(LocalDate startDate, LocalDate endDate, Set<String> keyWords) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.keyWords = keyWords;
	}

	public QueryParameters addKeyWord(String keyWord) {
		keyWords.add(keyWord);
		return this;
	}

	public QueryParameters removeKeyWord(String keyWord) {
		keyWords.remove(keyWord);
		return this;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getKeyWords() {
		return getKeyWords(" ");
	}

	public String getKeyWords(String separator) {
		return join(separator, keyWords);
	}

	public void setKeyWords(Set<String> keyWords) {
		this.keyWords = keyWords;
	}
}
