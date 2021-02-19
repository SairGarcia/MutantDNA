package com.mutant.magneto.dao;

import com.mutant.magneto.model.Human;
import com.mutant.magneto.model.Mutant;

public interface DBServiceDAO {
	
	public void insert(Mutant mutant) throws Exception;
	
	public void insert(Human human) throws Exception;
	
	public int getHumansCount() throws Exception;
	
	public int getMutantsCount() throws Exception;
}