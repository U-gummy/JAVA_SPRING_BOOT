package com.study.board.customer4;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return customerRepository.findAll().stream()
                .map(CustomerResponse::from)
                .toList();
    }

    public CustomerResponse detail(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));


        return CustomerResponse.from(customer);
    }

    @Transactional
    public CustomerResponse update(Long id, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customer.update(request.name(), request.email());

        return CustomerResponse.from(customer);
    }

    @Transactional
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customerRepository.delete(customer);
    }

}
