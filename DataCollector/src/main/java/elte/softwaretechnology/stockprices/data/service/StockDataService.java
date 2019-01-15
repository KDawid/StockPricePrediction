package elte.softwaretechnology.stockprices.data.service;

import elte.softwaretechnology.stockprices.data.model.StockData;
import elte.softwaretechnology.stockprices.data.repository.StockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class StockDataService {
	@Autowired
	private StockDataRepository repository;

	public StockData save(StockData stockData) {
		return repository.save(stockData);
	}

	public Iterable<StockData> saveAll(Iterable<StockData> stockData) {
		return repository.saveAll(stockData);
	}
}
