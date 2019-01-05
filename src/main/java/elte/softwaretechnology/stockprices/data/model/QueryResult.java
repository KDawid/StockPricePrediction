package elte.softwaretechnology.stockprices.data.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "query_result")
public class QueryResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "query_result_id")
    private List<Article> articles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "meta_id")
    private Meta meta;

    @Column(name = "dateTimeOfQuery", nullable = false)
    private LocalDateTime dateTimeOfQuery = LocalDateTime.now();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "query_parameter_id")
    private QueryParameter queryParameter;

    public QueryResult(Meta meta, QueryParameter queryParameter) {
        this.meta = meta;
        this.queryParameter = queryParameter;
    }

    public void addArticles(List<Article> articles) {
        this.articles.addAll(articles);
    }


    public List<Article> getArticles() {
        return articles;
    }

    public Meta getMeta() {
        return meta;
    }

    public LocalDateTime getDateTimeOfQuery() {
        return dateTimeOfQuery;
    }

    public QueryParameter getQueryParameter() {
        return queryParameter;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void setDateTimeOfQuery(LocalDateTime dateTimeOfQuery) {
        this.dateTimeOfQuery = dateTimeOfQuery;
    }

    public void setQueryParameter(QueryParameter queryParameter) {
        this.queryParameter = queryParameter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
