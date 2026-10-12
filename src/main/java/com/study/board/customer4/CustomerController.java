package com.study.board.customer4;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerCreateRequest request) {

        CustomerResponse response = customerService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> list() {
        List<CustomerResponse> responses = customerService.list();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> detail(@PathVariable Long id) {
        CustomerResponse response = customerService.detail(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @RequestBody CustomerUpdateRequest request) {
        CustomerResponse response = customerService.update(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
