package br.com.customers.controllers;

import br.com.customers.controllers.parameters.CustomerParameter;
import br.com.customers.models.Customer;
import br.com.customers.presenters.CustomerPresenter;
import br.com.customers.services.CustomerService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static java.util.stream.Collectors.toList;


@RestController
@Api(value = "Customers", tags = { "Customers" })
@RequestMapping("/v1")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<CustomerPresenter> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers != null) {
            return new ResponseEntity(customers.stream().map(c -> new CustomerPresenter(c)).collect(toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/customers/{document}")
    public ResponseEntity<CustomerPresenter> getCustomerById(@PathVariable String document) {
    	Customer customer = customerService.getCustomerById(document);
    	
    	if (customer != null) {
            return new ResponseEntity(new CustomerPresenter(customer), HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    
    @PostMapping("/customers")
    public ResponseEntity<CustomerPresenter> create(@RequestBody CustomerParameter parameter) {

    	Customer customer = this.customerService.insert(parameter.toModel());

        if (customer != null) {
            return new ResponseEntity( new CustomerPresenter(customer),HttpStatus.CREATED);
        } else if (customer == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
