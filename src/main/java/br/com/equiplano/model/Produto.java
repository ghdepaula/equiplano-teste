package br.com.equiplano.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="produto")
@NamedQueries({
	@NamedQuery(name="Produto.findByName", query="SELECT p FROM Produto p WHERE p.descricao LIKE :descricao")
})
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_NAME = "Produto.findByName";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String descricao;
	
	@Column
	private Integer quantidade;
	
	@Column
	private Long idCategoria;
	
	@ManyToOne
	@JoinColumn(name="idCategoria", referencedColumnName="id", insertable=false, updatable=false)
	private Categoria categoria;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
