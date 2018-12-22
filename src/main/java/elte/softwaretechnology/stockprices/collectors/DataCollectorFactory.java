package elte.softwaretechnology.stockprices.collectors;

import elte.softwaretechnology.exceptions.NotImplementedException;
import elte.softwaretechnology.stockprices.collectors.implementations.NewYorkTimesDataCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataCollectorFactory {
	@Autowired
	private Environment environment;

	private Map<DataCollectorType, DataCollector> dataCollectors = new HashMap<>();

	private DataCollectorFactory() {
	}

	public DataCollector createOrRetrieveDataCollector(DataCollectorType dataCollectorType) {
		DataCollector dataCollector = dataCollectors.get(dataCollectorType);
		if (dataCollector == null) {
			if (dataCollectorType == DataCollectorType.newYorkTimes) {
				dataCollector = new NewYorkTimesDataCollector(environment.resolvePlaceholders("${new.york.times.api.link}"), environment.resolvePlaceholders("${new.york.times.api.key}"));
			}
			else {
				throw new NotImplementedException();
			}
		}
		dataCollectors.put(dataCollectorType, dataCollector);
		return dataCollector;
	}
}
