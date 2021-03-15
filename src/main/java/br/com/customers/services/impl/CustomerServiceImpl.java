package br.com.customers.services.impl;

import br.com.customers.entities.AddressEntity;
import br.com.customers.entities.CustomerEntity;
import br.com.customers.models.Address;
import br.com.customers.models.Customer;
import br.com.customers.repositories.CustomerRepository;
import br.com.customers.services.CustomerService;
import br.com.customers.utils.CpfCnpjValidation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		List<CustomerEntity> customerEntityList = this.customerRepository.findAll();

		return !CollectionUtils.isEmpty(customerEntityList) ? customerEntityList.stream().map(CustomerEntity::toModel).collect(Collectors.toList()) : null;
	}
	
	@Override
	public Customer getCustomerByDocument(String document) {
		if(document != null) {
			CustomerEntity customerEntity = this.customerRepository.findByDocument(CpfCnpjValidation.removeDots(document));
			return customerEntity.toModel();
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public Customer insert(Customer customer) {
		if (customer != null && CpfCnpjValidation.isValid(customer.getDocument())) {
			CustomerEntity customerEntity = new CustomerEntity();
			setCustomer(customerEntity, customer);

			return this.customerRepository.saveAndFlush(customerEntity).toModel();
		}
		return null;
	}

	@Override
	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public Customer updateCustomerByDocument(Customer customer) {
		if (customer != null && CpfCnpjValidation.isValid(customer.getDocument())){

			CustomerEntity customerEntity = getCustomerEntityByDocument(CpfCnpjValidation.removeDots(customer.getDocument()));
			customer.setId(customerEntity.getId());
			setCustomer(customerEntity, customer);

			return this.customerRepository.save(customerEntity).toModel();
		}
		return null;
	}

	@Override
	public boolean deletetCustomerByDocument(String document) {
		if(document != null) {
			try {
				CustomerEntity customerEntity = this.customerRepository.findByDocument(CpfCnpjValidation.removeDots(document));
				customerRepository.delete(customerEntity);
				return true;
			}catch (Exception e){
				return false;
			}
		}
		return false;
	}

	private void setCustomer(CustomerEntity customerEntity, Customer customer) {
		if (customer != null){
			customerEntity.setName(customer.getName());
			customerEntity.setDocument(CpfCnpjValidation.removeDots(customer.getDocument()));
			setAddress(customerEntity, customer.getAddress());
		}
	}

	private void setAddress(CustomerEntity customerEntity, List<Address> addressList) {
		List<AddressEntity> addressEntities = new ArrayList<>();
		if (!addressList.isEmpty()){

			for (Address address : addressList){
				AddressEntity addressEntity = new AddressEntity();
				addressEntity.setStreet(address.getStreet());
				addressEntity.setNumber(address.getNumber());
				addressEntity.setNeighborhood(address.getNeighborhood());
				addressEntity.setCity(address.getCity());
				addressEntity.setProvince(address.getProvince());
				addressEntity.setCountry(address.getCountry());
				addressEntity.setPostCode(address.getPostCode());
				addressEntity.setCustomer(customerEntity);

				addressEntities.add(addressEntity);
			}

			customerEntity.setAddress(addressEntities);
		}
	}
	public CustomerEntity getCustomerEntityByDocument(String document) {
		return !document.isEmpty() ? this.customerRepository.findByDocument(document): null;
	}
}
