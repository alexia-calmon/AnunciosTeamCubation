package br.com.meli.anuncios.controllers;

import br.com.meli.anuncios.dto.AnuncioDtoIn;
import br.com.meli.anuncios.dto.AnuncioDtoOut;
import br.com.meli.anuncios.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnuncioDtoIn anuncioDto) {
        return new ResponseEntity<>(anuncioService.create(anuncioDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(anuncioService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<AnuncioDtoOut> dtos = anuncioService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AnuncioDtoIn anuncioDto) {
        AnuncioDtoOut returnDtoOut = anuncioService.update(anuncioDto,id);
        return ResponseEntity.ok(returnDtoOut);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        anuncioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
