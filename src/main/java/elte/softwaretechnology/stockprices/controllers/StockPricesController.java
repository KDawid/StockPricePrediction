package elte.softwaretechnology.stockprices.controllers;

import elte.softwaretechnology.stockprices.collectors.DataCollector;
import elte.softwaretechnology.stockprices.collectors.DataCollectorFactory;
import elte.softwaretechnology.stockprices.data.model.Article;
import elte.softwaretechnology.stockprices.data.model.KeyWord;
import elte.softwaretechnology.stockprices.data.model.Meta;
import elte.softwaretechnology.stockprices.data.model.QueryResponse;
import elte.softwaretechnology.stockprices.data.service.QueryResponseService;
import elte.softwaretechnology.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static elte.softwaretechnology.stockprices.collectors.DataCollectorType.NewYorkTimes;

@RestController
@Transactional
public class StockPricesController {
	@Autowired
	private DataCollectorFactory dataCollectorFactory;

	@Autowired
	private QueryResponseService queryResponseService;

	@GetMapping(path = "/")
	public String getHome() {

		return "index";
	}

	@GetMapping(path = "/index")
	public String getIndex() {
		return "index";
	}

	@GetMapping(path = "/test")
	public void test() {
		QueryResponse queryResponse = new QueryResponse(new Meta());
		Article article = new Article();
		article.setDateOfPublication(LocalDateTime.now());
		article.setTitle("title");
		KeyWord keyWord = new KeyWord();
		keyWord.setKeyWord("asd");
		keyWord.setMajor("cica");
		article.addKeyWord(keyWord);
		queryResponse.addArticles(Collections.singletonList(article));
		queryResponseService.save(queryResponse);
	}

	@GetMapping(path = "/newyorktimes")
	public String getNewYorkTimesApi(Model model) {
		DataCollector dataCollector = dataCollectorFactory.createOrRetrieveDataCollector(NewYorkTimes);
		model.addAttribute("key", dataCollector.getKey());
		QueryParameters queryParameters = new QueryParameters(LocalDate.of(2009, 1, 1), LocalDate.of(2009, 1, 3))
						.addMayHaveKeyWord("IOS")
						.addMayHaveKeyWord("OS X")
						.addMayHaveKeyWord("IPAD")
						.addMayHaveKeyWord("MAC")
						.addMayHaveKeyWord("STEVE JOBS")
						.addMayHaveKeyWord("SMART PHONE")
						.addMayHaveKeyWord("ITUNES")
						.addMayHaveKeyWord("APP STORE")
						.addMayHaveKeyWord("IPHONE")
						.addMayHaveKeyWord("AAPL")
						.addMustHaveKeyWord("APPLE");
		QueryResponse queryResponse = dataCollector.queryContent(queryParameters);
		queryResponseService.save(queryResponse);
		model.addAttribute("html", queryResponse.getParsedMessage());
		return NewYorkTimes.getId();
	}
}
