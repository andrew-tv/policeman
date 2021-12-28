package agency.july.validjsondemo.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Header implements Serializable {
    private String key;
    private String value;
}
