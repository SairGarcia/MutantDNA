package com.mutant.magneto.service.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mutant.magneto.dao.impl.DBServiceDAOImpl;
import com.mutant.magneto.dto.StatsResponse;
import com.mutant.magneto.service.impl.StatsServiceImpl;

import junit.framework.TestCase;

public class StatsServiceImplTest {

	@InjectMocks
	private StatsServiceImpl statsServiceImpl;
	@Mock
	private DBServiceDAOImpl dbServiceDAOimpl;	
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testStatsServiceInvocation() throws Exception {
		Mockito.when(dbServiceDAOimpl.getMutantsCount()).thenReturn(40);
		Mockito.when(dbServiceDAOimpl.getHumansCount()).thenReturn(100);
		StatsResponse statsResp = statsServiceImpl.getStats();
		TestCase.assertNotNull(statsResp);
	}
}
