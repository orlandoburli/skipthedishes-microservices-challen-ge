package com.skipthedishes.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.skipthedishes.model.dto.CousineDto;
import com.skipthedishes.products.builder.CousineBuilder;
import com.skipthedishes.products.domain.Cousine;
import com.skipthedishes.products.repository.CousineRepository;

@Service
public class CousineService {

	private static final String CA = "CA";
	private static final String BR = "BR";

	private static final String EN = "en";
	private static final String PT = "pt";

	private final CousineRepository cousineRepository;

	private final ModelMapper modelMapper;

	@Autowired
	public CousineService(final CousineRepository cousineRepository, final ModelMapper modelMapper) {
		this.cousineRepository = cousineRepository;
		this.modelMapper = modelMapper;
	}

	public List<CousineDto> listAll() {
		final Locale locale = LocaleContextHolder.getLocale();

		return this.convertListToDto(
				this.cousineRepository.findByLanguageAndCountry(locale.getLanguage(), locale.getCountry()));
	}

	public List<CousineDto> searchByName(String name) {
		final Locale locale = LocaleContextHolder.getLocale();

		return this.convertListToDto(
				this.cousineRepository.findByNameContainingIgnoreCaseAndLanguageAndCountryOrderByName("%" + name + "%",
						locale.getLanguage(), locale.getCountry()));
	}

	public void prepareData() {
		// Build data for en_CA
		this.saveIfNotExists(new CousineBuilder().withName("Chinese").withLanguage(EN).withCountry(CA).build());
		this.saveIfNotExists(new CousineBuilder().withName("Pizza").withLanguage(EN).withCountry(CA).build());
		this.saveIfNotExists(new CousineBuilder().withName("Vietnamese").withLanguage(EN).withCountry(CA).build());
		this.saveIfNotExists(new CousineBuilder().withName("Sushi").withLanguage(EN).withCountry(CA).build());

		// Build data for pt_BR
		this.saveIfNotExists(new CousineBuilder().withName("Chinesa").withLanguage(PT).withCountry(BR).build());
		this.saveIfNotExists(new CousineBuilder().withName("Pizza").withLanguage(PT).withCountry(BR).build());
		this.saveIfNotExists(new CousineBuilder().withName("Vietnamita").withLanguage(PT).withCountry(BR).build());
		this.saveIfNotExists(new CousineBuilder().withName("Sushi").withLanguage(PT).withCountry(BR).build());
	}

	private void saveIfNotExists(final Cousine cousine) {
		final Optional<Cousine> c = this.cousineRepository.findByNameAndLanguageAndCountry(cousine.getName(),
				cousine.getLanguage(), cousine.getCountry());

		if (!c.isPresent()) {
			this.cousineRepository.save(cousine);
		}
	}

	private List<CousineDto> convertListToDto(final List<Cousine> list) {
		final List<CousineDto> finalList = new ArrayList<>();

		list.forEach((i) -> {
			final CousineDto dto = new CousineDto();
			this.modelMapper.map(i, dto);
			finalList.add(dto);
		});

		return finalList;
	}
}
