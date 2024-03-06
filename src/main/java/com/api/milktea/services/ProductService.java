package com.api.milktea.services;

import com.api.milktea.models.Product;
import com.api.milktea.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;

    public Product createProductService(
            float price,
            String description,
            String name,
            String public_id,
            String image_url) {
        Product product = new Product();
        product.setDescription(description);
        product.setName(name);
        product.setPrice((int) price);
        product.setActive(true);
//        product.setCapital_price(0);
        product.setPublicId(public_id);
        product.setImageUrl(image_url);
        return productsRepository.save(product);
    }

    public List<Product> getAllProductsService() {
        return (List<Product>) productsRepository.findAll();
    }

    public Optional<Product> getProductById(long id) {

        return productsRepository.findById(id);
    }

    public Product CreateProductServiceVer2(Product product) {
        return productsRepository.save(product);
    }

    public List<Product> getAllProductServiceTrue() {
       return productsRepository.findByActiveTrue();
    }

    public List<Product> searchProductsService(String keyword) {
        return productsRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Boolean deleteProductService(long id){
        try {
            productsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }


}
