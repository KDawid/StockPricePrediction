package elte.softwaretechnology.stockprices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import elte.softwaretechnology.stockprices.collector.NewYorkTimesDataCollector;

@Controller
public class StockPricesController {
	
	@Autowired
	private NewYorkTimesDataCollector newYorkTimesDataCollector;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getHome() {
		
		return "index";
	}
	
	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping(path = "/newyorktimes", method = RequestMethod.GET)
	public String getNewYorkTimesApi(Model model) {
		model.addAttribute("key", newYorkTimesDataCollector.getKey());
		model.addAttribute("html", newYorkTimesDataCollector.getHTML());
		return "newyorktimes";
	}
}
