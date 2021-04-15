package mif.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CountryDto {
    private String Name;
    private Integer Population;
    private String CapitalCity;
}
