package br.mil.eb.decex.sisaluno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matriculas")
public class MatriculasController {
	
	@GetMapping("/nova")
	public String nova() {
		return "matricula/MatriculaAluno";
	}

}
