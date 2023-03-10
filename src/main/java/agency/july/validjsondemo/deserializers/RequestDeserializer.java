package agency.july.validjsondemo.deserializers;

import agency.july.validjsondemo.enums.HttpMethod;
import agency.july.validjsondemo.models.Request;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URL;

public class RequestDeserializer extends StdDeserializer<Request> {

    public RequestDeserializer() {
        this(null);
    }

    public RequestDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Request deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = jp.getCodec().readTree(jp);

        System.out.println("Request node: " + node);
        System.out.println("method: " + node.get("method"));

        HttpMethod method = HttpMethod.valueOf(node.get("method").asText());
        URL url = new URL (
            node.get("url").get("protocol").asText(),
            node.get("url").get("host").asText(),
            node.get("url").get("port").asInt(),
            node.get("url").get("path").asText()
        );

        HttpHeaders headers = mapper.treeToValue(node.get("headers"), HttpHeaders.class);

        return null; //new Request(method, url, headers, node.get("body"));

    }

}
