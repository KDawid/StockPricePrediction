package elte.softwaretechnology.stockprices.data.model;

public class Meta {
	private Integer hits;
	private Integer offset;

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
