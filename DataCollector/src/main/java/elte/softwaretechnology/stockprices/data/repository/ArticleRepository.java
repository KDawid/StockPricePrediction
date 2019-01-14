package elte.softwaretechnology.stockprices.data.repository;

import elte.softwaretechnology.stockprices.data.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
