package br.mil.eb.decex.sisaluno.matricula;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.model.Matricula;

public class TabelaMatriculasCurso {
	
	private List<Matricula> matriculas = new ArrayList<>();
	
	public BigDecimal getNotaTfm() {
		
		return matriculas.stream()
				.map(Matricula::getNotaTfm)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarCurso(Curso curso, BigDecimal tfm, BigDecimal tfm2, BigDecimal tfm3 ) {
		Matricula matriculaCurso = new Matricula();
//		matriculaCurso.setCurso(curso);
		matriculaCurso.setTfm(tfm);
		matriculaCurso.setTfm2(tfm2);
		matriculaCurso.setTfm3(tfm3);
//		matriculaCurso.setNotaTfm(curso.getNotaTfm());
		
		matriculas.add(matriculaCurso);
	}

}
