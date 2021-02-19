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

import com.mutant.magneto.controller.IsMutantController;
import com.mutant.magneto.service.impl.IsMutantServiceImpl;

public class IsMutantControllerTest {

	private MockMvc mock;
	@InjectMocks
	private IsMutantController isMutantController;
	@Mock
	private IsMutantServiceImpl isMutantServiceImpl;
	
	private static final String JSON = "application/json";
	private static final String URL = "/mutant";
	private String[] mutant = new String[] {"AAAAGA","CAGTGC","TTATCG","AGATGG","CCCGTA","TCGCTG"};
	private String[] human = new String[] {"ATGCCA","CAGTGC","TTCTGG","AGAAGG","CCCGTA","TCGCTG"};
	private String[] noDNAvalid = new String[] {"ATGCCA","CAGTGC","TTCTGG","AGAAGG","TCGCTG"};
	private String mutantReqValid = "{\"dna\":[\"AAAAGA\",\"CAGTGC\",\"TTATCG\",\"AGATGG\",\"CCCGTA\",\"TCGCTG\"]}";
	private String humanReqValid = "{\"dna\":[\"ATGCCA\",\"CAGTGC\",\"TTCTGG\",\"AGAAGG\",\"CCCGTA\",\"TCGCTG\"]}";
	private String errorReq = "{\"dna\":[\"ATGCCA\",\"CAGTGC\",\"TTCTGG\",\"AGAAGG\",\"TCGCTG\"]}";

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders.standaloneSetup(isMutantController).build();
	}
	
	@Test
	public void testMutantDNA() throws Exception {
		mockIsMutantService(mutant, true);
		ResultActions execService = mock.perform(MockMvcRequestBuilders.post(URL).contentType(JSON).content(mutantReqValid));
		MvcResult mvnResult = execService.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String contentAsString = mvnResult.getResponse().getContentAsString();
		Assert.isTrue(contentAsString.isEmpty(), "Vacio");
	}

	@Test
	public void testHumanDNA() throws Exception {
		mockIsMutantService(human, false);
		ResultActions execService = mock.perform(MockMvcRequestBuilders.post(URL).contentType(JSON).content(humanReqValid));
		MvcResult mvnResult = execService.andExpect(MockMvcResultMatchers.status().isForbidden()).andReturn();
		String contentAsString = mvnResult.getResponse().getContentAsString();
		Assert.isTrue(contentAsString.isEmpty(), "Vacio");
	}
	
	@Test
	public void testErrorDNA() throws Exception {
		Mockito.doThrow(new Exception("exception")).when(isMutantServiceImpl).isMutant(noDNAvalid);
		ResultActions execService = mock.perform(MockMvcRequestBuilders.post(URL).contentType(JSON).content(errorReq));
		MvcResult mvnResult = execService.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
		String contentAsString = mvnResult.getResponse().getContentAsString();
		Assert.isTrue(contentAsString.isEmpty(), "Vacio");
	}
	
	private void mockIsMutantService(String[] sequenceDNA, boolean answer) throws Exception {
		Mockito.when(isMutantServiceImpl.isMutant(sequenceDNA)).thenReturn(answer);
	}

}

