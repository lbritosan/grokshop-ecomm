package com.lbritosan.grokshop.service;

import com.lbritosan.grokshop.dto.ProductDTO;
import com.lbritosan.grokshop.entity.Category;
import com.lbritosan.grokshop.entity.Product;
import com.lbritosan.grokshop.exception.ResourceNotFoundException;
import com.lbritosan.grokshop.repository.CategoryRepository;
import com.lbritosan.grokshop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product createProduct(ProductDTO productDTO) {
        logger.info("Iniciando criação de produto: {}", productDTO);
        logger.info("Buscando categorias: {}", productDTO.getCategoryIds());
        Set<Category> categories = productDTO.getCategoryIds().stream()
                .map(categoryId -> {
                    Optional<Category> category = categoryRepository.findById(categoryId);
                    return category.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
                })
                .collect(Collectors.toSet());
        logger.info("Atribuindo categorias ao produto: {}", categories);

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(BigDecimal.valueOf(productDTO.getPrice()));
        product.setStock(productDTO.getStock());
        product.setCategories(categories);
        logger.info("Salvando produto: {}", product);

        Product savedProduct = productRepository.save(product);
        logger.info("Produto salvo: {}", savedProduct);
        return savedProduct;
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        logger.info("Iniciando atualização do produto com ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        logger.info("Buscando categorias para atualização: {}", productDTO.getCategoryIds());
        Set<Category> categories = productDTO.getCategoryIds().stream()
                .map(categoryId -> {
                    Optional<Category> category = categoryRepository.findById(categoryId);
                    return category.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
                })
                .collect(Collectors.toSet());
        logger.info("Atribuindo novas categorias ao produto: {}", categories);

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(BigDecimal.valueOf(productDTO.getPrice()));
        product.setStock(productDTO.getStock());
        product.setCategories(categories);
        logger.info("Salvando produto atualizado: {}", product);

        Product updatedProduct = productRepository.save(product);
        logger.info("Produto atualizado com sucesso: {}", updatedProduct);
        return updatedProduct;
    }

    public void deleteProduct(Long id) {
        logger.info("Iniciando deleção do produto com ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        logger.info("Deletando produto: {}", product);
        productRepository.delete(product);
        logger.info("Produto deletado com sucesso: {}", id);
    }

    public List<Product> findProductsByCategory(String categoryName) {
        logger.info("Buscando produtos por categoria: {}", categoryName);
        List<Product> products = productRepository.findByCategories_Name(categoryName);
        logger.info("Produtos encontrados: {}", products.size());
        return products;
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
}