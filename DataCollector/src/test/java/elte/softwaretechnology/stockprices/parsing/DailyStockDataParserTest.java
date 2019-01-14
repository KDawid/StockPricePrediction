package elte.softwaretechnology.stockprices.parsing;

import elte.softwaretechnology.stockprices.data.model.StockData;
import elte.softwaretechnology.stockprices.parsers.StockDataParser;
import elte.softwaretechnology.stockprices.parsers.implementations.DailyStockDataParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DailyStockDataParserTest {

	@Test
	public void testStockDataParsing() {
		String parsingDataStr = "1/12/2009,12.922857,12.998571,12.507143,12.665714\n";
		StockDataParser stockDataParser = new DailyStockDataParser();
		StockData stockData = stockDataParser.parseStockData(parsingDataStr);
		assertEquals(12.922857, stockData.getOpen(), 0.00001);
		assertEquals(12.998571, stockData.getMax(), 0.00001);
		assertEquals(12.507143, stockData.getMin(), 0.00001);
		assertEquals(12.665714, stockData.getClose(), 0.00001);
		assertEquals("2009-01-12", stockData.getDate().toString());
	}
}
