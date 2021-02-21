package br.com.customers.controllers.parameters;

import br.com.customers.models.Address;
import br.com.customers.models.Customer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CustomerParameter implements Serializable {
	private String name;
	private String document;
	private List<AddressParameter> address;

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

	public List<AddressParameter> getAddress() {
		return address;
	}

	public void setAddress(List<AddressParameter> address) {
		this.address = address;
	}

	public Customer toModel() {
		Customer customer = new Customer();
		List<Address> addressList = new ArrayList<>();
		address.forEach(addressParameter -> addressList.add(addressParameter.toModel()));

		customer.setName(this.name);
		customer.setDocument(this.document);
		customer.setAddress(addressList);

		return customer;
	}

}
