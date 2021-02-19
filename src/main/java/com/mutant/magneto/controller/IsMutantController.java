package com.mutant.magneto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mutant.magneto.dto.MutantRequest;
import com.mutant.magneto.service.IsMutantService;

@RestController
public class IsMutantController {
	
	@Autowired
	private IsMutantService isMutantService;
	
	@RequestMapping(value="/mutant", method=RequestMethod.POST)
	public ResponseEntity<String> isMutant(@RequestBody MutantRequest req) throws Exception {
		int a = 1;
		System.out.println(a + "prueba");
		try {
			if(isMutantService.isMutant(req.getDna())) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		} 		
	}
}