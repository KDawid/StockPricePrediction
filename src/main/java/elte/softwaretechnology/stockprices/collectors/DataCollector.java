package elte.softwaretechnology.stockprices.collectors;

import elte.softwaretechnology.utils.QueryParameters;
import elte.softwaretechnology.utils.WebUtil;
import org.apache.commons.httpclient.NameValuePair;

import java.time.format.DateTimeFormatter;

public abstract class DataCollector {

	private final String url;

	public DataCollector(String url) {
		this.url = url;
	}

	public String queryContent(QueryParameters queryParameters) {
		return WebUtil.querySite(getUrl(), getParams(queryParameters));
	}

	protected DateTimeFormatter getDateFormatter() {
		return DateTimeFormatter.ofPattern("yyyyMMdd");
	}

	public String getUrl() {
		return url;
	}

	public String getKey() {
		return "Key does not exists";
	}

	protected abstract NameValuePair[] getParams(QueryParameters queryParameters);
}
