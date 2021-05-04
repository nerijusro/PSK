package mif.rest.contracts;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter @Setter
public class AddCountryResponse {
    private Integer Id;
}
