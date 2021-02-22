package br.com.customers.entities;

import br.com.customers.models.Address;
import br.com.customers.models.Customer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @NotEmpty(message = "The document cannot be empty")
    @Column(unique = true)
    private String document;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressEntity> address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<AddressEntity> getAddress() {
        return address;
    }

    public void setAddress(List<AddressEntity> address) {
        this.address = address;
    }

    public Customer toModel() {
        Customer customer = new Customer();
        List<Address> addressList = new ArrayList<>();
        if (address != null && !address.isEmpty()) {
            address.forEach(addressEntity -> addressList.add(addressEntity.toModel()));
        }
        customer.setId(this.id);
        customer.setName(this.name);
        customer.setDocument(this.document);
        customer.setAddress(addressList);

        return customer;
    }
}
