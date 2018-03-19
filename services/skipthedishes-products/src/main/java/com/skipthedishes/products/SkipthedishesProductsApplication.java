package com.skipthedishes.products;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.skipthedishes.products.service.CousineService;
import com.skipthedishes.products.service.StoreService;

@SpringBootApplication
public class SkipthedishesProductsApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(SkipthedishesProductsApplication.class,
				args);

		// Prepare the data for startup the server
		context.getBean(CousineService.class).prepareData();
		context.getBean(StoreService.class).prepareData();
	}

	@Bean
	public ModelMapper buildModelMapper() {
		return new ModelMapper();
	}

	@Bean
	public LocaleResolver localeResolver() {
		final SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.CANADA);
		return slr;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:locale/messages");
		messageSource.setCacheSeconds(3600);
		return messageSource;
	}
}
