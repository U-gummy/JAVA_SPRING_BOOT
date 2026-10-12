package com.study.board.customer4;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public CustomerResponse create(CustomerCreateRequest request) {
        Customer customer = new Customer(request.name(), request.email());
        Customer saved = customerRepository.save(customer);

        return CustomerResponse.from(saved);
    }

    public List<CustomerResponse> list() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponse> responses = new ArrayList<>();

        for (Customer customer : customers) {
            responses.add(CustomerResponse.from(customer));
        }

        return responses;
    }

    public CustomerResponse detail(Long id) {
        Optional<Customer> result = customerRepository.findById(id);

        if (result.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }

        Customer customer = result.get();

        return CustomerResponse.from(customer);
    }

    @Transactional
    public CustomerResponse update(Long id, CustomerUpdateRequest request) {
        Optional<Customer> result = customerRepository.findById(id);

        if (result.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }

        Customer customer = result.get();
        customer.update(request.name(), request.email());

        return CustomerResponse.from(customer);
    }

    @Transactional
    public void delete(Long id) {
        Optional<Customer> result = customerRepository.findById(id);

        if (result.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }

        Customer customer = result.get();

        customerRepository.delete(customer);
    }

}
