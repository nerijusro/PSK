package mif.rest;

import lombok.Getter;
import lombok.Setter;
import mif.entities.City;
import mif.entities.Country;
import mif.interfaces.PresidentElectionService;
import mif.persistence.CountriesDAO;
import mif.rest.contracts.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/map")
public class CountriesController {
    @Inject
    @Setter @Getter
    private CountriesDAO countriesDAO;

    @Inject
    private PresidentElectionService electionService;

    @Path("countries/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_XML)
    public Response getCountryById(@PathParam("id") final Integer id) {
        Country country = countriesDAO.findOne(id);
        if (country == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CountryResponseDto response = new CountryResponseDto();
        List<String> cities = new ArrayList<>();
        List<String> neighbours = new ArrayList<>();
        response.setName(country.getName());
        if(country.getPopulation() != null){
            response.setPopulation(country.getPopulation());
        }
        if(country.getCapitalCity() != null){
            response.setCapitalCity(country.getCapitalCity().getName());
        }
        if(country.getCities() != null){
            for (City city: country.getCities()) {
                cities.add(city.getName());
            }
        }
        if(country.getNeighbours() != null){
            for (Country neighbour: country.getNeighbours()) {
                neighbours.add(neighbour.getName());
            }
        }

        response.setCities(cities);
        response.setNeighbours(neighbours);

        return Response.ok(response).build();
    }

    @Path("/addCountry")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addCountry(CountryRequestDto request) {
        if(request.getName() == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Country newCountry = new Country(request.getName(), request.getPopulation());
        countriesDAO.persist(newCountry);

        AddCountryResponse response = new AddCountryResponse();
        response.setId(newCountry.getId());

        return Response.ok(response).build();
    }

    @Path("/updateCountry/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateCountry(@PathParam("id") final Integer id, CountryRequestDto request) {
        Country country = countriesDAO.findOne(id);
        if (country == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(request.getName() != null){
            country.setName(request.getName());
        }
        if(request.getPopulation() != null){
            country.setPopulation(request.getPopulation());
        }

        countriesDAO.merge(country);
        return Response.ok(country).build();
    }

    @Path("electPresident/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response electPresident(@PathParam("id") final Integer id, ElectPresidentRequest request) {
        Country country = countriesDAO.findOne(id);
        if (country == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        GenericResponse response = new GenericResponse();
        response.setMessage("The new president of "+ country.getName() + " is " + electionService.electPresident(request.getCandidates()));
        return Response.ok(response).build();
    }
}
