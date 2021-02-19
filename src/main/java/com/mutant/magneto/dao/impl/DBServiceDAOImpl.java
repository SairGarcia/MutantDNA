package com.mutant.magneto.dao.impl;

import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.mutant.magneto.dao.DBServiceDAO;
import com.mutant.magneto.dao.DynamoDAO;
import com.mutant.magneto.model.Human;
import com.mutant.magneto.model.Mutant;

@Service
public class DBServiceDAOImpl extends DynamoDAO implements DBServiceDAO {
	
	public DBServiceDAOImpl() {
		super();
	}
	
	@Override
	public void insert(Mutant mutant) {
		mapper.save(mutant);
	}
	
	@Override
	public void insert(Human human) {
		mapper.save(human);
	}
	
	@Override
	public int getHumansCount() {
		DynamoDBScanExpression dbScanExpression = new DynamoDBScanExpression();
		return mapper.count(Human.class, dbScanExpression);
	}
	
	@Override
	public int getMutantsCount() {
		DynamoDBScanExpression dbScanExpression = new DynamoDBScanExpression();
		return mapper.count(Mutant.class, dbScanExpression);
	}
}
