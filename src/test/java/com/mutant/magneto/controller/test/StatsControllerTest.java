package com.mutant.magneto.controller.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.mutant.magneto.controller.StatsController;
import com.mutant.magneto.dto.StatsResponse;
import com.mutant.magneto.service.impl.StatsServiceImpl;

public class StatsControllerTest {
	
	private MockMvc mock;
	@InjectMocks
	private StatsController statsController;
	@Mock
	private StatsServiceImpl statsService;
	private static final String URL = "/stats";

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders.standaloneSetup(statsController).build();
	}
	
	@Test
	public void testInvocation() throws Exception {
		Mockito.when(statsService.getStats()).thenReturn(new StatsResponse(40, 100, 0.4));
		ResultActions execService = mock.perform(MockMvcRequestBuilders.get(URL));
		MvcResult mvnResult = execService.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String contentAsString = mvnResult.getResponse().getContentAsString();
		Assert.isTrue(!contentAsString.isEmpty(), "No empty");
	}
}
