package mif.rest.contracts;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ElectPresidentRequest {
    List<String> Candidates;
}
