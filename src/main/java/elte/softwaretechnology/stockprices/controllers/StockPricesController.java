package elte.softwaretechnology.stockprices.controllers;

import elte.softwaretechnology.stockprices.collectors.DataCollector;
import elte.softwaretechnology.stockprices.collectors.DataCollectorFactory;
import elte.softwaretechnology.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

import static elte.softwaretechnology.stockprices.collectors.DataCollectorType.newYorkTimes;

@Controller
public class StockPricesController {
	@Autowired
	private DataCollectorFactory dataCollectorFactory;

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
		DataCollector dataCollector = dataCollectorFactory.createOrRetrieveDataCollector(newYorkTimes);
		model.addAttribute("key", dataCollector.getKey());
		QueryParameters queryParameters = new QueryParameters(LocalDate.of(2009, 1, 1), LocalDate.of(2009, 1, 11))
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
		model.addAttribute("html", dataCollector.queryContent(queryParameters));
		return newYorkTimes.getId();
	}
}
