package elte.softwaretechnology.stockprices.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StockPricesController {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getHome() {
		return "index";
	}
	
	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String getIndex() {
		return "index";
	}
}
