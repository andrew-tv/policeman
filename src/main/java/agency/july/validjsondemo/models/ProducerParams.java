package agency.july.validjsondemo.models;

import agency.july.validjsondemo.config.ServiceUrls;
import agency.july.validjsondemo.enums.ServiceSingleton;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.URL;

@Getter
@Setter
@ToString
public class ProducerParams {

    private String client;
    private String bootstrap;
    private ServiceSingleton service;
//    private URL service = ServiceUrls.getURL("schema-registry");

    private String topic;
    private JsonNode schema;

}