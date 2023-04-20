package br.com.meli.anuncios.services.impl;

import br.com.meli.anuncios.dto.AnuncioDtoIn;
import br.com.meli.anuncios.dto.AnuncioDtoOut;
import br.com.meli.anuncios.entitites.Anuncio;
import br.com.meli.anuncios.exceptions.ResourceNotFoundException;
import br.com.meli.anuncios.repositories.AnuncioRepository;
import br.com.meli.anuncios.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnuncioServiceImpl implements AnuncioService {
    @Autowired
    private AnuncioRepository repository;

    @Override
    public AnuncioDtoOut create(AnuncioDtoIn anuncioDto) {
        Anuncio entity = new Anuncio();
        entity.setName(anuncioDto.getName());

        Anuncio savedEntity = repository.save(entity);

        AnuncioDtoOut returnDto = new AnuncioDtoOut();
        returnDto.setId(savedEntity.getId());
        returnDto.setName(savedEntity.getName());


        return returnDto;
    }


    @Override
    public AnuncioDtoOut findById(Long id) {
        Anuncio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio nao encontrado"));

        AnuncioDtoOut returnDto = new AnuncioDtoOut(entity.getId(), entity.getName());

        return returnDto;
    }

    @Override
    public List<AnuncioDtoOut> findAll() {
        List<Anuncio> entities = repository.findAll();

        return entities.stream().map(e -> new AnuncioDtoOut(e.getId(), e.getName())).collect(Collectors.toList());
    }


    @Override
    public AnuncioDtoOut update(AnuncioDtoIn anuncioDto, Long id) {
        Anuncio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio nao encontrado"));

        entity.setName(anuncioDto.getName());

        entity = repository.save(entity);

        return new AnuncioDtoOut(entity.getId(), entity.getName());
    }

    @Override
    public void delete(Long id) {

        Optional<Anuncio> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.delete(entity.get());
        }
        //Consultar se o id existe no banco.
        // Se existir vou deletar.


    }
}
