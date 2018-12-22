package elte.softwaretechnology.stockprices.collectors.implementations;

import elte.softwaretechnology.stockprices.collectors.DataCollector;
import elte.softwaretechnology.utils.QueryParameters;
import org.apache.commons.httpclient.NameValuePair;

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
	protected NameValuePair[] getParams(QueryParameters queryParameters) {
		return new NameValuePair[] {
						new NameValuePair("api-key", key),
						new NameValuePair("fq", getContentFilter(queryParameters)),
						new NameValuePair("begin_date", queryParameters.getStartDate().format(getDateFormatter())),
						new NameValuePair("end_date", queryParameters.getEndDate().format(getDateFormatter())),
						new NameValuePair("sort", "oldest")
		};
	}

	private String getContentFilter(QueryParameters queryParameters) {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator mustHaveKeyWords = queryParameters.getMustHaveKeyWords().iterator();
		while (mustHaveKeyWords.hasNext()) {
			stringBuilder.append("body:(").append(mustHaveKeyWords.next()).append(") ");
			if (mustHaveKeyWords.hasNext()) {
				stringBuilder.append("AND ");
			}
		}

		if (!queryParameters.getMayHaveKeyWords().isEmpty()) {
			if (stringBuilder.length() != 0) {
				stringBuilder.append("AND ");//note that one of the may have values have to be contained
			}
			stringBuilder.append("body:(").append(String.join(" ", queryParameters.getMayHaveKeyWords())).append(")");
		}

		System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}
}