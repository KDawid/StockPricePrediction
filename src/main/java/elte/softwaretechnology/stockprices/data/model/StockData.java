package elte.softwaretechnology.stockprices.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "stock_data")
public class StockData {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "date", nullable = false, unique = true)
	private LocalDate date;

	@Column(name = "open", nullable = false)
	private double open;

	@Column(name = "close", nullable = false)
	private double close;

	@Column(name = "min", nullable = false)
	private double min;

	@Column(name = "max", nullable = false)
	private double max;

	public StockData(LocalDate date, double open, double close, double min, double max) {
		this.date = date;
		this.open = open;
		this.close = close;
		this.min = min;
		this.max = max;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "StockData{" +
						"id=" + id +
						", date=" + date +
						", open=" + open +
						", close=" + close +
						", min=" + min +
						", max=" + max +
						'}';
	}
}
