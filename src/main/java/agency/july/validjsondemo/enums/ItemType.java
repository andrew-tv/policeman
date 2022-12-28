package agency.july.validjsondemo.enums;

import agency.july.validjsondemo.tasks.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum ItemType {
    NODE,
    REST {
        @Override
        public Task getTaskInstance(JsonNode task) throws JsonProcessingException {
            System.out.println(task);
            return mapper.treeToValue(task, Rest.class);
        }
    },
    PRODUCER {
        @Override
        public Task getTaskInstance(JsonNode task) throws JsonProcessingException {
            System.out.println(task);
            return mapper.treeToValue(task, Producer.class);
        }
    },
    SQL_QUERY {
        @Override
        public Task getTaskInstance(JsonNode task) throws JsonProcessingException {
            System.out.println(task);
            return mapper.treeToValue(task, SqlQuery.class);
        }
    },
    SQL_SCRIPT {
        @Override
        public Task getTaskInstance(JsonNode task) throws JsonProcessingException {
            System.out.println(task);
            return mapper.treeToValue(task, SqlScript.class);
        }
    };

    private static ObjectMapper mapper = new ObjectMapper();

    public Task getTaskInstance(JsonNode task) throws JsonProcessingException {
        return null;
    }

}
