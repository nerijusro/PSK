/*
package converters;

import entities.City;
import persistence.CitiesDAO;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CityConverter implements Converter {

    @Inject
    private CitiesDAO citiesService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            int parameter = Integer.valueOf(submittedValue);
            City result = citiesService.findOne(parameter);
            return result;
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Warehouse ID"), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return ""; // Never return null here!
        }

        if (modelValue instanceof City) {
            return String.valueOf(((City) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " is not a valid City"));
        }
    }
}
*/
