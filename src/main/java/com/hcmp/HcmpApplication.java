package com.hcmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hcmp.filter.JWTFilter;


@SpringBootApplication
public class HcmpApplication {

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean fb = new FilterRegistrationBean();
		fb.setFilter(new JWTFilter());
		//fb.addUrlPatterns("/api/ex/v2/*");
		fb.addUrlPatterns("/mem/api/**");
		fb.addUrlPatterns("/claim/api/**");
		return fb;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HcmpApplication.class, args);
	}
	
	/*
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/claim/api/**").allowedOrigins("*");
				registry.addMapping("/mem/api/**").allowedOrigins("*");
			}
		};
	}
	*/

}
