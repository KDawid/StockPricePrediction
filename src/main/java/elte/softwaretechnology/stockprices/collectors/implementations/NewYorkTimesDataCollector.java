package elte.softwaretechnology.stockprices.collectors.implementations;

import elte.softwaretechnology.stockprices.collectors.DataCollector;
import elte.softwaretechnology.stockprices.parsers.Parser;
import elte.softwaretechnology.stockprices.parsers.implementations.NewYorkTimesDataParser;
import elte.softwaretechnology.stockprices.data.model.QueryParameter;
import org.apache.commons.httpclient.NameValuePair;

import java.util.Arrays;
import java.util.Iterator;

public class NewYorkTimesDataCollector extends DataCollector {
	private final String key;

	public NewYorkTimesDataCollector(String newYorkTimesLink, String key) {
		super(newYorkTimesLink);
		this.key = key;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	protected NameValuePair[] getParams(QueryParameter queryParameter) {
		return new NameValuePair[] {
						new NameValuePair("api-key", key),
						new NameValuePair("fq", getContentFilter(queryParameter)),
						new NameValuePair("begin_date", queryParameter.getStartDate().format(getDateFormatter())),
						new NameValuePair("end_date", queryParameter.getEndDate().format(getDateFormatter())),
						new NameValuePair("sort", "oldest"),
						new NameValuePair("page", queryParameter.getNextPage().toString())
		};
	}

	private String getContentFilter(QueryParameter queryParameter) {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator mustHaveKeyWords = Arrays.asList(queryParameter.getMustHaveKeyWords()).iterator();
		while (mustHaveKeyWords.hasNext()) {
			stringBuilder.append("body:(").append(mustHaveKeyWords.next()).append(") ");
			if (mustHaveKeyWords.hasNext()) {
				stringBuilder.append("AND ");
			}
		}

		if (queryParameter.getMayHaveKeyWords().length != 0) {
			if (stringBuilder.length() != 0) {
				stringBuilder.append("AND ");//note that one of the may have values have to be contained
			}
			stringBuilder.append("body:(").append(String.join(" ", queryParameter.getMayHaveKeyWords())).append(")");
		}

		return stringBuilder.toString();
	}

	@Override
	protected int getAllowedQuerriesPerSec() {
		return 1;
	}

	@Override
	protected int getNrOfArticlesPerQuery() {
		return 10;
	}

	@Override
	protected Parser getParser() {
		return new NewYorkTimesDataParser();
	}
}