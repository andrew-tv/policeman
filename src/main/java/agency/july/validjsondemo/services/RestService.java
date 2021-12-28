package agency.july.validjsondemo.services;

import agency.july.validjsondemo.models.Request;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    public JsonNode sendRequest(Request request) throws MalformedURLException {

        URL url = new URL("http", "localhost", 8082, "/jsonrpc/v1");

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // create a map for post parameters
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        map.put("title", "Introduction to Spring Boot");
        map.put("body", "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.");

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        // send POST request
        ResponseEntity<JsonNode> response = this.restTemplate.postForEntity(url.toString(), entity, JsonNode.class);

        // check response status code
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            return null;
        }
    }

}
