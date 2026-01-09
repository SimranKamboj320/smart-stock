package com.jsp.service;

import com.jsp.dto.ProductRequestDTO;
import com.jsp.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    // to save the product
   ProductResponseDTO save(ProductRequestDTO dto);

   // to search a product by id
   ProductResponseDTO findByProductId(int id);

   // to find all product of a company
   List<ProductResponseDTO> findByCompanyName(String companyName);

   // to find product by color
    List<ProductResponseDTO> findByColor(String color);

   // to update a product details
    ProductResponseDTO update(int productId, ProductRequestDTO dto);

    // get all product 5 at a time
    Page<ProductResponseDTO> findAllProduct(Pageable pageable);

    // to delete a product
    void delete(String productId);

    // to purchase a product




}
