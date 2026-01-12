package com.jsp.service.impl;

import com.jsp.dto.ProductRequestDTO;
import com.jsp.dto.ProductResponseDTO;
import com.jsp.entity.Product;
import com.jsp.exception.ProductNotFoundException;
import com.jsp.repository.ProductRepository;
import com.jsp.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    // map dto to entity
    public Product mapToEntity(ProductRequestDTO dto){
        Product p=new Product();
        p.setProductName(dto.getProductName());
        p.setPrice(dto.getPrice());
        p.setCategory(dto.getCategory());
        p.setColor(dto.getColor());
        p.setCompanyName(dto.getCompanyName());
        p.setDateOfExpiry(dto.getDateOfExpiry());
        p.setDescription(dto.getDescription());
        return p;
    }

    // map entity to dto
    public ProductResponseDTO mapToDto(Product p){
        ProductResponseDTO dto=new ProductResponseDTO();
        dto.setProductName(p.getProductName());
        dto.setPrice(p.getPrice());
        dto.setCategory(p.getCategory());
        dto.setColor(p.getColor());
        dto.setCompanyName(p.getCompanyName());
        dto.setDateOfExpiry(p.getDateOfExpiry());
        dto.setDescription(p.getDescription());
        return dto;
    }

    public Page<ProductResponseDTO> mapToDto(Page<Product> products){
        return products.map(this::mapToDto);
    }

    @Override
    public ProductResponseDTO save(ProductRequestDTO dto){
        Product product=mapToEntity(dto);
        return mapToDto(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO findByProductId(int id){
        return mapToDto(productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found by given Id.")));
    }

    @Override
    public List<ProductResponseDTO> findByCompanyName(String companyName){
        return productRepository.findByCompanyName(companyName)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> findByColor(String color){
        return productRepository.findByColor(color)
                .stream()
                .map(this::mapToDto)
                .toList();
    }
    @Override
    public Page<ProductResponseDTO> findAllProduct(Pageable pageable){
        return mapToDto(productRepository.findAll(pageable));
    }

    @Override
    @Transactional
    public ProductResponseDTO update(int productId,ProductRequestDTO dto){
        Product product=productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product does not exist."));
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setColor(dto.getColor());
        product.setCompanyName(dto.getCompanyName());
        product.setDateOfExpiry(dto.getDateOfExpiry());
        product.setDescription(dto.getDescription());

        return mapToDto(productRepository.save(product));
    }
    @Override
    public void delete(int id){
        Product product=productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Project does not exist with this Id"));
        productRepository.delete(product);
    }
}