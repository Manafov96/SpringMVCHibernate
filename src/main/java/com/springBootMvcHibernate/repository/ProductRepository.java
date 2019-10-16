package com.springBootMvcHibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBootMvcHibernate.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
