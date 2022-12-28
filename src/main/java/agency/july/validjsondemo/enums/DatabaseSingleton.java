package agency.july.validjsondemo.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Getter
public enum DatabaseSingleton {

    MSSQL_DATABASE("jdbc:sqlserver", "localhost", 1433, System.getenv("MSSQL_DATABASE"), "sa", System.getenv("MSSQL_SA_PASSWORD"));

    private String protocol;
    private String host;
    private int port;
    private String databaseName;
    private String userName;
    private String password;
    private Connection connection;
    private Statement statement;

    DatabaseSingleton (String protocol, String host, int port, String databaseName, String userName, String password) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
        try {
            this.connection = DriverManager.getConnection(getConnectionUrl(), getUserName(), getPassword());
            this.statement = getConnection().createStatement();
        } catch (SQLException e) {
            System.err.println("Impossible to connect to SQL server. Connection url was: " + getConnectionUrl());
            throw new RuntimeException(e);
        }
    }

    private static JSONArray resultSet2JsonArray(ResultSet rs) throws SQLException {

        int totalColumns = rs.getMetaData().getColumnCount();

        JSONArray jsonArray = new JSONArray();

        // Iterate through the data in the result set
        while (rs.next()) {
            JSONObject obj = new JSONObject();
            for (int i = 1; i <= totalColumns; i++) {
                obj.put(rs.getMetaData().getColumnLabel(i),
                        // Convert timestamp to string, leave other types as is
                        rs.getObject(i) instanceof Timestamp ? rs.getObject(i).toString() : rs.getObject(i));
            }
            jsonArray.add(obj);
        }
        return jsonArray;
    }

    private static List<String> parseScript2SqlCommands (Stream<String> lines) {

        String inOneLine = lines
                .map(l -> l.replaceFirst("--.*", "")) // remove comments
                .map(l -> l.trim()) // trim unnecessary spaces
                .filter(l -> l.length() > 0)
                .collect(Collectors.joining(" ")); // join all into one line

        return Stream.of(inOneLine.split("; ?")) // split by semicolon
                .map(String::new)
                .map(l -> l.concat(";"))
                .collect(Collectors.toList());
    }

    public String getConnectionUrl() {
        return protocol + "://" + host + ":" + port + ";databaseName=" + databaseName;
    }

    public JSONArray getData(String query) {
        try {
            return resultSet2JsonArray(getStatement().executeQuery(query));
        } catch (SQLException e) {
            log.error("Impossible to fetch data. Connection url was: {}. Query: {}", getConnectionUrl(), query);
            throw new RuntimeException(e);
        }
    }

    public void runSqlScript(String script) {

        try (Stream<String> lines = Files.lines(Paths.get(script)).onClose(() -> log.info("Reading the script {} was finished", script))) {

            List<String> sqlStatements = parseScript2SqlCommands(lines);

            sqlStatements.forEach(s -> log.info(s));

            try {
                statement.clearBatch();
                sqlStatements.forEach((sql) -> {
                    try {
                        statement.addBatch(sql);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

                int[] updateCounts = statement.executeBatch();
//                statement.close();
                Arrays.stream(updateCounts)
                        .mapToObj(i -> Integer.toUnsignedString(i, 10))
                        .forEach(i -> log.debug("Affected: {} records", i));

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
