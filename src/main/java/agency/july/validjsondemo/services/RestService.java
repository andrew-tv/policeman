package agency.july.validjsondemo.services;

import agency.july.validjsondemo.models.Request;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        // Set connection and read timeouts
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
    }

    public String sendRequest(Request request) {

        System.out.println("Request: "+ request);

        ResponseEntity<String> response = request.getMethod().send(request);

        // check response status code
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("OK");
            return response.getBody();
        } else {
            System.out.println("WTF");
            return null;
        }
    }

}
