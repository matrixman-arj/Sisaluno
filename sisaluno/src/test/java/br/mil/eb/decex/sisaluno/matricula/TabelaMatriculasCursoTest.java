package br.mil.eb.decex.sisaluno.matricula;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class TabelaMatriculasCursoTest {
	
	private TabelaMatriculasCurso tabelaMatriculasCurso;
	
	@Before
	public void setUp() {
		this.tabelaMatriculasCurso = new TabelaMatriculasCurso();
	}
	
	@Test
	public void deveRetornarNotaFinalSemNota() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaMatriculasCurso.getNotaTfm());
	}

}
