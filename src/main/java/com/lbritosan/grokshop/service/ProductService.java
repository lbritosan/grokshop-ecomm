package com.lbritosan.grokshop.service;

import com.lbritosan.grokshop.dto.ProductDTO;
import com.lbritosan.grokshop.entity.Category;
import com.lbritosan.grokshop.entity.Product;
import com.lbritosan.grokshop.exception.ResourceNotFoundException;
import com.lbritosan.grokshop.repository.CategoryRepository;
import com.lbritosan.grokshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Product createProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        Set<Category> categories = dto.getCategoryIds().stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id)))
                .collect(Collectors.toSet());
        product.setCategories(categories);

        return productRepository.save(product);
    }

    public List<Product> findProductsByCategory(String categoryName) {
        return productRepository.findByCategories_Name(categoryName);
    }
}
