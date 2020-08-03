package org.kp.restws.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/home-i18n")
	public String i18n() {
		return messageSource.getMessage("Good.moning.message",null,LocaleContextHolder.getLocale());
	}
	
	
}
