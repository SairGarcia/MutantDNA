package com.mutant.magneto.service.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mutant.magneto.dao.impl.DBServiceDAOImpl;
import com.mutant.magneto.model.Human;
import com.mutant.magneto.model.Mutant;
import com.mutant.magneto.service.impl.IsMutantServiceImpl;

import junit.framework.TestCase;
public class IsMutantServiceImplTest {
	
	@InjectMocks
	private IsMutantServiceImpl isMutantServiceImpl;
	@Mock
	private DBServiceDAOImpl dbServiceDAOImpl;

	private String[] mutantDNAHorizontal = new String[] {"TTTTGA","CAGTGC","TTATGG","AGAAGG","CCCCTA","TCGCTG"};
	private String[] mutantDNAVertical = new String[] {"GTGCGA","CACTGC","TCATGG","CGATGG","CCCTTA","TCTCCG"};
	private String[] mutantDNADiagonal = new String[] {"TTGCGA","CCCTAC","TCGTGT","CGAGTG","CCCTGA","TCTCTG"};
	private String[] mutantDNAX = new String[] {"GTGCGA","CGCTAC","TCGTGG","CGAGTG","CCCCTA","TCTCTG"};
	private String[] humanDNA = new String[] {"TGGCGA","ACATAC","TCCTGG","CGAAAG","CCCGTA","TCGCTG"};
	private String[] dnaErrorPatt = new String[] {"CCCGTA","CACTGC","TCATGG","CGAAAG","JXGCGA","TCGCTG"};
	private String[] dnaNoFormat = new String[] {"CACTGC","CACTGC","TCATGG","CGAAAG","CCCGTA"};
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testOkMutantDNAHorizontal() throws Exception {
		String[] dna = mutantDNAHorizontal;
		Mutant mutant = new Mutant(dna);
		Mockito.doNothing().when(dbServiceDAOImpl).insert(Mockito.eq(mutant));
		boolean respBoolean = isMutantServiceImpl.isMutant(dna);
		TestCase.assertTrue(respBoolean);
	}

	@Test
	public void testOkMutantDNAVertical() throws Exception {
		String[] dna = mutantDNAVertical;
		Mutant mutant = new Mutant(dna);
		Mockito.doNothing().when(dbServiceDAOImpl).insert(Mockito.eq(mutant));
		boolean respBoolean = isMutantServiceImpl.isMutant(dna);
		TestCase.assertTrue(respBoolean);
	}
	
	@Test
	public void testMutantDNADiagonal() throws Exception {
		String[] dna = mutantDNADiagonal;
		Mutant mutant = new Mutant(dna);
		Mockito.doNothing().when(dbServiceDAOImpl).insert(Mockito.eq(mutant));
		boolean respBoolean = isMutantServiceImpl.isMutant(dna);
		TestCase.assertTrue(respBoolean);
	}
	
	@Test
	public void testMutantDNAX() throws Exception {
		String[] dna = mutantDNAX;
		Mutant mutant = new Mutant(dna);
		Mockito.doNothing().when(dbServiceDAOImpl).insert(Mockito.eq(mutant));
		boolean respBoolean = isMutantServiceImpl.isMutant(dna);
		TestCase.assertTrue(respBoolean);
	}
	
	@Test
	public void testHumanDNA() throws Exception {
		String[] dna = humanDNA;
		Human human = new Human(dna);
		Mockito.doNothing().when(dbServiceDAOImpl).insert(Mockito.eq(human));
		boolean respBoolean = isMutantServiceImpl.isMutant(dna);
		TestCase.assertFalse(respBoolean);
	}
	
	@Test(expected = Exception.class)
	public void testNoDNAPatt() throws Exception {
		String[] dna = dnaErrorPatt;
		isMutantServiceImpl.isMutant(dna);
	}
	
	@Test(expected = Exception.class)
	public void testDNANoFormat() throws Exception {
		String[] dna = dnaNoFormat;
		isMutantServiceImpl.isMutant(dna);
	}
	
	@Test(expected = Exception.class)
	public void testNoDNA() throws Exception {
		String[] dna = {};
		isMutantServiceImpl.isMutant(dna);
	}
}
