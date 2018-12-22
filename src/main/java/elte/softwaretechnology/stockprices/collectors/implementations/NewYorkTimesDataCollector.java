package elte.softwaretechnology.stockprices.collectors.implementations;

import elte.softwaretechnology.stockprices.collectors.DataCollector;
import elte.softwaretechnology.utils.QueryParameters;
import org.apache.commons.httpclient.NameValuePair;

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
						new NameValuePair("q", queryParameters.getKeyWords(" ")),
						new NameValuePair("begin_date", queryParameters.getStartDate().format(getDateFormatter())),
						new NameValuePair("end_date", queryParameters.getEndDate().format(getDateFormatter())),
						new NameValuePair("sort", "oldest")
		};
	}
}