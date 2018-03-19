package com.skipthedishes.customer;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SkipthedishesCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkipthedishesCustomerApplication.class, args);
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
