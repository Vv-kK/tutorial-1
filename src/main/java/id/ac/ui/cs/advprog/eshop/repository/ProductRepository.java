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


//    public Product edit(String productId, Product product){
//        productData.remove(product);
//        return product;
//    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }
}
