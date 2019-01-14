package elte.softwaretechnology.scripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import elte.softwaretechnology.stockprices.data.model.StockData;
import elte.softwaretechnology.stockprices.parsers.implementations.DailyStockDataParser;

public class StockPriceNormalizer {
	private static final String STOCK_PRICES_FILE_PATH = "src/main/resources/AAPL.csv";
	private static final String STOCK_PRICES_RESUL_FILE_PATH = "src/main/resources/AAPL_normalized.csv";

	private DailyStockDataParser dailyStockDataParser = new DailyStockDataParser();
	private Scanner scanner = null;
	private List<StockData> stockData = new ArrayList<StockData>();

	public static void main(String[] args) throws IOException {
		System.out.println("Starting normalization");
		StockPriceNormalizer normalizer = new StockPriceNormalizer();
		normalizer.readData();
		normalizer.transformData();
		normalizer.saveNormalizedData();
		System.out.println("Normalization has finished successfully");
	}

	private void readData() {
		try {
			scanner = new Scanner(new File(STOCK_PRICES_FILE_PATH));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner.nextLine(); //first row is header
		while (scanner.hasNext()) {
			stockData.add(dailyStockDataParser.parseStockData(scanner.nextLine()));
		}
	}

	private void transformData() {
		for (StockData data : stockData) {
			double open = data.getOpen();
			data.setOpen(0.0);
			data.setClose(data.getClose() - open);
			data.setMax(data.getMax() - open);
			data.setMin(data.getMin() - open);
		}
	}

	private void saveNormalizedData() throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(STOCK_PRICES_RESUL_FILE_PATH));

				CSVPrinter csvPrinter = new CSVPrinter(writer,
						CSVFormat.DEFAULT.withHeader("Date", "Open", "High", "Low", "Close"))) {
			for (StockData data : stockData) {
				String date = data.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
				String open = String.format("%.6f", data.getOpen());
				String max = String.format("%.6f", data.getMax());
				String min = String.format("%.6f", data.getMin());
				String close = String.format("%.6f", data.getClose());

				csvPrinter.printRecord(Arrays.asList(date, open, max, min, close));
			}
			csvPrinter.flush();
		}
	}
}
