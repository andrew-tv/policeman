package agency.july.validjsondemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.net.URL;

@Getter
@AllArgsConstructor
public enum ServiceSingleton {

    TARGET_JSON_RPC("http", "target-service", 8082, "/jsonrpc/v1"),
    TARGET_HEALTH_CHECK("http", "target-service", 5001, "/hc"),
    TARGET_NO_AUTH("http", "target-service", 8082, "/noauth/user"),
    MOUNTEBANK("http", "mountebank", 2525, "/imposters"),
    SCHEMA_REGISTRY("http", "schema-registry", 8081, ""),
    BROKER("", "broker", 9092, "") {

        @Override
        public String getUrlString() {
            return String.format("%s:%s", this.getHost(), this.getPort());
        }

    };

    private String protocol;
    private String host;
    private int port;
    private String path;

    public String getHost() {
        String isLocalVar = System.getenv("IS_LOCAL");
        boolean isLocal = isLocalVar!=null && isLocalVar.equals("true");
        return isLocal?"localhost":host;
    }

    @SneakyThrows
    public URL getURL() {
        return new URL(protocol, getHost(), port, path);
    }

    public String getUrlString() {
        return getURL().toString();
    }

}
