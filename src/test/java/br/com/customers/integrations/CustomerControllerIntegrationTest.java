package br.com.customers.integrations;

import br.com.customers.controllers.parameters.CustomerParameter;
import br.com.customers.models.Customer;
import br.com.customers.repositories.CustomerRepository;
import br.com.customers.utils.CustomerEntityCreator;
import br.com.customers.utils.CustomerParameterCreator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void getAllCustomers() {
        Customer savedCustomer = customerRepository.save(CustomerEntityCreator.createCustomer()).toModel();
        String expectedName = savedCustomer.getName();

        List<Customer> customers = testRestTemplate.exchange("/v1/customers", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Customer>>() {}).getBody();

        Assertions.assertThat(customers).isNotNull().isNotEmpty().hasSize(2);
        Assertions.assertThat(customers.get(1).getName()).isEqualTo(expectedName);
    }

    @Test
    void getCustomerByDocument() {
        Customer savedCustomer = customerRepository.save(CustomerEntityCreator.createCustomer()).toModel();
        String expectedDocument = savedCustomer.getDocument();

        Customer customer = testRestTemplate.getForObject("/v1/customers/{document}", Customer.class, expectedDocument);

        Assertions.assertThat(customer).isNotNull();
        Assertions.assertThat(customer.getDocument()).isNotEmpty().isEqualTo(expectedDocument);
    }

    @Test
    void createCustomer() {
        CustomerParameter customerParameterCreator = CustomerParameterCreator.createCustomer();

        ResponseEntity<Customer> animeResponseEntity = testRestTemplate.postForEntity("/v1/customers", customerParameterCreator, Customer.class);

        Assertions.assertThat(animeResponseEntity).isNotNull();
        Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(animeResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(animeResponseEntity.getBody().getDocument()).isNotNull();
    }

    @Test
    void updateCustomerByDocument() {
        Customer savedCustomer = customerRepository.save(CustomerEntityCreator.createCustomer()).toModel();

        savedCustomer.setName("Nicole E. S. Nogueira");

        ResponseEntity<Void> animeResponseEntity = testRestTemplate.exchange("/v1/customers",
                HttpMethod.PUT,new HttpEntity<>(savedCustomer), Void.class);

        Assertions.assertThat(animeResponseEntity).isNotNull();
        Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}