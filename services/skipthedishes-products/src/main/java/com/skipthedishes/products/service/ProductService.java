package com.skipthedishes.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.skipthedishes.model.dto.ProductDto;
import com.skipthedishes.model.interfaces.IProduct;
import com.skipthedishes.products.domain.Product;
import com.skipthedishes.products.exceptions.ProductAlreadyExistsException;
import com.skipthedishes.products.exceptions.ProductNotFoundException;
import com.skipthedishes.products.messages.MessageByLocaleService;
import com.skipthedishes.products.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	private final MessageByLocaleService messageByLocaleService;

	private final ModelMapper modelMapper;

	@Autowired
	public ProductService(ProductRepository productRepository, MessageByLocaleService messageByLocaleService,
			ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.messageByLocaleService = messageByLocaleService;
		this.modelMapper = modelMapper;
	}

	public void create(Product product) throws ProductAlreadyExistsException {
		this.verifyIfExists(product);

		this.productRepository.save(product);
	}

	public void createIfNotExists(Product product) {
		try {
			this.create(product);
		} catch (final ProductAlreadyExistsException e) {
			// Do nothing.
		}
	}

	public void verifyIfExists(IProduct productDto) throws ProductAlreadyExistsException {
		final Optional<Product> findByNameAndStoreId = this.productRepository.findByNameAndStoreId(productDto.getName(),
				productDto.getStoreId());

		if (findByNameAndStoreId.isPresent()) {
			throw new ProductAlreadyExistsException(this.messageByLocaleService.getMessage("product.already.exists"));
		}
	}

	public List<ProductDto> listAll() {
		return this.convertListOfProductsToDto(this.productRepository.findAll(new Sort(Direction.ASC, "name")));
	}

	private List<ProductDto> convertListOfProductsToDto(final List<Product> list) {
		final List<ProductDto> finalList = new ArrayList<>();

		list.forEach((i) -> {
			final ProductDto dto = new ProductDto();
			this.modelMapper.map(i, dto);
			finalList.add(dto);
		});

		return finalList;
	}

	public List<ProductDto> searchByName(String searchText) {
		return this.convertListOfProductsToDto(
				this.productRepository.findByNameContainingIgnoreCaseOrderByName("%" + searchText + "%"));
	}

	public ProductDto findById(Long id) throws ProductNotFoundException {
		final Optional<Product> findById = this.productRepository.findById(id);

		if (!findById.isPresent()) {
			throw new ProductNotFoundException(this.messageByLocaleService.getMessage("product.not.found"));
		}
		return this.modelMapper.map(findById.get(), ProductDto.class);
	}

}
