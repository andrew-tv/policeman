package agency.july.validjsondemo.models;

import agency.july.validjsondemo.deserializers.RequestDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.net.URL;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(using = RequestDeserializer.class)
public class Request implements Serializable {

    private String method;
    private URL url;
    private Header[] headers;
    private JsonNode body;

}
