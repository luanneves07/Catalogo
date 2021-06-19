package com.luan.catalogo.service;

import java.util.List;

import com.luan.catalogo.model.Musica;

public interface CatalogoService {
    List<Musica> findAll();
    Musica findById(long id);
    Musica save(Musica musica);
    Musica update(Musica musica);
    void excluir(long id);  
}
