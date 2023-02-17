package com.example.product;

import com.example.product.entities.Product;
import com.example.product.repositories.ProductRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate() {
		Product product = new Product();
		product.setId(1);
		product.setName("iphone");
		product.setDesc("will buy");
		product.setPrice(1000d);

		productRepository.save(product);
	}

	@Test
	public void testRead() {
		Product product = productRepository.findById(1).get();
		assertNotNull(product);
		assertEquals("iphone", product.getName());
	}

	@Test
	public void testUpdate() {
		Product product = productRepository.findById(1).get();
		product.setPrice(1200d);

		productRepository.save(product);
	}

	@Test
	public void testDelete() {
		if (productRepository.existsById(1)) {
			productRepository.deleteById(1);
		}
	}

	@Test
	public void testCount() {
		System.out.println("Total records :: " + productRepository.count());
	}

	@Test
	public void testFindByName() {
		List<Product> products = productRepository.findByName("macbook");
		products.forEach(p -> System.out.println(p.getPrice()));
	}

	@Test
	public void testFindByNameAndDesc() {
		List<Product> products = productRepository.findByNameAndDesc("iphone", "will buy");
		products.forEach(p -> System.out.println(p.getDesc() + "," + p.getPrice()));
	}

	@Test
	public void testFindByPriceGreaterThan() {
		List<Product> products = productRepository.findByPriceGreaterThan(300d);
		products.forEach(p -> System.out.println(p.getDesc() + "," + p.getPrice()));
	}

	@Test
	public void testFindByDescContains() {
		List<Product> products = productRepository.findByDescContains("will");
		products.forEach(p -> System.out.println(p.getDesc() + "," + p.getPrice()));
	}

	@Test
	public void testFindByPriceBetween() {
		List<Product> products = productRepository.findByPriceBetween(300d, 1000d);
		products.forEach(p -> System.out.println(p.getDesc() + "," + p.getPrice()));
	}

	@Test
	public void testFindByDescLike() {
		List<Product> products = productRepository.findByDescLike("%y%");
		products.forEach(p -> System.out.println(p.getDesc() + "," + p.getPrice()));
	}

	@Test
	public void testFindByIdIn() {
		Pageable pageable = PageRequest.of(1,2);
		List<Product> products = productRepository.findByIdIn(Arrays.asList(2,3,4,5), pageable);
		products.forEach(p -> System.out.println(p.getDesc() + "," + p.getPrice()));
	}

	@Test
	public void testFindAllPaging() {
		Pageable pageable = PageRequest.of(1, 2);

		Page<Product> results = productRepository.findAll(pageable);
		results.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	public void testFindAll() {
//		productRepository
//				.findAll(Sort.by(Sort.Direction.DESC, "name", "price"))
//				.forEach(p -> System.out.println(p.getName() + " :: $" + p.getPrice()));

		productRepository
				.findAll(Sort.by(
					new Sort.Order(Sort.Direction.DESC, "name"),
					new Sort.Order(null, "price")
				))
				.forEach(p -> System.out.println(p.getName() + " :: $" + p.getPrice()));
	}

	@Test
	public void testFindAllPagingAndSorting() {
		Pageable pageable = PageRequest.of(1, 2, Sort.Direction.DESC, "name");
		productRepository.findAll(pageable).forEach(p -> System.out.println(p.getName() + " :: $" + p.getPrice()));
	}

	@Test
	@Transactional
	public void testCaching() {
		Session session = entityManager.unwrap(Session.class);

		Product product = productRepository.findById(1).get();
		productRepository.findById(1);

		session.evict(product);
		productRepository.findById(1);
	}
}
