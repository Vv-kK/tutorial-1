package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public void delete(String productId){
        Iterator<Product> products = findAll();
        while(products.hasNext()){
            Product currProduct = products.next();
            if (productId.equals(currProduct.getProductId())){
                products.remove();
                return;
            }
        }
    }

    public Product edit(Product editedproduct){
        for (int i=0; i < productData.size(); i++){
            Product produk = productData.get(i);
            if (produk.getProductId().equals(editedproduct.getProductId())){
                productData.set(i, editedproduct);
                return editedproduct;
            }
        }
        throw new IllegalArgumentException("Product ID invalid");
    }

    public Product getProductById(String productId){
        Iterator<Product> products = findAll();
        while(products.hasNext()){
            Product currProduct = products.next();
            if (productId.equals(currProduct.getProductId())){
                return currProduct;
            }
        }
        throw new IllegalArgumentException("Product ID invalid");
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }
}
