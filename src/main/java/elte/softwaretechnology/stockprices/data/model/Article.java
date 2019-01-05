package elte.softwaretechnology.stockprices.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "snippet")
    private String snippet;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "article_id")
    private List<KeyWord> keyWords = new ArrayList<>();

    @Column(name = "dateOfPublication", nullable = false)
    private LocalDateTime dateOfPublication;

    @Column(name = "wordCount")
    private Integer wordCount;

    @Column(name = "score")
    private Double score; //TODO: check if this should be excluded, or not

    public void addKeyWord(KeyWord keyWord) {
        keyWords.add(keyWord);
    }

    @Override
    public String toString() {
        return "Article{" +
                "dateOfPublication='" + dateOfPublication + '\'' +
                ", title=" + title +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<KeyWord> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<KeyWord> keyWords) {
        this.keyWords = keyWords;
    }

    public LocalDateTime getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDateTime dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
