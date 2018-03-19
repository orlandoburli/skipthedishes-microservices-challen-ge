package com.skipthedishes.products.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.model.dto.CousineDto;
import com.skipthedishes.products.service.CousineService;

@RestController
@RequestMapping("/Cousine")
public class CousineResource {

	private final CousineService cousineService;

	@Autowired
	public CousineResource(CousineService cousineService) {
		this.cousineService = cousineService;
	}

	@GetMapping({ "", "/search" })
	public ResponseEntity<List<CousineDto>> listAll() {

		final List<CousineDto> listAll = this.cousineService.listAll();

		return new ResponseEntity<>(listAll, HttpStatus.OK);
	}

	@GetMapping("/search/{searchText}")
	public ResponseEntity<List<CousineDto>> searchByName(@PathVariable(value = "searchText") String searchText) {

		final List<CousineDto> list = this.cousineService.searchByName(searchText);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
