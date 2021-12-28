package agency.july.validjsondemo.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum ItemType {
    NODE,
    REST {
        @Override
        public Task getTaskInstance(JsonNode task) throws JsonProcessingException {
            return mapper.treeToValue(task, Rest.class);
        }
    };

    private static ObjectMapper mapper = new ObjectMapper();

    public Task getTaskInstance(JsonNode task) throws JsonProcessingException {
        return null;
    }
}
