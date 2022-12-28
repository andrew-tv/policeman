package agency.july.validjsondemo.enums;

import java.util.HashMap;

public enum Storage {
    STORAGE();

    private HashMap<String, Object> storage;

    Storage() {
        this.storage = new HashMap<>();
    }

    public void put(String key, Object value) {
        this.storage.put(key, value);
    }

    public Object extract(String key) {
        return storage.get(key);
    }


}
