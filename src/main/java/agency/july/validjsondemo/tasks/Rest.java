package agency.july.validjsondemo.tasks;

import agency.july.validjsondemo.models.Request;
import agency.july.validjsondemo.tests.JsonTest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@Getter
@Setter
@Slf4j
public class Rest implements Task {

    private Request request;
    private JsonTest[] tests;

    @Override
    @JsonIgnore
    public void doIt() {

        log.info(String.format("REST: %s", request));

        ResponseEntity<String> response = request.send();
        DocumentContext responseContext = JsonPath.parse(response.getBody());

        log.info("REST: Status code: {}", response.getStatusCode());
        log.info("REST: Response body: {}", response.getBody());

        Arrays.stream(tests).forEach(t-> {
            if (t.verify(responseContext)) {
                log.info("REST: PASSED");
            } else {
                log.warn("REST: FAILED");
            }
        });

    }
}
