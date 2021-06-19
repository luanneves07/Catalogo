package com.luan.catalogo.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.catalogo.model.Musica;
import com.luan.catalogo.repository.CatalogoRepository;
import com.luan.catalogo.service.CatalogoService;

@Service
public class CatalogoServiceImpl implements CatalogoService {

	@Autowired
	CatalogoRepository catalogoRepository;

	@Override
	public List<Musica> findAll() {
		return catalogoRepository.findAll();
	}

	@Override
	public Musica findById(long id) {
		return catalogoRepository.findById(id).get();
	}

	@Override
	public Musica save(Musica musica) {

		return catalogoRepository.save(musica);
	}

	@Override
	public void excluir(long id) {
		catalogoRepository.deleteById(id);

	}

	@Override
	public Musica update(Musica musica) {
		Musica foundMusic = catalogoRepository.findById(musica.getId()).get();
		foundMusic.setAutor(musica.getAutor());
		foundMusic.setLetra(musica.getLetra());
		foundMusic.setTitulo(musica.getTitulo());
		return catalogoRepository.save(foundMusic);
	}

}
