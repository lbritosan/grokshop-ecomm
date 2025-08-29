package com.lbritosan.grokshop.service;

import com.lbritosan.grokshop.dto.ProductDTO;
import com.lbritosan.grokshop.entity.Category;
import com.lbritosan.grokshop.entity.Product;
import com.lbritosan.grokshop.exception.ResourceNotFoundException;
import com.lbritosan.grokshop.repository.CategoryRepository;
import com.lbritosan.grokshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Product createProduct(ProductDTO dto) {
        try {
            logger.info("Iniciando criação de produto: {}", dto);
            if (dto.getCategoryIds() == null || dto.getCategoryIds().isEmpty()) {
                logger.error("categoryIds é nulo ou vazio");
                throw new IllegalArgumentException("categoryIds não pode ser nulo ou vazio");
            }

            Product product = new Product();
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setStock(dto.getStock());

            List<Long> categoryIdsCopy = new ArrayList<>(dto.getCategoryIds());
            logger.info("Buscando categorias: {}", categoryIdsCopy);
            Set<Category> categories = categoryIdsCopy.stream()
                    .map(id -> {
                        logger.debug("Buscando categoria com ID: {}", id);
                        return categoryRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
                    })
                    .collect(Collectors.toSet());

            logger.info("Atribuindo categorias ao produto: {}", categories);
            product.getCategories().clear();
            product.getCategories().addAll(categories);

            logger.info("Salvando produto: {}", product);
            Product savedProduct = productRepository.save(product);
            logger.info("Produto salvo: {}", savedProduct);
            return savedProduct;
        } catch (ResourceNotFoundException e) {
            logger.error("Categoria não encontrada", e);
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("Erro de validação", e);
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao criar produto: {}", e.getClass().getSimpleName(), e);
            throw new RuntimeException("Erro ao criar produto: " + e.getClass().getSimpleName(), e);
        }
    }

    @Transactional
    public Product updateProduct(Long id, ProductDTO dto) {
        try {
            logger.info("Iniciando atualização do produto com ID: {}", id);
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));

            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setStock(dto.getStock());

            if (dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
                List<Long> categoryIdsCopy = new ArrayList<>(dto.getCategoryIds());
                logger.info("Buscando categorias para atualização: {}", categoryIdsCopy);
                Set<Category> categories = categoryIdsCopy.stream()
                        .map(categoryId -> {
                            logger.debug("Buscando categoria com ID: {}", categoryId);
                            return categoryRepository.findById(categoryId)
                                    .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));
                        })
                        .collect(Collectors.toSet());

                logger.info("Atribuindo novas categorias ao produto: {}", categories);
                product.getCategories().clear();
                product.getCategories().addAll(categories);
            }

            logger.info("Salvando produto atualizado: {}", product);
            Product updatedProduct = productRepository.save(product);
            logger.info("Produto atualizado com sucesso: {}", updatedProduct);
            return updatedProduct;
        } catch (ResourceNotFoundException e) {
            logger.error("Recurso não encontrado", e);
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao atualizar produto: {}", e.getClass().getSimpleName(), e);
            throw new RuntimeException("Erro ao atualizar produto: " + e.getClass().getSimpleName(), e);
        }
    }

    @Transactional
    public void deleteProduct(Long id) {
        try {
            logger.info("Iniciando deleção do produto com ID: {}", id);
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
            logger.info("Deletando produto: {}", product);
            productRepository.delete(product);
            logger.info("Produto deletado com sucesso: {}", id);
        } catch (ResourceNotFoundException e) {
            logger.error("Produto não encontrado", e);
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao deletar produto: {}", e.getClass().getSimpleName(), e);
            throw new RuntimeException("Erro ao deletar produto: " + e.getClass().getSimpleName(), e);
        }
    }

    public List<Product> findProductsByCategory(String categoryName) {
        try {
            logger.info("Buscando produtos por categoria: {}", categoryName);
            List<Product> products = productRepository.findByCategories_Name(categoryName);
            logger.info("Produtos encontrados: {}", products.size());
            return products;
        } catch (Exception e) {
            logger.error("Erro ao buscar produtos por categoria: {}", e.getClass().getSimpleName(), e);
            throw new RuntimeException("Erro ao buscar produtos: " + e.getClass().getSimpleName(), e);
        }
    }
}