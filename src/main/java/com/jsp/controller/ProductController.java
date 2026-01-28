package com.jsp.controller;

import com.jsp.dto.ProductRequestDTO;
import com.jsp.dto.ProductResponseDTO;

import com.jsp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/save")
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductRequestDTO dto){
        return ResponseEntity.ok(productService.save(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestParam int productId, @RequestBody ProductRequestDTO dto){
        return ResponseEntity.ok(productService.update(productId,dto));
    }

    @GetMapping("/findById")
    public ResponseEntity<ProductResponseDTO> findProductById(@RequestParam int productId){
        return ResponseEntity.ok(productService.findByProductId(productId));
    }

    @GetMapping("/findByCompany")
    public ResponseEntity<List<ProductResponseDTO>> findByCompanyName(@RequestParam String companyName){
        return ResponseEntity.ok(productService.findByCompanyName(companyName));
    }

    @GetMapping("/findByColor")
    public ResponseEntity<List<ProductResponseDTO>> findByProductColor(@RequestParam String color){
        return ResponseEntity.ok(productService.findByColor(color));
    }

    @GetMapping("/findAllProduct")
    public ResponseEntity<Page<ProductResponseDTO>> findAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size ){
            return ResponseEntity.ok(productService.findAllProduct(PageRequest.of(page,size)));
    }

    @DeleteMapping("/removeProduct")
    public ResponseEntity<Void> delete(@RequestParam int productId){
            productService.delete(productId);
           return ResponseEntity.noContent().build();
    }
}
