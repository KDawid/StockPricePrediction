package elte.softwaretechnology.stockprices.collectors;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Value;

public class NewYorkTimesDataCollector extends DataCollector {

	@Value("${new.york.times.api.link}")
	private String newYorkTimesLink;

	@Value("${new.york.times.api.key}")
	private String key;

	@Override
	protected String getUrl() {
		return newYorkTimesLink;
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

	public String getKey() {
		return key;
	}
}
