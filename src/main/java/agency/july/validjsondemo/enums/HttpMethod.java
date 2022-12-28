package agency.july.validjsondemo.enums;

import agency.july.validjsondemo.models.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public enum HttpMethod {
    GET {
        @Override
        public ResponseEntity<String> send(Request request) {
            return restTemplate.getForEntity(fullStringUrl(request), String.class);
        }
    },
    POST {
        @Override
        public ResponseEntity<String> send(Request request) {
            return restTemplate.postForEntity(fullStringUrl(request), new HttpEntity<>(request.getBody(), request.getHeaders()), String.class);
        }
    };

    private final static RestTemplate restTemplate = new RestTemplate();

    private static String fullStringUrl(Request request) {
        return request.getService().getURL().toString().concat(request.getQuery()==null?"":request.getQuery());
    }

    public ResponseEntity<String> send(Request request) {
        return null;
    }

}
