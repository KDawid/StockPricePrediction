package elte.softwaretechnology.stockprices.data.repository;

import elte.softwaretechnology.stockprices.data.model.KeyWord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyWordRepository extends CrudRepository<KeyWord, Long> {
}
