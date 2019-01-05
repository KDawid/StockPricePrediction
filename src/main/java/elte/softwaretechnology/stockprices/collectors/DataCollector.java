package elte.softwaretechnology.stockprices.collectors;

import elte.softwaretechnology.stockprices.data.model.Meta;
import elte.softwaretechnology.stockprices.data.model.QueryResult;
import elte.softwaretechnology.stockprices.parsers.Parser;
import elte.softwaretechnology.stockprices.data.model.QueryParameter;
import elte.softwaretechnology.utils.WebUtil;
import org.apache.commons.httpclient.NameValuePair;

import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class DataCollector {
    private final String url;

    public DataCollector(String url) {
        this.url = url;
    }

    public QueryResult queryContent(QueryParameter queryParameter) {
        Parser parser = getParser();
        Meta meta = parser.parseResponeMeta(WebUtil.querySite(getUrl(), 1, getAllowedQuerriesPerSec(), queryParameter, this::getParams).get(0));
        System.out.println(meta);
        QueryResult queryResult = new QueryResult(meta, queryParameter);
        queryParameter.resetPage();
        List<String> responses = WebUtil.querySite(getUrl(), getNrOfRequiredQueries(meta), getAllowedQuerriesPerSec(), queryParameter, this::getParams);
        System.out.println("Parsing of responses started.");
        responses.forEach(message -> queryResult.addArticles(parser.parseResponeData(message)));
        System.out.println("Parsing of responses finished.");
        return queryResult;
    }

    private int getNrOfRequiredQueries(Meta meta) {
        return (int) Math.ceil((double) meta.getHits() / getNrOfArticlesPerQuery());
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

    protected abstract NameValuePair[] getParams(QueryParameter queryParameter);

    protected abstract int getAllowedQuerriesPerSec();

    protected abstract int getNrOfArticlesPerQuery();

    protected abstract Parser getParser();
}
