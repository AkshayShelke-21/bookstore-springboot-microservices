package com.akshaylab.bookstore.catalog.web.Controllers;

import com.akshaylab.bookstore.catalog.domain.PagedResult;
import com.akshaylab.bookstore.catalog.domain.Product;
import com.akshaylab.bookstore.catalog.domain.ProductNotFoundException;
import com.akshaylab.bookstore.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo){
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code){
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> ProductNotFoundException.forCode(code));
    }
}
