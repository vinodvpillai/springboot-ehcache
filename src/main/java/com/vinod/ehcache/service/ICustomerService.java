package com.vinod.ehcache.service;


import com.vinod.ehcache.model.Customer;

public interface ICustomerService {

    /**
     * Add new customer.
     *
     * @param customer  - Customer object.
     * @return          - Persisted customer object.
     */
    Customer addCustomer(Customer customer);

    /**
     * Get customer object by customer id.
     *
     * @param id    - Customer ID.
     * @return      - Customer object.
     */
    Customer getCustomerById(Long id);

    /**
     * Get customer object by email id.
     *
     * @param emailId    - Customer Email ID.
     * @return      - Customer object.
     */
    Customer getCustomerByEmailId(String emailId);
}
