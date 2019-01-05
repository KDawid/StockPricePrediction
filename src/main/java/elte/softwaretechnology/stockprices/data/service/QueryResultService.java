package elte.softwaretechnology.stockprices.data.service;

import elte.softwaretechnology.stockprices.data.model.QueryResult;
import elte.softwaretechnology.stockprices.data.repository.QueryResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class QueryResultService {
    @Autowired
    private QueryResultRepository repository;

    public QueryResult save(QueryResult queryResult) {
        System.out.println("Persisting query started");
        QueryResult persistedQueryResult = repository.save(queryResult);
        System.out.println("Persisting query finished");
        return persistedQueryResult;
    }
}
