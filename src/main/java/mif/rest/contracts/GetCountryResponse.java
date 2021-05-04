package mif.rest.contracts;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Getter @Setter
public class GetCountryResponse {
    private String Name;
    private Integer Population;
    private String CapitalCity;
    private List<String> Cities;
    private List<String> Neighbours;
}
