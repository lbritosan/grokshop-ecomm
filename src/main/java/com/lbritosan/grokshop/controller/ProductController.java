package com.lbritosan.grokshop.controller;

import com.lbritosan.grokshop.dto.ProductDTO;
import com.lbritosan.grokshop.entity.Product;
import com.lbritosan.grokshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Recebendo requisição para criar produto: {}", productDTO);
        try {
            Product product = productService.createProduct(productDTO);
            logger.info("Produto criado com sucesso: {}", product);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.error("Erro interno ao criar produto", e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        logger.info("Recebendo requisição para atualizar produto com ID: {}", id);
        try {
            Product updatedProduct = productService.updateProduct(id, productDTO);
            logger.info("Produto atualizado com sucesso: {}", updatedProduct);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            logger.error("Erro interno ao atualizar produto", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("Recebendo requisição para deletar produto com ID: {}", id);
        try {
            productService.deleteProduct(id);
            logger.info("Produto deletado com sucesso: {}", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Erro interno ao deletar produto", e);
            throw e;
        }
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable String categoryName) {
        logger.info("Buscando produtos por categoria: {}", categoryName);
        List<Product> products = productService.findProductsByCategory(categoryName);
        return ResponseEntity.ok(products);
    }
}