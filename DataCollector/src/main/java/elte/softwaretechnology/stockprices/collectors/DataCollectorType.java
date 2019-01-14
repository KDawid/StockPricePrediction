package elte.softwaretechnology.stockprices.collectors;

import elte.softwaretechnology.utils.Identifiable;

public enum DataCollectorType implements Identifiable<String> {

	NewYorkTimes("newyorktimes");

	private final String name;

	DataCollectorType(String name) {
		this.name = name;
	}

	@Override
	public String getId() {
		return name;
	}
}
