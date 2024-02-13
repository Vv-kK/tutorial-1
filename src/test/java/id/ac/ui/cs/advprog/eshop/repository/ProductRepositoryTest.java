package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {

    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEdit(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = productRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedProduct.setProductQuantity(60);
        editedProduct.setProductName("Sampo Cap Bango");
        Product result = productRepository.edit(editedProduct);

        assertEquals(editedProduct, result);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(editedProduct.getProductId(), savedProduct.getProductId());
        assertEquals(editedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), savedProduct.getProductQuantity());
        assertNotEquals(100, savedProduct.getProductQuantity());
        assertNotEquals("Sampo Cap Bambang", savedProduct.getProductName());
    }

    @Test
    void testEditIdNotExist(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        assertThrows(IllegalArgumentException.class, () -> productRepository.edit(product), "Product ID invalid");

        productRepository.create(product);
        assertThrows(IllegalArgumentException.class, () -> productRepository.getProductById("1"), "Product ID invalid");
        assertDoesNotThrow(() -> productRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        assertDoesNotThrow(() -> productRepository.edit(product));


        Product notExistProduct = new Product();
        notExistProduct.setProductId("sh82je9y2");
        notExistProduct.setProductName("Sabun Cap Bambang");
        notExistProduct.setProductQuantity(50);
        assertThrows(IllegalArgumentException.class, () -> productRepository.edit(notExistProduct));
    }

    @Test
    void testDelete(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteIdNotExist(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        productRepository.delete("1234");
        assertTrue(productIterator.hasNext());
    }

    @Test
    void testDeleteThenEdit(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertThrows(IllegalArgumentException.class, () -> productRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6"), "Product ID invalid");

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product currProduct = productIterator.next();
        assertFalse(productIterator.hasNext());
        assertEquals(product2.getProductId(), currProduct.getProductId());
        assertEquals(product2.getProductName(), currProduct.getProductName());
        assertEquals(product2.getProductQuantity(), currProduct.getProductQuantity());

        Product editedProduct = productRepository.getProductById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        editedProduct.setProductQuantity(10);
        editedProduct.setProductName("Sampo Cap Ucok");
        productRepository.edit(editedProduct);

        Iterator<Product> productIterator2 = productRepository.findAll();
        currProduct = productIterator2.next();
        assertFalse(productIterator2.hasNext());
        assertEquals(editedProduct.getProductId(), currProduct.getProductId());
        assertEquals(editedProduct.getProductName(), currProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), currProduct.getProductQuantity());
        assertNotEquals("Sampo Cap Usep", currProduct.getProductName());
        assertNotEquals(50, currProduct.getProductQuantity());
    }
}
