package elte.softwaretechnology.stockprices.controllers;

import static elte.softwaretechnology.stockprices.collectors.DataCollectorType.NewYorkTimes;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import elte.softwaretechnology.stockprices.collectors.DataCollector;
import elte.softwaretechnology.stockprices.collectors.DataCollectorFactory;
import elte.softwaretechnology.stockprices.data.model.QueryParameter;
import elte.softwaretechnology.stockprices.data.model.QueryResult;
import elte.softwaretechnology.stockprices.data.service.QueryResultService;
import elte.softwaretechnology.stockprices.data.service.StockDataService;
import elte.softwaretechnology.stockprices.parsers.implementations.DailyStockDataParser;

@Controller
@Transactional
public class StockPricesController { //todo another controller for different purposes
	@Autowired
	private DataCollectorFactory dataCollectorFactory;

	@Autowired
	private QueryResultService queryResultService;

	@Autowired
	private StockDataService stockDataService;

	@Value("${new.york.times.start.date}")
	private String startDate;

	@Value("${new.york.times.end.date}")
	private String endDate;

	@GetMapping(path = "/")
	public String getHome() {
		return "index";
	}

	@GetMapping(path = "/index")
	public String getIndex() {
		return "index";
	}

	@GetMapping(path = "/stockdata")
	public String getStockData() {
		DailyStockDataParser dailyStockDataParser = new DailyStockDataParser();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("src/main/resources/AAPL.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner.nextLine(); //first row is header
		while (scanner.hasNext()) {
			stockDataService.save(dailyStockDataParser.parseStockData(scanner.nextLine()));
		}

		return "index"; //TODO create a proper page
	}

	@GetMapping(path = "/newyorktimes")
	public String getNewYorkTimesApi(Model model) {
		DataCollector dataCollector = dataCollectorFactory.createOrRetrieveDataCollector(NewYorkTimes);
		model.addAttribute("key", dataCollector.getKey());
		QueryParameter queryParameter = new QueryParameter(getDate(startDate), getDate(endDate))
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

	private LocalDate getDate(String date) {
		int[] dateArray = Arrays.asList(date.split("-")).stream().mapToInt(Integer::parseInt).toArray();
		return LocalDate.of(dateArray[0], dateArray[1], dateArray[2]);
	}
}
