package com.skipthedishes.products.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.model.dto.ProductDto;
import com.skipthedishes.products.exceptions.ProductNotFoundException;
import com.skipthedishes.products.service.ProductService;

@RestController
@RequestMapping("/Product")
public class ProductResource {

	private final ProductService productService;

	@Autowired
	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping({ "", "/search" })
	public ResponseEntity<List<ProductDto>> listAll() {

		final List<ProductDto> listAll = this.productService.listAll();

		return new ResponseEntity<>(listAll, HttpStatus.OK);
	}

	@GetMapping("/search/{searchText}")
	public ResponseEntity<List<ProductDto>> searchByName(@PathVariable(value = "searchText") String searchText) {

		final List<ProductDto> list = this.productService.searchByName(searchText);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping({ "/{productId}" })
	public ResponseEntity<ProductDto> getById(@PathVariable("productId") Long id) throws ProductNotFoundException {

		final ProductDto product = this.productService.findById(id);

		return new ResponseEntity<>(product, HttpStatus.OK);
	}
}
