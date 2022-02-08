package com.Spring.Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//(exclude = {SecurityAutoConfiguration.class}) deixa de validar usuario e senha do SpringSecurity
public class Cripto_Endecode {

	public static void main(String[] args) {SpringApplication.run(Cripto_Endecode.class, args);}


		@Bean
		public PasswordEncoder getPasswordEncoder(){


			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;

		}



}
