package elte.softwaretechnology.stockprices.collectors;

import elte.softwaretechnology.stockprices.data.model.Meta;
import elte.softwaretechnology.stockprices.data.model.QueryResponse;
import elte.softwaretechnology.stockprices.parsers.Parser;
import elte.softwaretechnology.utils.QueryParameters;
import elte.softwaretechnology.utils.WebUtil;
import org.apache.commons.httpclient.NameValuePair;

import java.time.format.DateTimeFormatter;

public abstract class DataCollector {
	private final String url;

	public DataCollector(String url) {
		this.url = url;
	}

	public QueryResponse queryContent(QueryParameters queryParameters) {
		Parser parser = getParser();
		Meta meta = parser.parseResponeMeta(WebUtil.querySite(getUrl(), 1, getAllowedQuerriesPerSec(), queryParameters, this::getParams).get(0));
		System.out.println(meta);
		QueryResponse queryResponse = new QueryResponse(meta);
		queryParameters.resetPage();
		WebUtil.querySite(getUrl(), getNrOfRequiredQueries(meta), getAllowedQuerriesPerSec(), queryParameters, this::getParams)
						.forEach(message -> {
							queryResponse.addArticles(parser.parseResponeData(message));
							queryResponse.addParsedMessage(message);
							queryResponse.addParsedMessage("/n");
										}

						);
		return queryResponse;
	}

	private int getNrOfRequiredQueries(Meta meta) {
		return (int)Math.ceil((double)meta.getHits() / getNrOfArticlesPerQuery());
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

	protected abstract int getAllowedQuerriesPerSec();

	protected abstract int getNrOfArticlesPerQuery();

	protected abstract Parser getParser();
}
