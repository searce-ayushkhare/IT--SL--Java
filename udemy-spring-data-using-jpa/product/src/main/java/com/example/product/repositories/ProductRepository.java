package com.example.product.repositories;

import com.example.product.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    //@Query(value = "SELECT * FROM product WHERE name =:productName", nativeQuery = true)
    List<Product> findByName(String productName);
    List<Product> findByNameAndDesc(String productName, String productDesc);
    List<Product> findByPriceGreaterThan(Double price);
    List<Product> findByDescContains(String desc);
    List<Product> findByPriceBetween(Double lowPrice, Double highPrice);
    List<Product> findByDescLike(String desc);
    List<Product> findByIdIn(List<Integer> ids, Pageable pageable);
}
