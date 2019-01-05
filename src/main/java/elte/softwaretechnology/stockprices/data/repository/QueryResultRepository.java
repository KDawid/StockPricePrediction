package elte.softwaretechnology.stockprices.data.repository;

import elte.softwaretechnology.stockprices.data.model.QueryParameter;
import elte.softwaretechnology.stockprices.data.model.QueryResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryResultRepository extends CrudRepository<QueryResult, Long> {
}
