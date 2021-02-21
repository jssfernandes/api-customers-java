package br.com.customers.repositories;

import br.com.customers.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long > {
    public CustomerEntity findByDocument(String document);
}
