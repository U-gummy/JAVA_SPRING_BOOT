package com.study.board.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> list() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse response = new ProductResponse(product.getId(), product.getName(), product.getProductCode());
            responses.add(response);
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> detail(@PathVariable Long id) {
        Optional<Product> result = productRepository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product product = result.get();

        ProductResponse response = new ProductResponse(product.getId(), product.getName(), product.getProductCode());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductCreateRequest request) {
        Product product = new Product(request.name(), request.productCode());
        Product saved = productRepository.save(product);

        ProductResponse response = new ProductResponse(saved.getId(), saved.getName(), saved.getProductCode());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
