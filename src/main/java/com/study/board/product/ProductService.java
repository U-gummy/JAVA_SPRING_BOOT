package com.study.board.product;

import com.study.board.category.Category;
import com.study.board.category.CategoryNotFoundException;
import com.study.board.category.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponse> list() {
        return productRepository.findAllWithCategory().stream()
                .map(ProductResponse::from)
                .toList();
    }

    public ProductResponse detail(Long id) {
        Product product = productRepository.findByIdWithCategory(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductResponse.from(product);
    }

    @Transactional
    public ProductResponse create(ProductCreateRequest request) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(request.categoryId()));

        Product product = new Product(request.name(), request.productCode(), category);

        Product saved = productRepository.save(product);

        if (request.name().equals("롤백테스트")) {
            throw new IllegalStateException("테스트용 예외");   // 실험용. 나중에 지움
        }

        return ProductResponse.from(saved);
    }


    @Transactional
    public ProductResponse update(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(request.categoryId()));

        product.update(request.name(), request.productCode(), category);

        return ProductResponse.from(product);
    }

    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);
    }

}
