package com.example.demo;

import com.example.demo.dao.domain.Product;
import com.example.demo.dao.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vincent on 2017/7/15.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public Product getProductInfo(
            @PathVariable("id") Long id
    ) {
        return productMapper.select(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable("id")
                    Long productId,
            @RequestBody
                    Product newProduct
    ) {
        Product product = productMapper.select(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        productMapper.update(product);
        return product;
    }
}
