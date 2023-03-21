package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.repository.Cursos;
import br.mil.eb.decex.sisaluno.service.exception.CategoriaCursoJaCadastradoException;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroCursoService {
	
	@Autowired
	private Cursos cursos;
	
	@Transactional
	public void salvar(Curso curso) {
		Optional<Curso> cursoOptional = cursos.findByCategoria(curso.getCategoria().getDescricao());
		if (cursoOptional.isPresent() && !cursoOptional.get().equals(curso)) {
			throw new CategoriaCursoJaCadastradoException("Já existe um curso cadastrado com o cpf informado");
		}	
		
		cursos.saveAndFlush(curso);
	}
	
	@Transactional
	public void excluir(Long codigo ) { //Código para excluir quando não há foto.
		try {
			cursos.delete(codigo);
			cursos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar unidade. Já foi usada em alguma entidade.");
		}
	}

}
