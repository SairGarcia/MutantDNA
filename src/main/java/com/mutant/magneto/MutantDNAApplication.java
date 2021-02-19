package com.mutant.magneto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication 	
public class MutantDNAApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
    	SpringApplication.run(MutantDNAApplication.class, args);
    }
}
