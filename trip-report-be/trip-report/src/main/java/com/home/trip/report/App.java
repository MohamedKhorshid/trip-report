package com.home.trip.report;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 */
public final class App {
    private static final String TYPE = "trip";
    private static final String INDEX = "trips";
    private static final String SERVER_ROOT = "http://localhost:9200";

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        
    
        String getURL = SERVER_ROOT + "/" + INDEX + "/" + TYPE + "/_search";

        RestTemplate template = new RestTemplate(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
        template.setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptor(){
        
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                System.out.println(new String(body, "UTF-8"));
                ClientHttpResponse response = execution.execute(request, body);
                System.out.println(StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
                return response;
            }
        }));
        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory() {
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                if (httpMethod == HttpMethod.GET) {
                    HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase = new HttpEntityEnclosingRequestBase() {
                        @Override
                        public String getMethod() {
                            return HttpMethod.GET.name();
                        }
                    };

                    httpEntityEnclosingRequestBase.setURI(uri);
                    
                    return httpEntityEnclosingRequestBase;
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        });

        TripReportRequest tripRequest = new TripReportRequest();
        tripRequest.setQuery(new TripReportRequest.Query());
        tripRequest.getQuery().setRange(new TripReportRequest.Query.Range());
        tripRequest.getQuery().getRange().setStart(new TripReportRequest.Query.Range.RangeAttr());
        tripRequest.getQuery().getRange().getStart().setLte(1548169300000l);
        tripRequest.getQuery().getRange().getStart().setGte(1548169299800l);

        HttpEntity<TripReportRequest> entity = new HttpEntity<>(tripRequest);

        long start = new Date().getTime();
        System.out.println("Started searching...");

        // String query = "{\"query\": {\"range\": {\"start\":{\"gte\":1548169299800, \"lte\": 1548169300000}}}}";
        ResponseEntity<TripReportResponse> response = template.exchange(getURL, HttpMethod.GET, entity,
                TripReportResponse.class);

        long end = new Date().getTime();
        System.out.println("Done searching...");
        // System.out.println(query);
        System.out.println(response.getBody());

        long remainder = end - start;
        int millis = (int) remainder % 1000;
        remainder = remainder / 1000;
        int seconds = (int) (remainder % 60);
        remainder = remainder / 60;
        int minutes = (int) (remainder % 60);
        remainder = remainder / 60;
        int hours = (int) (remainder % 24);
        remainder = remainder / 24;

        System.out.print("Elapsed time: ");
        System.out.print(hours + " hours ");
        System.out.print(minutes + " minutes ");
        System.out.print(seconds + " seconds ");
        System.out.print(millis + " millis");
    }
}
