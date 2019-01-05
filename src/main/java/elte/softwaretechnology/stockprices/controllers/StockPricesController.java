package elte.softwaretechnology.stockprices.controllers;

import elte.softwaretechnology.stockprices.collectors.DataCollector;
import elte.softwaretechnology.stockprices.collectors.DataCollectorFactory;
import elte.softwaretechnology.stockprices.data.model.Article;
import elte.softwaretechnology.stockprices.data.model.KeyWord;
import elte.softwaretechnology.stockprices.data.model.Meta;
import elte.softwaretechnology.stockprices.data.model.QueryResult;
import elte.softwaretechnology.stockprices.data.service.QueryResultService;
import elte.softwaretechnology.stockprices.data.model.QueryParameter;
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
	private QueryResultService queryResultService;

	@GetMapping(path = "/")
	public String getHome() {

		return "index";
	}

	@GetMapping(path = "/index")
	public String getIndex() {
		return "index";
	}

	@GetMapping(path = "/newyorktimes")
	public String getNewYorkTimesApi(Model model) {
		DataCollector dataCollector = dataCollectorFactory.createOrRetrieveDataCollector(NewYorkTimes);
		model.addAttribute("key", dataCollector.getKey());
		QueryParameter queryParameter = new QueryParameter(LocalDate.of(2009, 1, 1), LocalDate.of(2009, 1, 9))
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
		QueryResult queryResult = dataCollector.queryContent(queryParameter);
		queryResultService.save(queryResult);
		model.addAttribute("html", "done");
		return NewYorkTimes.getId();
	}
}
