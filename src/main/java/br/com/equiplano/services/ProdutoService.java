package br.com.equiplano.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.equiplano.dto.CategoriaDTO;
import br.com.equiplano.dto.ProdutoDTO;
import br.com.equiplano.model.Produto;
import br.com.equiplano.repository.ProdutoRepository;

@RequestScoped
@Transactional(rollbackOn=Exception.class)
public class ProdutoService {

	@Inject
	private ProdutoRepository repository;
	
	@Inject
	private CategoriaService categoriaService;

	public void save(ProdutoDTO produtoDTO) {
		Produto produto = convertToModel(produtoDTO);
		this.repository.save(produto);
	}

	public void update(ProdutoDTO produtoDTO) {
		Produto produto = convertToModel(produtoDTO);
		this.repository.update(produto);
	}
	
	public void remove(ProdutoDTO produtoDTO) {
		Produto produto = convertToModel(produtoDTO);
		this.repository.remove(produto);
	}
	
	public ProdutoDTO findById(Long id) {

		Produto produto = this.repository.findById(id);
		ProdutoDTO produtoDTO = convertToDTO(produto);

		return produtoDTO;
	}
	
	public List<ProdutoDTO> findByDescricao(String descricao) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("descricao", "%" + descricao + "%");

		List<Produto> produtos = this.repository.findManyResults(Produto.FIND_BY_NAME, params);
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();

		for (Produto p : produtos) {
			ProdutoDTO produtoDTO = convertToDTO(p);
			produtosDTO.add(produtoDTO);
		}

		return produtosDTO;

	}

	public List<ProdutoDTO> findAll() {

		List<Produto> produtos = this.repository.findAll();
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();

		for (Produto p : produtos) {
			ProdutoDTO produtoDTO = convertToDTO(p);
			produtosDTO.add(produtoDTO);
		}

		return produtosDTO;

	}

	public Produto convertToModel(ProdutoDTO produtoDTO) {
		
		if(produtoDTO == null){
			return null;
		}

		Produto produto = new Produto();

		produto.setId(produtoDTO.getId());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setQuantidade(produtoDTO.getQuantidade());
		produto.setIdCategoria(produtoDTO.getIdCategoria());

		return produto;
	}

	public ProdutoDTO convertToDTO(Produto produto) {
		
		if(produto == null){
			return null;
		}

		ProdutoDTO produtoDTO = new ProdutoDTO();
		CategoriaDTO categoriaDTO = this.categoriaService.convertToDTO(produto.getCategoria());

		produtoDTO.setId(produto.getId());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setQuantidade(produto.getQuantidade());
		produtoDTO.setIdCategoria(produto.getIdCategoria());
		produtoDTO.setCategoriaDTO(categoriaDTO);

		return produtoDTO;
	}

}
