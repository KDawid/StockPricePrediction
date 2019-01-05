package elte.softwaretechnology.stockprices.data.repository;

import elte.softwaretechnology.stockprices.data.model.Article;
import elte.softwaretechnology.stockprices.data.model.Meta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends CrudRepository<Meta, Long> {
}
