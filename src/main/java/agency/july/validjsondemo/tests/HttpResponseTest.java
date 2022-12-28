package agency.july.validjsondemo.tests;

import agency.july.validjsondemo.enums.Operation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@Slf4j
public class HttpResponseTest implements Test {

    @JsonProperty("data_type")
    private String dataType;
    private Operation operation;
    private String what;
    private JsonNode expectation;

    @Override
    @JsonIgnore
    public boolean verify(ResponseEntity<String> response) {

        switch (operation) {
            case EQUAL:
                DocumentContext responseContext = JsonPath.parse(response.getBody());
                DocumentContext expectationContext = JsonPath.parse(expectation.toString());

                log.info("TEST: verify: what: {}", what);
                log.info("TEST: expectation raw: {}", expectation);
                log.info("TEST: reality     map: {}", responseContext.read(what).toString());
                log.info("TEST: expectation map: {}", expectationContext.read("$").toString());

                return responseContext.read(what).equals(expectationContext.read("$"));

            default:
                throw new IllegalStateException("Invalid predicate: " + operation);
        }
    }

    @Override
    public boolean verify(DocumentContext documentContext) {
        return false;
    }
}
