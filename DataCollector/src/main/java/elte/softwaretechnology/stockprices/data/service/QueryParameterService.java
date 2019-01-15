package elte.softwaretechnology.stockprices.data.service;

import elte.softwaretechnology.stockprices.data.model.KeyWord;
import elte.softwaretechnology.stockprices.data.model.QueryParameter;
import elte.softwaretechnology.stockprices.data.repository.KeyWordRepository;
import elte.softwaretechnology.stockprices.data.repository.QueryParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class QueryParameterService {
	@Autowired
	private QueryParameterRepository repository;

	public QueryParameter save(QueryParameter queryParameter) {
		return repository.save(queryParameter);
	}
}
