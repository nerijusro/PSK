package mif.services;

import mif.interfaces.PresidentElectionService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.enterprise.inject.Alternative;
import javax.faces.bean.RequestScoped;
import java.util.List;

@Alternative
@RequestScoped
public class RiggedElectionService implements PresidentElectionService {
    @Override
    public String electPresident(List<String> candidates) {
        return "Vladimir Putin";
    }
}
