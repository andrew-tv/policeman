package agency.july.validjsondemo.models;

import agency.july.validjsondemo.enums.HttpMethod;
import agency.july.validjsondemo.enums.ServiceSingleton;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@ToString
public class Request {

    private HttpMethod method;
    private ServiceSingleton service;
    private HttpHeaders headers;
    private JsonNode body;
    private String query;

    @JsonIgnore
    public ResponseEntity send() {

        return method.send(this);

    }

}
