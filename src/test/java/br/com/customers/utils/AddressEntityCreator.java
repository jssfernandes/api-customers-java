package br.com.customers.utils;

import br.com.customers.entities.AddressEntity;

public class AddressEntityCreator {
    public static AddressEntity createAddress() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("Rua Alexandre Bonadiman");
        addressEntity.setNumber("950");
        addressEntity.setNeighborhood("Santa Barbara");
        addressEntity.setCity("Cariacica");
        addressEntity.setProvince("ES");
        addressEntity.setCountry("Brasil");
        addressEntity.setPostCode("29145-050");

        return addressEntity;
    }
}
