package com.skipthedishes.products.messages;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(String id) {
		final Locale locale = LocaleContextHolder.getLocale();
		return this.messageSource.getMessage(id, null, locale);
	}
}
