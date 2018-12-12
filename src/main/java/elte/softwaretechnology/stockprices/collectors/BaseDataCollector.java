package elte.softwaretechnology.stockprices.collectors;

import elte.softwaretechnology.utils.WebUtil;
import org.apache.commons.httpclient.NameValuePair;

import java.time.format.DateTimeFormatter;

public abstract class BaseDataCollector {
	public String queryContent(QueryParameters queryParameters) {
		return WebUtil.querySite(getUrl(), getParams(queryParameters));
	}

	protected DateTimeFormatter getDateFormatter() {
		return DateTimeFormatter.ofPattern("yyyyMMdd");
	}

	protected abstract String getUrl();

	protected abstract NameValuePair[] getParams(QueryParameters queryParameters);
}
