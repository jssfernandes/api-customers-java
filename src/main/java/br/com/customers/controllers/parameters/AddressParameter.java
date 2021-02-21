package br.com.customers.controllers.parameters;

import br.com.customers.models.Address;
import java.io.Serializable;


public class AddressParameter implements Serializable {
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String province;
    private String country;
    private String postCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Address toModel() {
        Address address = new Address();

        address.setStreet(this.street);
        address.setNumber(this.number);
        address.setNeighborhood(this.neighborhood);
        address.setCity(this.city);
        address.setProvince(this.province);
        address.setCountry(this.country);
        address.setPostCode(this.postCode);

        return address;
    }
}
