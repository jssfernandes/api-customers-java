package br.com.customers.services;

import br.com.customers.models.Customer;
import java.util.List;


public interface CustomerService {
	public List<Customer> getAllCustomers();

	public Customer getCustomerById(String document);

	public Customer insert(Customer customer);

}
