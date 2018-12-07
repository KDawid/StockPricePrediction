package elte.softwaretechnology.stockprices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import elte.softwaretechnology.stockprices.collector.NewYorkTimesDataCollector;

@SpringBootApplication
public class StockPricePredictionApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockPricePredictionApplication.class, args);
	}
	
	@Bean
	public NewYorkTimesDataCollector buildNewYorkTimesDataCollector() {
		NewYorkTimesDataCollector newYorkTimesDataCollector = new NewYorkTimesDataCollector();
		return newYorkTimesDataCollector;
	}
}
