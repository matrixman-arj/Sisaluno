package br.mil.eb.decex.sisaluno.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

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
	private String sku;/*MDEE*/
	
	private String modalidade;
		
    @NotNull(message = "Escolha um universo")
    private String universo;
    
    private String descricao;
	
	private String linha;
	
	private String vinculo;
	
	@Column(name = "vinculo_descr")
	private String vinculoDescr;
	
	@Column(name = "tipo_vinculo")
	private String tipoVinculo;
	
	@Column(name = "tipo_vinculo_descr")
	private String tipoVinculoDescr;
    
    private String foto;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Transient
	private boolean novaFoto;
	
	public String getFotoOuMock() {
		return !StringUtils.isEmpty(foto) ? foto : "divisa-mock.png";
	}
	
//	public String getOfOuSgt() {
//		return StringUtils.pathEquals("FORM_OF", getUniverso()) ? "patente-oficial.jpeg " : "divisa.png";
//	}
	
	public String getOfOuSgt() {
		if (getUniverso() == "FORM_OF") {
		 foto = "patente-oficial.jpeg";
		}if (getUniverso() == "FORM_GRAD_PCA")
		foto = "divisa.png";
		return foto;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(this.foto);
	}
	
	
    @PrePersist
    private void prePersist() {   	
    	sku = sku.toUpperCase();         
    }
    
    @PreUpdate
	private void preUpdate() {
		sku = sku.toUpperCase();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	

	public String getUniverso() {
		return universo;
	}

	public void setUniverso(String universo) {
		this.universo = universo;
	}	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
	public String getLinha() {
		return linha;
	}
	public void setLinha(String linha) {
		this.linha = linha;
	}
	
	

	public String getVinculo() {
		return vinculo;
	}

	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	public String getVinculoDescr() {
		return vinculoDescr;
	}

	public void setVinculoDescr(String vinculoDescr) {
		this.vinculoDescr = vinculoDescr;
	}

	public String getTipoVinculo() {
		return tipoVinculo;
	}

	public void setTipoVinculo(String tipoVinculo) {
		this.tipoVinculo = tipoVinculo;
	}

	public String getTipoVinculoDescr() {
		return tipoVinculoDescr;
	}

	public void setTipoVinculoDescr(String tipoVinculoDescr) {
		this.tipoVinculoDescr = tipoVinculoDescr;
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public boolean isNovo() {
        return this.codigo == null;
    }

    public boolean isEdicao() {
        return this.codigo != null;
    }

	@Override
	public int hashCode() {
		return Objects.hash(codigo, sku);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(sku, other.sku);
	}	
	
}
