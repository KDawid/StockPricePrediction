package elte.softwaretechnology.stockprices.data.model;

import org.apache.tomcat.util.buf.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "query_parameter")
public class QueryParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @Column(name = "mustHaveKeyWords")
    private String mustHaveKeyWords = "";

    @Column(name = "mayHaveKeyWords")
    private String mayHaveKeyWords = "";

    @Column(name = "nextPage", nullable = false)
    private Integer nextPage = 0;

    @Column(name = "successful", nullable = false)
    private Boolean successful = true;

    public QueryParameter(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public QueryParameter addMayHaveKeyWord(String keyWord) {
        if (!mayHaveKeyWords.isEmpty()) {
            mayHaveKeyWords += ",";
        }
        mayHaveKeyWords += '"' + keyWord + '"';
        return this;
    }

    public QueryParameter addMustHaveKeyWord(String keyWord) {
        if (!mustHaveKeyWords.isEmpty()) {
            mustHaveKeyWords += ",";
        }
        mustHaveKeyWords += '"' + keyWord + '"';
        return this;
    }

    public void increasePage() {
        ++nextPage;
    }

    public void resetPage() {
        nextPage = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String[] getMustHaveKeyWords() {
        return mustHaveKeyWords.split(",");
    }

    public String[] getMayHaveKeyWords() {
        return mayHaveKeyWords.split(",");
    }

    public void setMayHaveKeyWords(String[] mayHaveKeyWords) {
        this.mayHaveKeyWords = StringUtils.join(mayHaveKeyWords);
    }

    public void setMustHaveKeyWords(String[] mustHaveKeyWords) {
        this.mustHaveKeyWords = StringUtils.join(mustHaveKeyWords);
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public Integer getNextPage() {
        return nextPage;
    }
}
