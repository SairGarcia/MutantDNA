package com.mutant.magneto.service.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutant.magneto.dao.DBServiceDAO;
import com.mutant.magneto.model.Human;
import com.mutant.magneto.model.Mutant;
import com.mutant.magneto.service.IsMutantService;

@Service
public class IsMutantServiceImpl implements IsMutantService {

	@Autowired	
	private DBServiceDAO dbServiceDAO;

	private int sequences;
	private static final String PATRON = "[atcg]+";
	private static final int MINIMAL_SEQUENCES = 2;
	private static final int LETRAS_IGUALES_EN_SECUENCIA = 4;

	private enum DireccionTipo { HORIZONTAL, VERTICAL, DIAGONAL }
	private enum Direccion { IZQ_A_DER, DER_A_IZQ }
		
	/**
	 * isMutant se encarga de realizar las validaciones del String de ADN
	 * para determinar si es Mutante o no.
	 * El retorno del metodo es booleano con el que podemos identificar fácilmente la respuesta que se otrorgara al response
	 * 
	 * Finalmente, sea humano o mutante realiza una insercion en base de datos a partir de la creacion de una de sus instancias
	 * 
	 * @param dna
	 * @return boolean
	 */
	@Override
	public boolean isMutant(String[] dna) throws Exception {
		boolean mutant = false;
		char[][] sequence = dataDNAanalize(dna);
		mutant = analizeDNAseq(sequence);
		if(mutant) {
			Mutant mutantObj = new Mutant(dna);
			System.out.println("Es mutante y por acá pasó para realizar inserción, que aún no está habilitada");
			dbServiceDAO.insert(mutantObj);
		} else {
			Human human = new Human(dna);
			System.out.println("Es humano y por acá pasó para realizar inserción, que aún no está habilitada");
			dbServiceDAO.insert(human);
		}
		return mutant;
	}
	
	/**
	 * dataDNAanalize se encarga de convertir el String de entrada en un char,
	 * que a su vez valida el contedino de cada secuencia contra un patron ya definido de letras.
	 * 
	 * Para verificar las dimensiones del String a partir del largo del String
	 * 
	 * @param dna
	 * @return char[][]
	 */
	private char[][] dataDNAanalize(String[] dna) throws Exception {	
		int lenghtDNA = dna.length;
		if(lenghtDNA < LETRAS_IGUALES_EN_SECUENCIA) {
			throw new Exception("Las dimensiones sin incorrectas para la secuencia que se ingreso.");
		}
		Pattern patron = Pattern.compile(PATRON, Pattern.CASE_INSENSITIVE);
		char[][] seq = new char[lenghtDNA][lenghtDNA];
		for (int i = 0; i < lenghtDNA ; i++) {
			if(dna[i].length() != lenghtDNA) {
				throw new Exception("Las dimensiones sin incorrectas para la secuencia que se ingreso, el numero de filas debe ser igual al numero de columnas.");
			} else if(!patron.matcher(dna[i]).matches()){
				throw new Exception("valores incorrectos en el patron de ADN establecido");
			} else {
				seq[i] = dna[i].toUpperCase().toCharArray();
			}
		}
		return seq;
	}
	
	/**
	 * analyzeDNA permite el ingreso de un valor tipo char the dna,
	 * se implementa lógica para recorrer los diferentes vectores que existen en la matriz.
	 * una vez tenemos el ingreso al primer ciclo se hace el llamado al método que analiza el ADN
	 * 
	 * se retorna true si es ADN mutante o false para humano
	 * 
	 * @param sequence
	 * @return boolean
	 */
	private boolean analizeDNAseq(char[][] sequence) {
		sequences = 0;
		for (int i = 0; i < sequence.length; i++) {
			if(showDNAnalize(sequence, i, 0, DireccionTipo.HORIZONTAL, null)) {
				return true;
			}
			if(showDNAnalize(sequence, 0, i, DireccionTipo.VERTICAL, null)) {
				return true;
			}
		}
		for (int i = 0; i <= sequence.length - LETRAS_IGUALES_EN_SECUENCIA; i++) {
			if(showDNAnalize(sequence, i, 0, DireccionTipo.DIAGONAL, Direccion.IZQ_A_DER)) {
				return true;
			}
			if(showDNAnalize(sequence, i, 0, DireccionTipo.DIAGONAL, Direccion.DER_A_IZQ)) {
				return true;
			}
		}
		for (int i = 1; i <= sequence.length - LETRAS_IGUALES_EN_SECUENCIA; i++) {
			if(showDNAnalize(sequence, 0, i, DireccionTipo.DIAGONAL, Direccion.IZQ_A_DER)) {
				return true;
			}
			if(showDNAnalize(sequence, 0, i, DireccionTipo.DIAGONAL, Direccion.DER_A_IZQ)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean showDNAnalize(char[][] sequence, int fila, int columna, DireccionTipo dirTipo, Direccion direccion) {
		char ultimoCaracter = 0;
		int seqLessOne = sequence.length - 1;
		int contarLetters = 0;
		int maxLimit = sequence.length;
		if (dirTipo.equals(DireccionTipo.DIAGONAL)) {
			maxLimit = sequence.length - fila - columna;
		}
		for (int j = 0; j < maxLimit; j++) {
			int filaIndicada = 0;
			int columnaIndicada = 0;
			if(dirTipo.equals(DireccionTipo.HORIZONTAL)) {
				filaIndicada = fila;
				columnaIndicada = j;
			} else if(dirTipo.equals(DireccionTipo.VERTICAL)) {
				filaIndicada = j;
				columnaIndicada = columna;
			} else {
				filaIndicada = j+fila;
				columnaIndicada = direccion.equals(Direccion.IZQ_A_DER) ? j+columna : seqLessOne-j-columna;
			}
			char caracterActual = sequence[filaIndicada][columnaIndicada];
			if(ultimoCaracter == 0) {
				ultimoCaracter = caracterActual;
			}
			if (caracterActual == ultimoCaracter) {
				contarLetters++;
				if(contarLetters == LETRAS_IGUALES_EN_SECUENCIA) {
					contarLetters = 0;
					sequences++;
					if(sequences == MINIMAL_SEQUENCES) {
						return true;
					}
				}
			} else {
				ultimoCaracter = caracterActual;
				contarLetters = 1;
			}
		}
		return false;
	}
}
