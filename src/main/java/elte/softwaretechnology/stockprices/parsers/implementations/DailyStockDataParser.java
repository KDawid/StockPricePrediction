package elte.softwaretechnology.stockprices.parsers.implementations;

import elte.softwaretechnology.stockprices.data.model.StockData;
import elte.softwaretechnology.stockprices.parsers.StockDataParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//todo write proper tests
public class DailyStockDataParser implements StockDataParser {

	@Override
	public StockData parseStockData(String line) {
		String[] splitLine = line.split(",");
		StockData stockData = new StockData(
						LocalDate.parse(splitLine[0], DateTimeFormatter.ofPattern("M/d/yyyy")),
						Double.valueOf(splitLine[1]),
						Double.valueOf(splitLine[4]),
						Double.valueOf(splitLine[3]),
						Double.valueOf(splitLine[2])
		);
		return stockData;
	}
}
