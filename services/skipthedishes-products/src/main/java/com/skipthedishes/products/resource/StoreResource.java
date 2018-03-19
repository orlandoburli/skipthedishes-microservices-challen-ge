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
import com.skipthedishes.model.dto.StoreDto;
import com.skipthedishes.products.exceptions.StoreNotFoundException;
import com.skipthedishes.products.service.StoreService;

@RestController
@RequestMapping("/Store")

public class StoreResource {

	private final StoreService storeService;

	@Autowired
	public StoreResource(StoreService storeService) {
		this.storeService = storeService;
	}

	@GetMapping({ "", "/search" })
	public ResponseEntity<List<StoreDto>> listAll() {
		final List<StoreDto> listAll = this.storeService.findAll();

		return new ResponseEntity<>(listAll, HttpStatus.OK);
	}

	@GetMapping({ "/{storeId}/products" })
	public ResponseEntity<List<ProductDto>> searchProductsByStoreId(@PathVariable("storeId") Long storeId) {
		final List<ProductDto> listAll = this.storeService.searchProductsByStoreId(storeId);

		return new ResponseEntity<>(listAll, HttpStatus.OK);
	}

	@GetMapping({ "/{storeId}" })
	public ResponseEntity<StoreDto> getStore(@PathVariable("storeId") Long storeId) throws StoreNotFoundException {
		final StoreDto store = this.storeService.findById(storeId);

		return new ResponseEntity<>(store, HttpStatus.OK);
	}

	@GetMapping("/search/{searchText}")
	public ResponseEntity<List<StoreDto>> searchByName(@PathVariable(value = "searchText") String searchText) {
		final List<StoreDto> list = this.storeService.searchByName(searchText);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}