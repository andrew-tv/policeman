package agency.july.validjsondemo.tasks;

import agency.july.validjsondemo.enums.DatabaseSingleton;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class SqlScript implements Task {

    private String script;
    private DatabaseSingleton database;

    @Override
    public void doIt() {

        log.info("SQL connectionUrl: {}", database.getConnectionUrl());
        log.info("SQL script: {}", script);

        database.runSqlScript(script);

    }
}
