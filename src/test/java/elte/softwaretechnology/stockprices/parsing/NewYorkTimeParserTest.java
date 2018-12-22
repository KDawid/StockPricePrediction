package elte.softwaretechnology.stockprices.parsing;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class NewYorkTimeParserTest {
	@Test
	public void testResponseParsing() throws IOException {
		System.out.println(readInput("new_york_times_response_example.txt"));
	}

	protected String readInput(String fileName) throws IOException {
		return new String(Files.readAllBytes(new File(getClass().getResource(fileName).getFile()).toPath()));
	}
}
