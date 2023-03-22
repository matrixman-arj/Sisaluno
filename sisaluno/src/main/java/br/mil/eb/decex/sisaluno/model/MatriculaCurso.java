package br.mil.eb.decex.sisaluno.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.mil.eb.decex.sisaluno.enumerated.CFGOCurso;
import br.mil.eb.decex.sisaluno.enumerated.CFGSCurso;
import br.mil.eb.decex.sisaluno.enumerated.CPORCurso;
import br.mil.eb.decex.sisaluno.enumerated.Categoria;
import br.mil.eb.decex.sisaluno.enumerated.MatBelCurso;
import br.mil.eb.decex.sisaluno.enumerated.MedicoCurso;
import br.mil.eb.decex.sisaluno.enumerated.ODONTOCurso;
import br.mil.eb.decex.sisaluno.enumerated.OficiaisCurso;
import br.mil.eb.decex.sisaluno.enumerated.Periodo;
import br.mil.eb.decex.sisaluno.enumerated.QCOCurso;

@Entity
@Table(name = "matricula_curso", schema = "ensino")
public class MatriculaCurso {
	
	@Id
    @SequenceGenerator(name = "ENSINO.MATRICULA_CURSO_CODIGO_GENERATOR", sequenceName = "ENSINO.MATRICULA_CURSO_CODIGO_SEQ",  allocationSize = 1)
    @GeneratedValue(        strategy = GenerationType.SEQUENCE, generator = "ENSINO.MATRICULA_CURSO_CODIGO_GENERATOR")
    private Long codigo;
	
	private Curso curso;
	
	private String ano;
	
	@Enumerated(EnumType.STRING)
    @NotNull(message = "Escolha uma catgoria")
    private Categoria categoria;
	
	@Column(name = "categoria_descr")
    private String categoriaDescr;
	
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
          
    @Size(min = 1, message = "Selecione ao menos um Estabelecimento de ensino")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "curso_om",				
				joinColumns = @JoinColumn(name = "codigo_curso"),
	 			inverseJoinColumns = @JoinColumn(name = "codigo_om"))
	private Set<OrganizacaoMilitar> oms;
    
    @ManyToOne
    @JoinColumn(name = "npor_codigo")
    private Npor npor;
    
    @ManyToOne
    @JoinColumn(name = "uete_codigo")
    private Uete uete;
    
    @Column(name = "data_inicio_curso")
    @NotNull(message = "A data de inicio do curso é obrigatória")
    private LocalDate dataInicioCurso;
    
    @Column(name = "data_final_curso")
    private LocalDate dataFinalCurso;
    
    @Max( value = 10L, message = "A nota de conteúdo atitudinal deve ser maior que 0,01 e menor ou igual a 10,00")
    @NotNull(message = "O Campo atitudinal obrigatório")
    @Column
    private BigDecimal atitudinal;
    
    @Max(value = 10L, message = "A nota de conteúdo atitudinal lateral deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column(name = "atitudinal_lateral")
    private BigDecimal atitudinalLateral;
    
    @Max(value = 10L, message = "A nota de conteúdo atitudinal vertical deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column(name = "atitudinal_vertical")
    private BigDecimal atitudinalVertical;
    
    @Max(value = 10L, message = "A nota do TFM deve ser maior que 0,01 e menor ou igual a 10,00")
    @NotNull(message = "O Campo último TFM é obrigatório")
    @Column
    private BigDecimal tfm;
    
    @Max(value = 10L, message = "A nota do TFM 2 deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column
    private BigDecimal tfm2;
    
    @Max(value = 10L, message = "A nota do TFM 3 deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column
    private BigDecimal tfm3;
    
    private BigDecimal notaTfm;
    
    @Column(name = "situacao_no_curso_descr")
    private String situacaoNoCursoDescr;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo período é obrigatório")    
    private Periodo periodo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getCategoriaDescr() {
		return categoriaDescr;
	}

	public void setCategoriaDescr(String categoriaDescr) {
		this.categoriaDescr = categoriaDescr;
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

	public Set<OrganizacaoMilitar> getOms() {
		return oms;
	}

	public void setOms(Set<OrganizacaoMilitar> oms) {
		this.oms = oms;
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

	public LocalDate getDataInicioCurso() {
		return dataInicioCurso;
	}

	public void setDataInicioCurso(LocalDate dataInicioCurso) {
		this.dataInicioCurso = dataInicioCurso;
	}

	public LocalDate getDataFinalCurso() {
		return dataFinalCurso;
	}

	public void setDataFinalCurso(LocalDate dataFinalCurso) {
		this.dataFinalCurso = dataFinalCurso;
	}

	public BigDecimal getAtitudinal() {
		return atitudinal;
	}

	public void setAtitudinal(BigDecimal atitudinal) {
		this.atitudinal = atitudinal;
	}

	public BigDecimal getAtitudinalLateral() {
		return atitudinalLateral;
	}

	public void setAtitudinalLateral(BigDecimal atitudinalLateral) {
		this.atitudinalLateral = atitudinalLateral;
	}

	public BigDecimal getAtitudinalVertical() {
		return atitudinalVertical;
	}

	public void setAtitudinalVertical(BigDecimal atitudinalVertical) {
		this.atitudinalVertical = atitudinalVertical;
	}

	public BigDecimal getTfm() {
		return tfm;
	}

	public void setTfm(BigDecimal tfm) {
		this.tfm = tfm;
	}

	public BigDecimal getTfm2() {
		return tfm2;
	}

	public void setTfm2(BigDecimal tfm2) {
		this.tfm2 = tfm2;
	}

	public BigDecimal getTfm3() {
		return tfm3;
	}

	public void setTfm3(BigDecimal tfm3) {
		this.tfm3 = tfm3;
	}
	

	public void setNotaTfm(BigDecimal notaTfm) {
		this.notaTfm = notaTfm;
	}

	public String getSituacaoNoCursoDescr() {
		return situacaoNoCursoDescr;
	}

	public void setSituacaoNoCursoDescr(String situacaoNoCursoDescr) {
		this.situacaoNoCursoDescr = situacaoNoCursoDescr;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	public BigDecimal getNotaTfm() {
		BigDecimal nota = getTfm().add(tfm2).add(tfm3);
		BigDecimal d = new BigDecimal("3");
		nota = nota.divide(d);
		return getNotaTfm();
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatriculaCurso other = (MatriculaCurso) obj;
		return Objects.equals(codigo, other.codigo);
	}

	
	
	
}
