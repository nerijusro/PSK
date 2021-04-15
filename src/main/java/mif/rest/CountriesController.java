package mif.rest;

import lombok.Getter;
import lombok.Setter;
import mif.entities.Country;
import mif.persistence.CountriesDAO;
import mif.rest.contracts.CountryDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/countries")
public class CountriesController {
    @Inject
    @Setter @Getter
    private CountriesDAO countriesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Country country = countriesDAO.findOne(id);
        if (country == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CountryDto countryDto = new CountryDto();
        countryDto.setName(country.getName());
        if(country.getPopulation() != null){
            countryDto.setPopulation(country.getPopulation());
        }
        if(country.getCapitalCity() != null){
            countryDto.setCapitalCity(country.getCapitalCity().getName());
        }

        return Response.ok(countryDto).build();
    }

/*    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer playerId,
            PlayerDto playerData) {
        try {
            Player existingPlayer = playersDAO.findOne(playerId);
            if (existingPlayer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingPlayer.setName(playerData.getName());
            existingPlayer.setJerseyNumber(playerData.getJerseyNumber());
            playersDAO.update(existingPlayer);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }*/
}
