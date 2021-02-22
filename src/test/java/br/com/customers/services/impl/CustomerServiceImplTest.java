package br.com.customers.services.impl;

import br.com.customers.entities.CustomerEntity;
import br.com.customers.models.Customer;
import br.com.customers.repositories.CustomerRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;
    @Mock
    private CustomerRepository customerRepository;
    @BeforeEach
    public void setup(){
        List<CustomerEntity> customerEntities = new ArrayList<>();
        customerEntities.add(CustomerEntityCreator.createCustomer());
        BDDMockito.when(customerRepository.findAll()).thenReturn(customerEntities);

        BDDMockito.when(customerRepository.findByDocument(ArgumentMatchers.anyString()))
                .thenReturn(CustomerEntityCreator.createCustomer());

        BDDMockito.when(customerRepository.saveAndFlush(ArgumentMatchers.any(CustomerEntity.class)))
                .thenReturn(CustomerEntityCreator.createCustomer());

        BDDMockito.when(customerRepository.save(ArgumentMatchers.any(CustomerEntity.class)))
                .thenReturn(CustomerEntityCreator.createCustomer());
    }

    @Test
    void getAllCustomers() {
        String expectedName = CustomerEntityCreator.createCustomer().getName();

        List<Customer> customers = customerServiceImpl.getAllCustomers();

        Assertions.assertThat(customers).isNotNull().hasSize(1);
        Assertions.assertThat(Objects.requireNonNull(customers).get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void getCustomerByDocument() {
        String expectedDocument = CustomerEntityCreator.createCustomer().getDocument();

        Customer customer = customerServiceImpl.getCustomerByDocument("07707303881");

        Assertions.assertThat(customer).isNotNull();
        Assertions.assertThat(customer.getDocument()).isEqualTo(expectedDocument);
    }

    @Test
    void insert() {
        Customer customer = customerServiceImpl.insert(CustomerParameterCreator.createCustomer().toModel());

        Assertions.assertThat(customer).isNotNull().isEqualTo(customer);
    }

    @Test
    void updateCustomerByDocument() {
        Customer entity = customerServiceImpl.updateCustomerByDocument(CustomerParameterCreator.createCustomer().toModel());

        Assertions.assertThat(entity).isNotNull();
    }

    @Test
    void getCustomerEntityByDocument() {
        String expectedDocument = CustomerEntityCreator.createCustomer().getDocument();

        Customer customer = customerServiceImpl.getCustomerByDocument("07707303881");

        Assertions.assertThat(customer).isNotNull();
        Assertions.assertThat(customer.getDocument()).isEqualTo(expectedDocument);
    }
}