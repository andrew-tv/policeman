package agency.july.validjsondemo.deserializers;

import agency.july.validjsondemo.enums.ItemType;
import agency.july.validjsondemo.tasks.Item;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ItemDeserializer extends StdDeserializer<Item> {

    public ItemDeserializer() {
        this(null);
    }

    public ItemDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Item deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        System.out.println("node: " + node);

        String name = node.get("name").asText();
        String description = node.get("description").asText();
        ItemType type = ItemType.valueOf(node.get("type").asText());
        Boolean skip = node.get("skip")==null?false:node.get("skip").asBoolean();

        if (node.get("items") != null) {
            ObjectMapper mapper = new ObjectMapper();
            Item[] subItems = mapper.treeToValue(node.get("items"), Item[].class);
            return new Item(name, description, type, subItems, null, skip);
        } else {
            return new Item(name, description, type, null, type.getTaskInstance(node.get("task")), skip);
        }
    }
}
