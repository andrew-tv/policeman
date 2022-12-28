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
public class JsonTest implements Test {

    @JsonProperty("data_type")
    private String dataType;
    private Operation operation;
    private String what;
    private JsonNode expectation;

    @JsonIgnore
    @Override
    public boolean verify(ResponseEntity<String> response) {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean verify(DocumentContext documentContext) {
        switch (operation) {
            case EQUAL:
                DocumentContext expectationContext = JsonPath.parse(expectation.toString());

                log.info("TEST: verify: what: {}", what);
                log.info("TEST: expectation raw: {}", expectation);
                log.info("TEST: reality     map: {}", documentContext.read(what).toString());
                log.info("TEST: expectation map: {}", expectationContext.read("$").toString());

                return documentContext.read(what).equals(expectationContext.read("$"));

            default:
                throw new IllegalStateException("Invalid predicate: " + operation);
        }
    }
}
