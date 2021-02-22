package br.com.customers.repositories;

import br.com.customers.entities.CustomerEntity;
import br.com.customers.utils.CustomerEntityCreator;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@DisplayName("Tests for Customer Repository")
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Save persists customer when successful")
    public void save_PersistenceCustomer_WhenSuccessful(){
        CustomerEntity customerEntityToBeSaved = CustomerEntityCreator.createCustomer();

        CustomerEntity customerEntitySaved = this.customerRepository.save(customerEntityToBeSaved);

        Assertions.assertThat(customerEntitySaved.getId()).isNotZero();
        Assertions.assertThat(customerEntitySaved.getName()).isEqualTo("Nicole Esther Sarah Nogueira");
    }

    @Test
    @DisplayName("Save updates customer when successful")
    public void save_UpdateCustomer_WhenSuccessful(){
        CustomerEntity customerEntityToBeSaved = CustomerEntityCreator.createCustomer();
        CustomerEntity customerEntitySaved = this.customerRepository.save(customerEntityToBeSaved);

        customerEntitySaved.setName("Nicole Nogueira");

        CustomerEntity customerEntityUpdate = this.customerRepository.save(customerEntitySaved);
        Assertions.assertThat(customerEntityUpdate).isNotNull();
        Assertions.assertThat(customerEntityUpdate.getId()).isNotZero();
        Assertions.assertThat(customerEntityUpdate.getName()).isEqualTo("Nicole Nogueira");
    }

    @Test
    @DisplayName("Find all returns list of customer when successful")
    public void findAll_ReturnsListOfCustomer_WhenSuccessful(){
        CustomerEntity customerEntityToBeSaved = CustomerEntityCreator.createCustomer();
        this.customerRepository.save(customerEntityToBeSaved);

        List<CustomerEntity> customerEntities = this.customerRepository.findAll();

        Assertions.assertThat(customerEntities).contains(customerEntities.get(0));
    }

    @Test
    @DisplayName("Find by document returns customer when successful")
    public void findByDocument_ReturnsCustomer_WhenSuccessful(){
        CustomerEntity customerEntityToBeSaved = CustomerEntityCreator.createCustomer();
        CustomerEntity customerEntitySaved= this.customerRepository.save(customerEntityToBeSaved);;

        String document = customerEntitySaved.getDocument();

        CustomerEntity customerEntity = this.customerRepository.findByDocument(document);

        Assertions.assertThat(customerEntity).isNotNull();
        Assertions.assertThat(customerEntity.getName()).isEqualTo("Nicole Esther Sarah Nogueira");
    }

    @Test
    @DisplayName("Find By Id returns empty list when no customer is found")
    void findById_ReturnsEmptyList_WhenCustomerIsNotFound(){
        Optional<CustomerEntity> customerEntity = this.customerRepository.findById(42L);

        Assertions.assertThat(customerEntity).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when document is empty")
    void save_ThrowsConstraintViolationException_WhenDocumentIsEmpty(){
        CustomerEntity customerEntity = new CustomerEntity();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.customerRepository.save(customerEntity))
                .withMessageContaining("The document cannot be empty");
    }


}
