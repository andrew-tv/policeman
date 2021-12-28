package agency.july.validjsondemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Rest implements Task, Serializable {

    private Request request;

    @Override
    @JsonIgnore
    public void doIt() {
    }
}
