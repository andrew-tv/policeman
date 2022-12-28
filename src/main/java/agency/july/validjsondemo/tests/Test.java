package agency.july.validjsondemo.tests;

import com.jayway.jsonpath.DocumentContext;
import org.springframework.http.ResponseEntity;

public interface Test {
    boolean verify(ResponseEntity<String> response);
    boolean verify(DocumentContext documentContext);
}
