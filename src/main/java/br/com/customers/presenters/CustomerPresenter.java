package br.com.customers.presenters;

import br.com.customers.models.Customer;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;


public class CustomerPresenter {
	private String name;
	private String document;
	private List<AddressPresenter> address;

    public CustomerPresenter(Customer customer) {
    	if (customer != null) {
			this.name = customer.getName();
			this.document = customer.getDocument();
			this.address = !CollectionUtils.isEmpty(customer.getAddress())
					? customer.getAddress().stream().map(AddressPresenter::new).collect(Collectors.toList()) : null;
		}
    }

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}

	public List<AddressPresenter> getAddress() {
		return address;
	}
}
