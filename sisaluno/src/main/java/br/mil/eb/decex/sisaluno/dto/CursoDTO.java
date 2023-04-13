package br.mil.eb.decex.sisaluno.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.mil.eb.decex.sisaluno.enumerated.CFGOCurso;
import br.mil.eb.decex.sisaluno.enumerated.CFGSCurso;
import br.mil.eb.decex.sisaluno.enumerated.Categoria;
import br.mil.eb.decex.sisaluno.enumerated.MatBelCurso;
import br.mil.eb.decex.sisaluno.enumerated.OficiaisCurso;

@Entity
@Table(name = "curso", schema = "ensino")
public class CursoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
    private Long codigo;
    private String sku;
    private Categoria categoria;	
    private CFGSCurso cfgsCurso;    
    private CFGOCurso cfgoCurso;    
    private MatBelCurso matbelCurso;    
    private OficiaisCurso oficiaisCurso;    
    private String area;
	
    
    public CursoDTO(Long codigo, String sku, Categoria categoria, CFGSCurso cfgsCurso, CFGOCurso cfgoCurso, MatBelCurso matbelCurso,
    		OficiaisCurso oficiaisCurso, String area) {
		
		this.codigo = codigo;
		this.sku = sku;
		this.categoria = categoria;
		this.cfgsCurso = cfgsCurso;
		this.cfgoCurso = cfgoCurso;
		this.matbelCurso = matbelCurso;
		this.oficiaisCurso = oficiaisCurso;
		this.area = area;
	}


	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public CFGSCurso getCfgsCurso() {
		return cfgsCurso;
	}
	public void setCfgsCurso(CFGSCurso cfgsCurso) {
		this.cfgsCurso = cfgsCurso;
	}

	public CFGOCurso getCfgoCurso() {
		return cfgoCurso;
	}
	public void setCfgoCurso(CFGOCurso cfgoCurso) {
		this.cfgoCurso = cfgoCurso;
	}

	public MatBelCurso getMatbelCurso() {
		return matbelCurso;
	}
	public void setMatbelCurso(MatBelCurso matbelCurso) {
		this.matbelCurso = matbelCurso;
	}

	public OficiaisCurso getOficiaisCurso() {
		return oficiaisCurso;
	}
	public void setOficiaisCurso(OficiaisCurso oficiaisCurso) {
		this.oficiaisCurso = oficiaisCurso;
	}

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}


	public String getSku() {
		return sku;
	}


	public void setSku(String sku) {
		this.sku = sku;
	}	
    
}
