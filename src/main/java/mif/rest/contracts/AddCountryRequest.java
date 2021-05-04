package mif.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddCountryRequest {
    private String Name;
    private Integer Population;
}
