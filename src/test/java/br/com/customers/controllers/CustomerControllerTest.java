package br.com.customers.controllers;

import br.com.customers.models.Customer;
import br.com.customers.presenters.CustomerPresenter;
import br.com.customers.services.CustomerService;
import br.com.customers.utils.CustomerEntityCreator;
import br.com.customers.utils.CustomerParameterCreator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;
    @BeforeEach
    public void setup(){
        List<Customer> customers = new ArrayList<>();
        customers.add(CustomerEntityCreator.createCustomer().toModel());
        BDDMockito.when(customerService.getAllCustomers()).thenReturn(customers);

        BDDMockito.when(customerService.getCustomerByDocument(ArgumentMatchers.anyString()))
                .thenReturn(CustomerEntityCreator.createCustomer().toModel());

        BDDMockito.when(customerService.insert(ArgumentMatchers.any(Customer.class)))
                .thenReturn(CustomerParameterCreator.createCustomer().toModel());

        BDDMockito.when(customerService.updateCustomerByDocument(ArgumentMatchers.any(Customer.class)))
                .thenReturn(CustomerParameterCreator.createCustomer().toModel());
    }

    @Test
    void getAllCustomers() {
        String expectedName = CustomerEntityCreator.createCustomer().getName();

        List<CustomerPresenter> customers = customerController.getAllCustomers().getBody();

        Assertions.assertThat(customers).isNotNull().hasSize(1);
        Assertions.assertThat(Objects.requireNonNull(customers).get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void getCustomerByDocument() {
        String expectedDocument = CustomerEntityCreator.createCustomer().getDocument();

        CustomerPresenter customers = customerController.getCustomerByDocument("07707303881").getBody();

        Assertions.assertThat(customers).isNotNull();
        Assertions.assertThat(customers.getDocument()).isEqualTo(expectedDocument);
    }

    @Test
    void createCustomer() {
        CustomerPresenter customer = customerController.createCustomer(CustomerParameterCreator.createCustomer()).getBody();

        Assertions.assertThat(customer).isNotNull().isEqualTo(customer);
    }

    @Test
    void updateCustomerByDocument() {
        ResponseEntity<CustomerPresenter> entity = customerController.updateCustomerByDocument(CustomerParameterCreator.createCustomer());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}