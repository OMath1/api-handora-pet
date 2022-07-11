package com.example.handorapets.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@RequiredArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(length = 146, nullable = false)
    private String nome;

    @NonNull
    @Column(nullable = false)
    private LocalDate dataDeNascimento;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPetEnum tipoPetEnum;

    @NonNull
    @Column(nullable = false)
    private String raca;


    public Pet() {
    }
}
