package elte.softwaretechnology.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WebUtil {
	public static List<String> querySite(String url, int nrOfQueries, int allowedQuerriesPerSec, QueryParameters queryParameters, Function<QueryParameters, NameValuePair[]> queryParameterWrappingFunction) {
		List<String> responses = new ArrayList<>();
		for (int i = 1; i <= nrOfQueries; ++i) {
			try {
				responses.add(querySite(url, queryParameterWrappingFunction.apply(queryParameters)));
				System.out.println("Query #" + i + " was successful.");
				if (i % allowedQuerriesPerSec == 0) {
					TimeUnit.SECONDS.sleep(1);
				}
			}
			catch (Exception e) {
				System.out.println("Querying failed after query: " + i + ".");
				e.printStackTrace();
			}
			queryParameters.increasePage();
		}
		return responses;
	}

	private static String querySite(String url, NameValuePair... nameValuePairs) {
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		method.setQueryString(nameValuePairs);
		try {
			client.executeMethod(method);
//			System.out.println(method.getStatusCode());
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				InputStream is = method.getResponseBodyAsStream();

				if (is != null) {
					Writer writer = new StringWriter();
					char[] buffer = new char[1024];
					try {
						Reader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
						int length;
						while ((length = reader.read(buffer)) != -1) {
							writer.write(buffer, 0, length);
						}
					}
					finally {
						is.close();
					}

					return writer.toString();
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			method.releaseConnection();
		}
		return "";
	}
}
