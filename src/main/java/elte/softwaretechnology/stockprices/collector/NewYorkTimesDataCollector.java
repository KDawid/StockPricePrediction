package elte.softwaretechnology.stockprices.collector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Value;

public class NewYorkTimesDataCollector {

	@Value("${new.york.times.api.link}")
	private String newYorkTimesLink;
	
	@Value("${new.york.times.api.key}")
	private String key;
	
	public String getHTML() {
		String url = newYorkTimesLink;
		HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        method.setQueryString(new NameValuePair[] { 
        	    new NameValuePair("api-key", key),
        	    new NameValuePair("q", "apple mac"),
        	    new NameValuePair("begin_date", "20080101"),
        	    new NameValuePair("end_date", "20081207"),
        	    new NameValuePair("sort", "oldest")
        	}); 
        
        try {
            client.executeMethod(method);
            
            System.out.println(method.getStatusCode());

            if (method.getStatusCode() == HttpStatus.SC_OK) {
                InputStream is = method.getResponseBodyAsStream();

                if (is != null) {
                    Writer writer = new StringWriter();
                    char[] buffer = new char[1024];
                    try {
                        Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        int length;
                        while ((length = reader.read(buffer)) != -1) {
                            writer.write(buffer, 0, length);
                        }
                    } finally {
                        is.close();
                    }
                    String result = writer.toString();
                    
                    return result;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
		return "";
	}

	
	public String getKey() {
		return key;
	}
}
