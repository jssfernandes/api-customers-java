package br.com.customers.services;

import br.com.customers.models.Customer;
import java.util.List;


public interface CustomerService {
	public List<Customer> getAllCustomers();

	public Customer getCustomerByDocument(String document);

	public Customer insert(Customer customer);

	public Customer updateCustomerByDocument(Customer customer);

	public boolean deletetCustomerByDocument(String document);
}
