package mif.usecases;

import mif.interceptors.LoggedInvocation;
import mif.services.PopulationService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class FetchCountrysPopulation implements Serializable {
    @Inject
    private PopulationService populationService;

    private CompletableFuture<Integer> populationFetchingTask = null;

    @LoggedInvocation
    public String fetchPopulation() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        populationFetchingTask = CompletableFuture.supplyAsync(() -> populationService.fetchCountrysPopulation());

        return  "/updatePopulation.xhtml?faces-redirect=true&countryId=" + requestParameters.get("countryId");
    }

    public boolean isPopulationFetchingRunning() {
        return populationFetchingTask != null && !populationFetchingTask.isDone();
    }

    public String getPopulationFetchingStatus() throws ExecutionException, InterruptedException {
        if (populationFetchingTask == null) {
            return null;
        } else if (isPopulationFetchingRunning()) {
            return "Population fetching in progress";
        }
        return "Estimated population: " + populationFetchingTask.get();
    }
}
