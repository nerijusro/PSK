package mif.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class EuropeanUnion implements InternationalOrganization {

    @Inject @Delegate @Any
    private UnitedNations unitedNations;

    @Override
    public String getDescription(){ return unitedNations.getDescription() + ", European Union"; }
}
