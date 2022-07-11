package com.example.handorapets.controller;

import com.example.handorapets.dto.PetRequest;
import com.example.handorapets.model.Pet;
import com.example.handorapets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class ListarPetController {
    @Autowired
    private PetRepository petRepository;

    @GetMapping
    public List<PetRequest> lista() {
        List<Pet> pet = petRepository.findAll();

        return PetRequest.topicar(pet);
    }
}
