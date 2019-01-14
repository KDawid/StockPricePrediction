package elte.softwaretechnology.stockprices.data.model;

import javax.persistence.*;

@Entity
@Table(name = "meta")
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;


    @Column(name = "hits", nullable = false)
    private Integer hits;

    @Column(name = "_offset")
    private Integer offset;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "hits=" + hits +
                ", offset=" + offset +
                '}';
    }
}
