package br.com.meli.anuncios.service.impl;

import br.com.meli.anuncios.dto.AnuncioDtoIn;
import br.com.meli.anuncios.dto.AnuncioDtoOut;
import br.com.meli.anuncios.dto.AnuncioDtoIn;
import br.com.meli.anuncios.dto.AnuncioDtoOut;
import br.com.meli.anuncios.entitites.Anuncio;
import br.com.meli.anuncios.exceptions.ResourceNotFoundException;
import br.com.meli.anuncios.repositories.AnuncioRepository;
import br.com.meli.anuncios.services.impl.AnuncioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@DisplayName("Anuncio service")
@ExtendWith(MockitoExtension.class)
public class AnuncioServiceImplTest {
    @InjectMocks
    private AnuncioServiceImpl anuncioService;
    @Mock
    private AnuncioRepository anuncioRepository;

    @Test
    @DisplayName("Quando create for chamado, deverá retornar um anuncioDto valido")
    void createTest(){
        AnuncioDtoOut anuncioDto = new AnuncioDtoOut(1L, "name");
        Anuncio anuncio = new Anuncio();
        anuncio.setName("name");

        Mockito.when(anuncioRepository.save(anuncio)).thenReturn(anuncio);

        AnuncioDtoOut result = anuncioService.create(new AnuncioDtoIn());

        Assertions.assertEquals(result.getName(), anuncioDto.getName());
        Mockito.verify(anuncioRepository, Mockito.times(1)).save(anuncio);
    }

    @Test
    @DisplayName("Quando findById for chamado, deverá retornar um anuncioDto valido")
    void findByIdTest () {
        Anuncio anuncioEntity = new Anuncio();
        anuncioEntity.setId(1L);
        anuncioEntity.setName("Cassio");

        Mockito.when(anuncioRepository.findById(1L)).thenReturn(Optional.of(anuncioEntity));
        AnuncioDtoOut resultDto = anuncioService.findById(1L);
        Assertions.assertEquals(resultDto.getId(), 1L);
        Mockito.verify(anuncioRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Quando findById for chamado, deverá retornar um error")
    void findByIdErrorTest () {
        Mockito.when(anuncioRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> anuncioService.findById(1L));
    }
}
