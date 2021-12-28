package agency.july.validjsondemo.models;

import agency.july.validjsondemo.deserializers.ItemDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonDeserialize(using = ItemDeserializer.class)
public class Items implements Serializable {

    private String name;
    private ItemType type;
    private Items[] items;
    private Task task;

    public Items(String name, ItemType type, Task task, Items[] items) {
        this.name = name;
        this.type = type;
        this.task = task;
        this.items = items;
    }
}
