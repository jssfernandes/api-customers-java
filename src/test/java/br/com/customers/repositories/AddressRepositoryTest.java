package br.com.customers.repositories;

import br.com.customers.entities.AddressEntity;
import br.com.customers.utils.AddressEntityCreator;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for Address Repository")
class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("Save persists address when successful")
    public void save_PersistenceAddress_WhenSuccessful(){
        AddressEntity addressEntityToBeSaved = AddressEntityCreator.createAddress();

        AddressEntity addressEntitySaved = this.addressRepository.save(addressEntityToBeSaved);

        Assertions.assertThat(addressEntitySaved.getId()).isNotZero();
        Assertions.assertThat(addressEntitySaved.getCity()).isEqualTo("Cariacica");
    }

    @Test
    @DisplayName("Save updates address when successful")
    public void save_UpdateAddress_WhenSuccessful(){
        AddressEntity addressEntityToBeSaved = AddressEntityCreator.createAddress();
        AddressEntity addressEntitySaved = this.addressRepository.save(addressEntityToBeSaved);

        addressEntitySaved.setStreet("R. Alexandre Bonadiman");

        AddressEntity addressEntityUpdate = this.addressRepository.save(addressEntitySaved);
        Assertions.assertThat(addressEntityUpdate).isNotNull();
        Assertions.assertThat(addressEntityUpdate.getId()).isNotZero();
        Assertions.assertThat(addressEntityUpdate.getStreet()).isEqualTo("R. Alexandre Bonadiman");
    }

    @Test
    @DisplayName("Find all returns list of address when successful")
    public void findAll_ReturnsListOfAddress_WhenSuccessful(){
        AddressEntity addressEntityToBeSaved = AddressEntityCreator.createAddress();
        this.addressRepository.save(addressEntityToBeSaved);

        List<AddressEntity> addressEntities = this.addressRepository.findAll();

        Assertions.assertThat(addressEntities).isNotEmpty().contains(addressEntities.get(0));
    }

    @Test
    @DisplayName("Find by id returns address when successful")
    public void findById_ReturnsAddress_WhenSuccessful(){
        AddressEntity addressEntityToBeSaved = AddressEntityCreator.createAddress();
        AddressEntity addressEntitySaved= this.addressRepository.save(addressEntityToBeSaved);;

        long id = addressEntitySaved.getId();

        Optional<AddressEntity> addressEntity = this.addressRepository.findById(id);

        Assertions.assertThat(addressEntity).isNotEmpty();
        Assertions.assertThat(addressEntity.get().getStreet()).isEqualTo("Rua Alexandre Bonadiman");
    }

    @Test
    @DisplayName("Find By Id returns empty list when no address is found")
    void findById_ReturnsEmptyList_WhenAddressIsNotFound(){
        Optional<AddressEntity> addressEntity = this.addressRepository.findById(42L);

        Assertions.assertThat(addressEntity).isEmpty();
    }

    
}