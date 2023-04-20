package br.com.meli.anuncios.services;

import br.com.meli.anuncios.dto.AnuncioDtoIn;
import br.com.meli.anuncios.dto.AnuncioDtoOut;

import java.util.List;

public interface AnuncioService {
    AnuncioDtoOut create(AnuncioDtoIn anuncioDtoIn);


    AnuncioDtoOut findById(Long id);

    List<AnuncioDtoOut> findAll();

    AnuncioDtoOut update(AnuncioDtoIn anuncioDto, Long id );

    void delete(Long id);
}
