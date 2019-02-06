package com.home.trip.generator;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(50);

        int trips = 100;
        int counter = 0;
        String postURL = SERVER_ROOT + "/" + INDEX + "/" + TYPE;

        while (counter < trips) {
            executor.execute(() -> {
                RestTemplate template = new RestTemplate(Collections.singletonList(new MappingJackson2HttpMessageConverter()));

                TripRequest tripRequest = TripRequest.newRandomTrip();

                TripResponse response = template.postForObject(postURL,
                        tripRequest, TripResponse.class);

                System.out.println("Trip " + response.getResult() + ": " + tripRequest);
            });

            counter ++;
        }

        executor.shutdown();

        System.out.println("Done with " + trips + " trips!");
        
    }
}
