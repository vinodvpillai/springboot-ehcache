package com.vinod.ehcache.controller;

import com.vinod.ehcache.model.Customer;
import com.vinod.ehcache.service.ICustomerService;
import com.vinod.ehcache.util.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.vinod.ehcache.util.GlobalUtility.buildResponseForError;
import static com.vinod.ehcache.util.GlobalUtility.buildResponseForSuccess;


@RestController
@RequestMapping("/customer")
@Log4j2
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<Response> addNewCustomer(@RequestBody Customer customer) {
        log.trace("Request came to add new customer with following details: {}", customer);
        Customer persistedCustomer=customerService.addCustomer(customer);
        if(null!=persistedCustomer) {
            return buildResponseForSuccess(HttpStatus.SC_OK,"Successfully added new customer",persistedCustomer);
        }
        return buildResponseForError(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR),"Unable to add the customer.",null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCustomer(@PathVariable("id") Long id) {
        log.trace("Request came to get the customer details having the id: {}", id);
        Customer customer=customerService.getCustomerById(id);
        if(null!=customer) {
            return buildResponseForSuccess(HttpStatus.SC_OK,"Successfully fetched customer",customer);
        }
        return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_BAD_REQUEST),"No customer detail found for the given id.",null);
    }

    @GetMapping
    public ResponseEntity<Response> getCustomerMyEmailId(@RequestParam("emailId") String emailId) {
        log.trace("Request came to get the customer details having the email id: {}", emailId);
        Customer customer=customerService.getCustomerByEmailId(emailId);
        if(null!=customer) {
            return buildResponseForSuccess(HttpStatus.SC_OK,"Successfully fetched customer",customer);
        }
        return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_BAD_REQUEST),"No customer detail found for the given email id.",null);
    }

    @DeleteMapping("/cache")
    public ResponseEntity<Response> clearCustomerCache() {
        log.trace("Request came to clear the customer cache");
        customerService.clearCache();
        return buildResponseForSuccess(HttpStatus.SC_OK,"Successfully cleared customer cache",null);
    }
}
