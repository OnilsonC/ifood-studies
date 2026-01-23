package br.com.onilson.cadastro;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "prato")
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @ManyToOne
    private Restaurante restaurante;
}
