package agency.july.validjsondemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public final class ServiceUrls {

    private static ServiceUrls INSTANCE;
    private static Map<String, Object> urls;

    private ServiceUrls(File jsonConfig) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        urls = objectMapper.readValue(jsonConfig, Map.class);
        System.out.println("111: " + urls);
    }

    public static ServiceUrls getInstance(File jsonConfig) throws IOException {
        if(INSTANCE == null) {
            INSTANCE = new ServiceUrls(jsonConfig);
        }
        return INSTANCE;
    }

    public static void init(File jsonConfig) throws IOException {
        if(INSTANCE == null) {
            INSTANCE = new ServiceUrls(jsonConfig);
        }
    }

    @SneakyThrows
    public static URL getURL(String urlKey) {
        Map<String, Object> url = (Map)urls.get(urlKey);
        return new URL((String)url.get("protocol"), (String)url.get("host"), (Integer)url.get("port"), (String)url.get("path"));
    }

    public String getUrlString(String urlKey) {
        return getURL(urlKey).toString();
    }
}
