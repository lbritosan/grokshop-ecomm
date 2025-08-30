package com.lbritosan.grokshop.service;

import com.lbritosan.grokshop.dto.ProductDTO;
import com.lbritosan.grokshop.entity.Category;
import com.lbritosan.grokshop.entity.Product;
import com.lbritosan.grokshop.exception.ResourceNotFoundException;
import com.lbritosan.grokshop.repository.CategoryRepository;
import com.lbritosan.grokshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Category category;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        product = new Product();
        product.setId(1L);
        product.setName("Smartphone");
        product.setDescription("High-end smartphone");
        product.setPrice(BigDecimal.valueOf(999.99));
        product.setStock(50);
        product.setCategories(new HashSet<>(List.of(category)));

        productDTO = new ProductDTO();
        productDTO.setName("Smartphone");
        productDTO.setDescription("High-end smartphone");
        productDTO.setPrice(999.99);
        productDTO.setStock(50);
        productDTO.setCategoryIds(List.of(1L));
    }

    @Test
    void testCreateProduct_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product savedProduct = invocation.getArgument(0);
            savedProduct.setId(1L); // Simula atribuição de ID
            return savedProduct;
        });

        Product createdProduct = productService.createProduct(productDTO);

        assertNotNull(createdProduct);
        assertEquals("Smartphone", createdProduct.getName());
        assertEquals(BigDecimal.valueOf(999.99), createdProduct.getPrice());
        assertEquals(50, createdProduct.getStock());
        assertEquals(1, createdProduct.getCategories().size());
        assertEquals("Electronics", createdProduct.getCategories().iterator().next().getName());
        verify(categoryRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testCreateProduct_CategoryNotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.createProduct(productDTO));

        assertEquals("Category not found with id: 1", exception.getMessage());
        verify(categoryRepository, times(1)).findById(1L);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testUpdateProduct_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product updatedProduct = productService.updateProduct(1L, productDTO);

        assertNotNull(updatedProduct);
        assertEquals("Smartphone", updatedProduct.getName());
        assertEquals(BigDecimal.valueOf(999.99), updatedProduct.getPrice());
        assertEquals(50, updatedProduct.getStock());
        assertEquals(1, updatedProduct.getCategories().size());
        assertEquals("Electronics", updatedProduct.getCategories().iterator().next().getName());
        verify(productRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdateProduct_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.updateProduct(1L, productDTO));

        assertEquals("Product not found with id: 1", exception.getMessage());
        verify(productRepository, times(1)).findById(1L);
        verify(categoryRepository, never()).findById(any());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testDeleteProduct_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(any(Product.class));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testDeleteProduct_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.deleteProduct(1L));

        assertEquals("Product not found with id: 1", exception.getMessage());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, never()).delete(any(Product.class));
    }

    @Test
    void testFindProductsByCategory_Success() {
        when(productRepository.findByCategories_Name("Electronics")).thenReturn(List.of(product));

        List<Product> products = productService.findProductsByCategory("Electronics");

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Smartphone", products.get(0).getName());
        verify(productRepository, times(1)).findByCategories_Name("Electronics");
    }

    @Test
    void testFindById_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById(1L);

        assertNotNull(foundProduct);
        assertEquals("Smartphone", foundProduct.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.findById(1L));

        assertEquals("Product not found with id: 1", exception.getMessage());
        verify(productRepository, times(1)).findById(1L);
    }
}