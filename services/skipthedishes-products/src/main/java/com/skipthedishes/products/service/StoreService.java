package com.skipthedishes.products.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.skipthedishes.model.dto.ProductDto;
import com.skipthedishes.model.dto.StoreDto;
import com.skipthedishes.products.builder.ProductBuilder;
import com.skipthedishes.products.builder.StoreBuilder;
import com.skipthedishes.products.domain.Cousine;
import com.skipthedishes.products.domain.Product;
import com.skipthedishes.products.domain.Store;
import com.skipthedishes.products.exceptions.StoreNotFoundException;
import com.skipthedishes.products.messages.MessageByLocaleService;
import com.skipthedishes.products.repository.CousineRepository;
import com.skipthedishes.products.repository.ProductRepository;
import com.skipthedishes.products.repository.StoreRepository;

@Service
public class StoreService {

	private final StoreRepository storeRepository;

	private final ProductRepository productRepository;

	private final CousineRepository cousineRepository;

	private final MessageByLocaleService messageByLocaleService;

	private final ModelMapper modelMapper;

	private final ProductService productService;

	@Autowired
	public StoreService(final StoreRepository storeRepository, final ModelMapper modelMapper,
			CousineRepository cousineRepository, ProductRepository productRepository,
			MessageByLocaleService messageByLocaleService, ProductService productService) {
		this.storeRepository = storeRepository;
		this.modelMapper = modelMapper;
		this.cousineRepository = cousineRepository;
		this.productRepository = productRepository;
		this.messageByLocaleService = messageByLocaleService;
		this.productService = productService;
	}

	public List<StoreDto> findAll() {
		return this.convertListToDto(this.storeRepository.findAll(new Sort(Direction.ASC, "name")));
	}

	public List<StoreDto> searchByName(String searchText) {
		return this.convertListToDto(this.storeRepository.findByNameContainingIgnoreCase("%" + searchText + "%"));
	}

	public List<ProductDto> searchProductsByStoreId(Long storeId) {
		return this.convertListOfProductsToDto(this.productRepository.findByStoreId(storeId));
	}

	public StoreDto findById(Long storeId) throws StoreNotFoundException {
		final Optional<Store> findById = this.storeRepository.findById(storeId);

		if (!findById.isPresent()) {
			throw new StoreNotFoundException(this.messageByLocaleService.getMessage("store.not.found"));
		}

		return this.modelMapper.map(findById.get(), StoreDto.class);
	}

	public void prepareData() {

		final Store store1 = this.saveIfNotExists(new StoreBuilder().withName("Hai Shang")
				.withAddress("2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada")
				.withCousineId(this.getCousineByName("Chinese").get().getId()).build());

		// Products
		this.productService.createIfNotExists(new ProductBuilder().withStoreId(store1.getId())
				.withName("Shrimp Tempura").withDescription("Fresh shrimp battered and deep fried until golden brown")
				.withPrice(new BigDecimal(10.95)).build());

		this.productService.createIfNotExists(
				new ProductBuilder().withStoreId(store1.getId()).withName("Shrimp with Snow Peas and Cashew")
						.withDescription("A delicious combination of fresh shrimp, snow peas, and cashew")
						.withPrice(new BigDecimal(12.5)).build());

		this.saveIfNotExists(
				new StoreBuilder().withName("Ye's").withAddress("616 St James St, Winnipeg, Manitoba R3G 3J5, Canada")
						.withCousineId(this.getCousineByName("Chinese").get().getId()).build());

		this.saveIfNotExists(new StoreBuilder().withName("Good Earth Chop Suey House")
				.withAddress("1849 Portage Ave, Winnipeg, Manitoba R3J 0G8, Canada")
				.withCousineId(this.getCousineByName("Chinese").get().getId()).build());

		this.saveIfNotExists(new StoreBuilder().withName("Za Pizza Bistro")
				.withAddress("E-1220 St Mary's Rd, Winnipeg, Manitoba R2M 3V6, Canada")
				.withCousineId(this.getCousineByName("Pizza").get().getId()).build());
	}

	private Optional<Cousine> getCousineByName(String name) {
		return this.cousineRepository.findByNameAndLanguageAndCountry(name, "en", "CA");
	}

	private Store saveIfNotExists(final Store store) {
		final Optional<Store> optional = this.storeRepository.findByNameIgnoreCase(store.getName());

		if (!optional.isPresent()) {
			return this.storeRepository.save(store);
		}
		return optional.get();
	}

	private List<StoreDto> convertListToDto(final List<Store> list) {
		final List<StoreDto> finalList = new ArrayList<>();

		list.forEach((i) -> {
			final StoreDto dto = new StoreDto();
			this.modelMapper.map(i, dto);
			finalList.add(dto);
		});

		return finalList;
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

}
