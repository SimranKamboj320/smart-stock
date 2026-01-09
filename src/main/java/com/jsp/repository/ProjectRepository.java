package com.jsp.repository;

import com.jsp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCompanyName(String companyName);

    List<Product> findByColor(String color);

}
