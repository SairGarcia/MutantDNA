package com.mutant.magneto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mutant.magneto.dto.StatsResponse;
import com.mutant.magneto.service.StatsService;

@RestController
public class StatsController {
	
	@Autowired
	private StatsService statsService;
	
	@RequestMapping(value="/stats", method=RequestMethod.GET)
	public StatsResponse getStats() {
		StatsResponse statsResp;
		try {
			statsResp = statsService.getStats();
		} catch (Exception ex) {
			return new StatsResponse(0,0,0);
		}
		return statsResp;
	}
}
