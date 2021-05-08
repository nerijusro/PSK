package mif.decorators;

import javax.enterprise.inject.Default;

@Default
public class UnitedNations implements InternationalOrganization {
    @Override
    public String getDescription(){ return "United Nations"; }
}
