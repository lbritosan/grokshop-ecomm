package com.lbritosan.grokshop.controller;

import com.lbritosan.grokshop.dto.ProductDTO;
import com.lbritosan.grokshop.entity.Product;
import com.lbritosan.grokshop.exception.ResourceNotFoundException;
import com.lbritosan.grokshop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Recebendo requisição para criar produto: {}", productDTO);
        try {
            Product product = productService.createProduct(productDTO);
            logger.info("Produto criado com sucesso: {}", product);
            return ResponseEntity.ok(product);
        } catch (ResourceNotFoundException e) {
            logger.error("Erro ao criar produto: {}", e.getMessage());
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            logger.error("Erro interno ao criar produto", e);
            throw e;
        }
    }

    @Operation(summary = "Update a product", description = "Updates an existing product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        logger.info("Recebendo requisição para atualizar produto com ID: {}", id);
        try {
            Product updatedProduct = productService.updateProduct(id, productDTO);
            logger.info("Produto atualizado com sucesso: {}", updatedProduct);
            return ResponseEntity.ok(updatedProduct);
        } catch (ResourceNotFoundException e) {
            logger.error("Erro ao atualizar produto: {}", e.getMessage());
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            logger.error("Erro interno ao atualizar produto", e);
            throw e;
        }
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("Recebendo requisição para deletar produto com ID: {}", id);
        try {
            productService.deleteProduct(id);
            logger.info("Produto deletado com sucesso: {}", id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            logger.error("Erro ao deletar produto: {}", e.getMessage());
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            logger.error("Erro interno ao deletar produto", e);
            throw e;
        }
    }

    @Operation(summary = "Find products by category", description = "Retrieves a list of products by category name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable String categoryName) {
        logger.info("Buscando produtos por categoria: {}", categoryName);
        try {
            List<Product> products = productService.findProductsByCategory(categoryName);
            return ResponseEntity.ok(products);
        } catch (ResourceNotFoundException e) {
            logger.error("Erro ao buscar produtos por categoria: {}", e.getMessage());
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            logger.error("Erro interno ao buscar produtos por categoria", e);
            throw e;
        }
    }

    @Operation(summary = "Get a product by ID", description = "Retrieves a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.info("Buscando produto por ID: {}", id);
        try {
            Product product = productService.findById(id);
            return ResponseEntity.ok(product);
        } catch (ResourceNotFoundException e) {
            logger.error("Erro ao buscar produto: {}", e.getMessage());
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            logger.error("Erro interno ao buscar produto por ID: {}", id, e);
            throw e;
        }
    }
}