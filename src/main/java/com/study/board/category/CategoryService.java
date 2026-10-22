package com.study.board.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse create(CategoryCreateRequest request) {
        Category category = new Category(request.name());
        Category saved = categoryRepository.save(category);
        return CategoryResponse.from(saved);
    }

    public List<CategoryResponse> list() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponse::from)
                .toList();
    }

    public CategoryResponse detail(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        return CategoryResponse.from(category);
    }


}
