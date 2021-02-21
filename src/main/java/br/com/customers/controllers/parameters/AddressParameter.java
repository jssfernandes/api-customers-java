package br.com.customers.controllers.parameters;

import br.com.customers.models.Address;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;


public class AddressParameter implements Serializable {
    @ApiModelProperty(value = "Street name of the customer", example = "Via Una", position = 1)
    private String street;
    @ApiModelProperty(value = "Number of the residence of the customer", example = "420", position = 2)
    private String number;
    @ApiModelProperty(value = "Neighborhood name of the customer", example = "Tarumã-Açu", position = 3)
    private String neighborhood;
    @ApiModelProperty(value = "City name of the customer", example = "Manaus", position = 4)
    private String city;
    @ApiModelProperty(value = "Province name of the customer", example = "AM", position = 5)
    private String province;
    @ApiModelProperty(value = "Country name of the customer", example = "Brasil", position = 6)
    private String country;
    @ApiModelProperty(value = "Post code number of the customer", example = "69022-560", position = 7)
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
