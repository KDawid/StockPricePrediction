package elte.softwaretechnology.stockprices.parsers;

import elte.softwaretechnology.stockprices.data.model.StockData;

public interface StockDataParser {
	StockData parseStockData(String line);
}
