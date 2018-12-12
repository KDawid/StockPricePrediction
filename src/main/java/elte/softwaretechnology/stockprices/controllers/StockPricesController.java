package elte.softwaretechnology.stockprices.controllers;

import elte.softwaretechnology.stockprices.collectors.NewYorkTimesDataCollector;
import elte.softwaretechnology.stockprices.collectors.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class StockPricesController {
	
	@Autowired
	private NewYorkTimesDataCollector newYorkTimesDataCollector;

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
		model.addAttribute("key", newYorkTimesDataCollector.getKey());
		model.addAttribute("html", newYorkTimesDataCollector.queryContent(new QueryParameters(LocalDate.of(2015, 02, 20), LocalDate.of(2015, 02, 21)).addKeyWord("apple")));
		return "newyorktimes";
	}
}
