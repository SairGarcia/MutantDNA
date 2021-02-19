package com.mutant.magneto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutant.magneto.dao.DBServiceDAO;
import com.mutant.magneto.dto.StatsResponse;
import com.mutant.magneto.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {
	
	@Autowired
	private DBServiceDAO dbServiceDAO;
	
	/**
	 * getStats esta encaregado de retornar un objeto con los valores de cantidad de ADN mutante, humano y un ratio.
	 * ratio es la relación que existen entre las muestras de adn humano y mutante
	 */
	@Override
	public StatsResponse getStats() throws Exception {
		long countMutDNA = 0;
		long countHumDNA = 0;
		try {
			countMutDNA = dbServiceDAO.getMutantsCount();
			countHumDNA = dbServiceDAO.getHumansCount();
		} catch (Exception ex) {
			throw new Exception("Error en BD, por favor comuniquese con Admin");
		}
		double ratio = 0;
		if (countHumDNA != 0) {
			ratio = new Double(countMutDNA)/countHumDNA;
		}
		ratio = Math.round(ratio*100.0)/100.0;	
		return new StatsResponse(countMutDNA, countHumDNA, ratio);
	}
}
