package com.luan.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luan.catalogo.model.Musica;

public interface CatalogoRepository extends JpaRepository<Musica, Long> {

    
}
