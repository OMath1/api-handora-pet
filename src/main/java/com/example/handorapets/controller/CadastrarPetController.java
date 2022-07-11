package com.example.handorapets.controller;

import com.example.handorapets.dto.PetRequest;
import com.example.handorapets.model.Pet;
import com.example.handorapets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/pets")
public class CadastrarPetController {

    @Autowired
    private PetRepository petRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // @Valid dispara as validaçoes do DTO/PetRequest
    public ResponseEntity<PetRequest> cadastrar(@RequestBody @Valid PetRequest requestDto, UriComponentsBuilder uriComponentsBuilder) {
        Pet novoPet = requestDto.paraPet();
        petRepository.save(novoPet);


        URI location = uriComponentsBuilder.path("/pets/{id}")
                .buildAndExpand(novoPet.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
