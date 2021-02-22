package br.com.customers.presenters;

import br.com.customers.models.Address;


public class AddressPresenter {
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String province;
    private String country;
    private String postCode;

    public AddressPresenter(Address address) {
        if (address != null) {
            this.street = address.getStreet();
            this.number = address.getNumber();
            this.neighborhood = address.getNeighborhood();
            this.city = address.getCity();
            this.province = address.getProvince();
            this.country = address.getCountry();
            this.postCode = address.getPostCode();
        }
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }
}
