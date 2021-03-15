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
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;


@RestController
@Api(value = "Customers", tags = { "Customers" })
@RequestMapping("/v1")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerPresenter>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers != null) {
            return new ResponseEntity<>(customers.stream().map(CustomerPresenter::new).collect(toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/customers/{document}")
    public ResponseEntity<CustomerPresenter> getCustomerByDocument(@PathVariable String document) {
    	Customer customer = customerService.getCustomerByDocument(document);
    	
    	if (customer != null) {
            return new ResponseEntity<>(new CustomerPresenter(customer), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/customers")
    public ResponseEntity<CustomerPresenter> createCustomer(@RequestBody CustomerParameter parameter) {

    	Customer customer = this.customerService.insert(parameter.toModel());

        if (customer != null) {
            return new ResponseEntity<>( new CustomerPresenter(customer),HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/customers")
    public ResponseEntity<CustomerPresenter> updateCustomerByDocument(@RequestBody CustomerParameter parameter) {

        Customer customer = this.customerService.updateCustomerByDocument(parameter.toModel());

        if (customer != null) {
            return new ResponseEntity<>( new CustomerPresenter(customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/customers/{document}")
    public ResponseEntity deletetCustomerByDocument(@PathVariable String document) {
        boolean customer = customerService.deletetCustomerByDocument(document);

        if (customer) {
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
