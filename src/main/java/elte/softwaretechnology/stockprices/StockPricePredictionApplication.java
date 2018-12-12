package elte.softwaretechnology.stockprices;

import elte.softwaretechnology.stockprices.collectors.NewYorkTimesDataCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
