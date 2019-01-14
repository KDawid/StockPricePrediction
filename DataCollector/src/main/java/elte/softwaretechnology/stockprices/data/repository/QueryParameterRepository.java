package elte.softwaretechnology.stockprices.data.repository;

import elte.softwaretechnology.stockprices.data.model.Article;
import elte.softwaretechnology.stockprices.data.model.QueryParameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryParameterRepository extends CrudRepository<QueryParameter, Long> {
}
