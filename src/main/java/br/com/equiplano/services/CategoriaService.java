package br.com.equiplano.services;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import br.com.equiplano.dto.CategoriaDTO;
import br.com.equiplano.model.Categoria;
import br.com.equiplano.repository.CategoriaRepository;

@RequestScoped
@Transactional(rollbackOn=Exception.class)
public class CategoriaService {

	@Inject
	private CategoriaRepository repository;

	public void save(CategoriaDTO categoriaDTO) {
		Categoria categoria = convertToModel(categoriaDTO);
		this.repository.save(categoria);
	}

	public void update(CategoriaDTO categoriaDTO) {
		Categoria categoria = convertToModel(categoriaDTO);
		this.repository.update(categoria);
	}
	
	public void remove(CategoriaDTO categoriaDTO) {
		Categoria categoria = convertToModel(categoriaDTO);
		this.repository.remove(categoria);
	}
	
	public CategoriaDTO findById(Long id) {

		Categoria categoria = this.repository.findById(id);
		CategoriaDTO categoriaDTO = convertToDTO(categoria);

		return categoriaDTO;
	}
	
	public List<CategoriaDTO> findAll() {

		List<Categoria> categorias = this.repository.findAll();
		List<CategoriaDTO> categoriasDTO = new ArrayList<CategoriaDTO>();

		for (Categoria p : categorias) {
			CategoriaDTO categoriaDTO = convertToDTO(p);
			categoriasDTO.add(categoriaDTO);
		}

		return categoriasDTO;

	}

	public Categoria convertToModel(CategoriaDTO categoriaDTO) {
		
		if(categoriaDTO == null){
			return null;
		}

		Categoria categoria = new Categoria();

		categoria.setId(categoriaDTO.getId());
		categoria.setDescricao(categoriaDTO.getDescricao());

		return categoria;
	}

	public CategoriaDTO convertToDTO(Categoria categoria) {
		
		if(categoria == null){
			return null;
		}

		CategoriaDTO categoriaDTO = new CategoriaDTO();

		categoriaDTO.setId(categoria.getId());
		categoriaDTO.setDescricao(categoria.getDescricao());

		return categoriaDTO;
	}

}
