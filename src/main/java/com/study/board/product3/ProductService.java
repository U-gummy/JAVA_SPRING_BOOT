package com.study.board.product3;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> list() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse response = ProductResponse.from(product);
            responses.add(response);
        }
        return responses;
    }

    public ProductResponse detail(Long id) {
        Optional<Product> result = productRepository.findById(id);

        if (result.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        Product product = result.get();

        return ProductResponse.from(product);
    }

    public ProductResponse create(ProductCreateRequest request) {
        Product product = new Product(request.name(), request.productCode());

        Product saved = productRepository.save(product);

        return ProductResponse.from(saved);
    }


    @Transactional
    public ProductResponse update(Long id, ProductUpdateRequest request) {
        Optional<Product> result = productRepository.findById(id);

        if (result.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        Product product = result.get();
        product.update(request.name(), request.productCode());

        return ProductResponse.from(product);

    }

    @Transactional
    public void delete(Long id) {
        Optional<Product> result = productRepository.findById(id);

        if (result.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        Product product = result.get();

        productRepository.delete(product);
    }

}
