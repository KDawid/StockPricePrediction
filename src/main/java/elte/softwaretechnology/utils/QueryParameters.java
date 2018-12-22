package elte.softwaretechnology.utils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class QueryParameters {
	private LocalDate startDate;
	private LocalDate endDate;
	private Set<String> mustHaveKeyWords = new HashSet<>();
	private Set<String> mayHaveKeyWords = new HashSet<>();

	public QueryParameters(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public QueryParameters(LocalDate startDate, LocalDate endDate, Set<String> mustHaveKeyWords) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.mustHaveKeyWords = mustHaveKeyWords;
	}

	public QueryParameters addMayHaveKeyWord(String keyWord) {
		mayHaveKeyWords.add('"' + keyWord + '"');
		return this;
	}

	public QueryParameters removeMayHaveKeyWord(String keyWord) {
		mayHaveKeyWords.remove(keyWord);
		return this;
	}

	public QueryParameters addMustHaveKeyWord(String keyWord) {
		mustHaveKeyWords.add('"' + keyWord + '"');
		return this;
	}

	public QueryParameters removeMustHaveKeyWord(String keyWord) {
		mustHaveKeyWords.remove(keyWord);
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

	public Set<String> getMustHaveKeyWords() {
		return mustHaveKeyWords;
	}

	public Set<String> getMayHaveKeyWords() {
		return mayHaveKeyWords;
	}

	public void setMayHaveKeyWords(Set<String> mayHaveKeyWords) {
		this.mayHaveKeyWords = mayHaveKeyWords;
	}

	public void setMustHaveKeyWords(Set<String> mustHaveKeyWords) {
		this.mustHaveKeyWords = mustHaveKeyWords;
	}
}
