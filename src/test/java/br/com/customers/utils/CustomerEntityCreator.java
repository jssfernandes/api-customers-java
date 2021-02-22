package br.com.customers.utils;

import br.com.customers.entities.AddressEntity;
import br.com.customers.entities.CustomerEntity;
import java.util.ArrayList;
import java.util.List;

public class CustomerEntityCreator {
    public static CustomerEntity createCustomer() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("Nicole Esther Sarah Nogueira");
        customerEntity.setDocument("07707303881");
        setAddress(customerEntity);

        return customerEntity;
    }

    private static void setAddress(CustomerEntity customerEntity) {
        List<AddressEntity> addressEntities = new ArrayList<>();

        addressEntities.add(AddressEntityCreator.createAddress());

        customerEntity.setAddress(addressEntities);
    }
}
