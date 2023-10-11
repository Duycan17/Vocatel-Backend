package net.codejava.controller;

import java.net.URI;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import net.codejava.entity.Product;
import net.codejava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductRepository repo;
	
	@PostMapping
	@RolesAllowed("ROLE_EDITOR")
	public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
		Product savedProduct = repo.save(product);
		URI productURI = URI.create("/products/" + savedProduct.getId());
		return ResponseEntity.created(productURI).body(savedProduct);
	}
	
	@GetMapping
	@RolesAllowed({"ROLE_CUSTOMER", "ROLE_EDITOR"})
	public List<Product> list() {
		return repo.findAll();
	}
}
