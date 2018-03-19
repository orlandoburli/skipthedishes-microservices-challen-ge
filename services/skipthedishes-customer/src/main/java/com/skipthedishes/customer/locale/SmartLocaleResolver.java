package com.skipthedishes.customer.locale;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

public class SmartLocaleResolver extends AcceptHeaderLocaleResolver {

	List<Locale> LOCALES = Arrays.asList(new Locale("en", "CA"), new Locale("pt", "BR"));

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		if (StringUtils.isEmpty(request.getHeader("Accept-Language"))) {
			return Locale.getDefault();
		}

		final List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
		final Locale locale = Locale.lookup(list, this.LOCALES);
		return locale;
	}
}
