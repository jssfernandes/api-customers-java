package br.com.customers.utils;

import br.com.customers.controllers.parameters.AddressParameter;

public class AddressParameterCreator {
    public static AddressParameter createAddress() {
        AddressParameter addressParameter = new AddressParameter();
        addressParameter.setStreet("Rua Alexandre Bonadiman");
        addressParameter.setNumber("950");
        addressParameter.setNeighborhood("Santa Barbara");
        addressParameter.setCity("Cariacica");
        addressParameter.setProvince("ES");
        addressParameter.setCountry("Brasil");
        addressParameter.setPostCode("29145-050");

        return addressParameter;
    }
}
