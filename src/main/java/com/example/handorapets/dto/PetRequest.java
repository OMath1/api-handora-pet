package com.example.handorapets.dto;


import com.example.handorapets.model.Pet;
import com.example.handorapets.model.TipoPetEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {

    @Size(max = 146)
    @NotBlank(message = "O nome do Pet e obrigatorio :)")
    private String nome;

    @NotNull(message = "A data nao pode estar vazia")
    @PastOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;

    @NotNull(message = "O tipo do seu Pet e obrigatorio :)")
    private TipoPetEnum tipoPetEnum;

    @NotBlank(message = "A raça do seu Pet e obrigatoria :)")
    private String raca;

    public Pet paraPet() {
        return new Pet(this.nome, this.dataDeNascimento, this.tipoPetEnum, this.raca);
    }

    public static List<PetRequest> topicar(List<Pet> topico) {
        // Lista de topicos recebida no param
        return topico
                // retornando o fluxo de dados sem precisar iterar manualmente
                .stream()
                // mapeando o fluxo de TopicoDto vindo do topico
                .map(PetRequest::new)
                // transforma em uma collection e passa pra lista
                .collect(Collectors.toList());
    }

    public PetRequest(Pet pet) {
        this.nome = pet.getNome();
        this.dataDeNascimento = pet.getDataDeNascimento();
        this.tipoPetEnum = pet.getTipoPetEnum();
        this.raca = pet.getRaca();
    }
}
