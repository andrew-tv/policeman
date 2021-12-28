package agency.july.validjsondemo.deserializers;

import agency.july.validjsondemo.models.ItemType;
import agency.july.validjsondemo.models.Items;
import agency.july.validjsondemo.models.Rest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ItemDeserializer extends StdDeserializer<Items> {

    public ItemDeserializer() {
        this(null);
    }

    public ItemDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Items deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        ItemType type = ItemType.valueOf(node.get("type").asText());

        System.out.println("items: " + node);
        System.out.println("items type: " + node.get("type"));
        System.out.println("items items: " + node.get("items"));
        System.out.println("items task: " + node.get("task"));

        if (node.get("items") != null) {
            ObjectMapper mapper = new ObjectMapper();
            Items[] subItems = mapper.treeToValue(node.get("items"), Items[].class);
            return new Items(name, type, null, subItems);
        } else {
            return new Items(name, type, type.getTaskInstance(node.get("task")), null);
        }
    }
}
