package mif.services;

import mif.interfaces.PresidentElectionService;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequestScoped
public class FairElectionService implements PresidentElectionService {
    @Override
    public String electPresident(List<String> candidates) {
        Integer candidateNumber = candidates.size();
        Integer winnerIndex = ThreadLocalRandom.current().nextInt(1, candidateNumber + 1);
        return candidates.get(winnerIndex-1);
    }
}
