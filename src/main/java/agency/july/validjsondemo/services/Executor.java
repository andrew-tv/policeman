package agency.july.validjsondemo.services;

import agency.july.validjsondemo.enums.ItemType;
import agency.july.validjsondemo.tasks.Item;

import java.net.MalformedURLException;

public class Executor {

    public static void execute(Item item) throws MalformedURLException {
        if (item.getType() == ItemType.NODE) {
            for (int i=0; i<item.getItems().length; i++) {
                execute(item.getItems()[i]);
            }
        } else {
            item.getTask().doIt();
        }
    }
}
