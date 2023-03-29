package br.mil.eb.decex.sisaluno.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.mil.eb.decex.sisaluno.enumerated.CFGOCurso;
import br.mil.eb.decex.sisaluno.enumerated.CFGSCurso;
import br.mil.eb.decex.sisaluno.enumerated.CPORCurso;
import br.mil.eb.decex.sisaluno.enumerated.Categoria;
import br.mil.eb.decex.sisaluno.enumerated.MatBelCurso;
import br.mil.eb.decex.sisaluno.enumerated.MedicoCurso;
import br.mil.eb.decex.sisaluno.enumerated.ODONTOCurso;
import br.mil.eb.decex.sisaluno.enumerated.OficiaisCurso;
import br.mil.eb.decex.sisaluno.enumerated.QCOCurso;
import br.mil.eb.decex.sisaluno.validation.SKU;

@Entity
@Table(name = "curso", schema = "ensino")
public class Curso implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name = "ENSINO.CURSO_CODIGO_GENERATOR", sequenceName = "ENSINO.CURSO_CODIGO_SEQ",  allocationSize = 1)
    @GeneratedValue(        strategy = GenerationType.SEQUENCE, generator = "ENSINO.CURSO_CODIGO_GENERATOR")
    private Long codigo;
	
	@SKU
	@NotBlank
	private String sku;
	
	@Enumerated(EnumType.STRING)
    @NotNull(message = "Escolha um universo")
    private Categoria categoria;
	
	@Column(name = "universo_descr")
    private String universoDescr;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "cfgs_curso")
    private CFGSCurso cfgsCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "cfgo_curso")
    private CFGOCurso cfgoCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "matbel_curso")
    private MatBelCurso matbelCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "oficiais_curso")
    private OficiaisCurso oficiaisCurso;
    
    @Column
    private String area;
    
    @Enumerated(EnumType.STRING)    
    @Column(name = "cpor_curso")
    private CPORCurso cporCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "medico_curso")
    private MedicoCurso medicoCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "qco_curso")
    private QCOCurso qcoCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "odonto_curso")
    private ODONTOCurso odontoCurso;
    
    @Column
    private String especialidade;          
        
    @ManyToOne
    @JoinColumn(name = "npor_codigo")
    private Npor npor;
    
    @ManyToOne
    @JoinColumn(name = "uete_codigo")
    private Uete uete;   
    
    private String foto;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Transient
	private boolean novaFoto;
    
    @PrePersist
    private void prePersist() {       

        if (this.cfgsCurso != null) {
            this.setArea(this.cfgsCurso.getDescricao());
        }

        if (this.cfgoCurso != null) {
            this.setArea(this.cfgoCurso.getDescricao());
        }

        if (this.matbelCurso != null) {
            this.setArea(this.matbelCurso.getDescricao());
        }

        if (this.oficiaisCurso != null) {
            this.setArea(this.oficiaisCurso.getDescricao());
        }

        if (this.cporCurso != null) {
            this.setArea(this.cporCurso.getDescricao());
        }

        if (this.medicoCurso != null) {
            this.setArea(this.medicoCurso.getDescricao());
        }

        if (this.qcoCurso != null) {
            this.setArea(this.qcoCurso.getDescricao());
        }

        if (this.odontoCurso != null) {
            this.setArea(this.odontoCurso.getDescricao());
        }
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

	public CPORCurso getCporCurso() {
		return cporCurso;
	}

	public void setCporCurso(CPORCurso cporCurso) {
		this.cporCurso = cporCurso;
	}

	public MedicoCurso getMedicoCurso() {
		return medicoCurso;
	}

	public void setMedicoCurso(MedicoCurso medicoCurso) {
		this.medicoCurso = medicoCurso;
	}

	public QCOCurso getQcoCurso() {
		return qcoCurso;
	}

	public void setQcoCurso(QCOCurso qcoCurso) {
		this.qcoCurso = qcoCurso;
	}

	public ODONTOCurso getOdontoCurso() {
		return odontoCurso;
	}

	public void setOdontoCurso(ODONTOCurso odontoCurso) {
		this.odontoCurso = odontoCurso;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	public Npor getNpor() {
		return npor;
	}

	public void setNpor(Npor npor) {
		this.npor = npor;
	}

	public Uete getUete() {
		return uete;
	}

	public void setUete(Uete uete) {
		this.uete = uete;
	}	

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isNovaFoto() {
		return novaFoto;
	}

	public void setNovaFoto(boolean novaFoto) {
		this.novaFoto = novaFoto;
	}
	
	
}
