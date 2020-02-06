package restclient.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)

public class Component {
    private  Long id;
    private  String name;
    private  Type type;


    public static enum Type {
        DRINKS, SNACK, FRUIT
    }


}