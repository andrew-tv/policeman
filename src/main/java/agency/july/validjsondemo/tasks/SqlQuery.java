package agency.july.validjsondemo.tasks;

import static agency.july.validjsondemo.enums.DatabaseSingleton.MSSQL_DATABASE;

import agency.july.validjsondemo.enums.DatabaseSingleton;
import agency.july.validjsondemo.tests.JsonTest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Getter
@Setter
@Slf4j
@Component
public class SqlQuery implements Task {

    private String query;
    private DatabaseSingleton database;
    private JsonTest[] tests;

    @Override
    @JsonIgnore
    public void doIt() {

        log.info("SQL connectionUrl: {}", database.getConnectionUrl());
        log.info("SQL query: {}", query);

        JSONArray jsonResult = database.getData(this.query);
        log.info("SQL: Result data >>{}", jsonResult.toJSONString());

        DocumentContext documentContext = JsonPath.parse(jsonResult);

        Arrays.stream(tests).forEach(t-> {
            if (t.verify(documentContext)) {
                log.info("SQL: PASSED");
            } else {
                log.warn("SQL: FAILED");
            }
        });
    }
}
