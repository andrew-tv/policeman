package agency.july.validjsondemo.tasks;

import agency.july.validjsondemo.deserializers.ItemDeserializer;
import agency.july.validjsondemo.enums.ItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(using = ItemDeserializer.class)
@Slf4j
public class Item implements Task {

    private String name;
    private String description;
    private ItemType type;
    private Item[] items;
    private Task task;
    private Boolean skip;

    @Override
    @JsonIgnore
    public void doIt() {

        log.info(String.format("Task: %s >> Type: %s >> skip:%b", name, type, skip));
        log.info(String.format("Description: %s", description));

        if (!skip) {
            if (type == ItemType.NODE) {
                for (int i = 0; i < items.length; i++) {
                    items[i].doIt();
                }
            } else {
                task.doIt();
            }
        } else {

        }

    }
}
