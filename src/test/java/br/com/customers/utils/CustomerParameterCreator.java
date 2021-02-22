package br.com.customers.utils;

import br.com.customers.controllers.parameters.AddressParameter;
import br.com.customers.controllers.parameters.CustomerParameter;
import java.util.ArrayList;
import java.util.List;

public class CustomerParameterCreator {
    public static CustomerParameter createCustomer() {
        CustomerParameter customerParameter = new CustomerParameter();
        customerParameter.setName("Nicole Esther Sarah Nogueira");
        customerParameter.setDocument("07707303881");
        setAddress(customerParameter);

        return customerParameter;
    }

    private static void setAddress(CustomerParameter customerParameter) {
        List<AddressParameter> addressParameters = new ArrayList<>();

        addressParameters.add(AddressParameterCreator.createAddress());

        customerParameter.setAddress(addressParameters);
    }
}
