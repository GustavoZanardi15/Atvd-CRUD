package com.example.game.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    private int level;

    private int forcaBase;
    private int defesaBase;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMagico> itens = new ArrayList<>();

    // Regras de negócio

    public int getForcaTotal() {
        return forcaBase + itens.stream().mapToInt(ItemMagico::getForca).sum();
    }

    public int getDefesaTotal() {
        return defesaBase + itens.stream().mapToInt(ItemMagico::getDefesa).sum();
    }

    public boolean possuiAmuleto() {
        return itens.stream().anyMatch(item -> item.getTipo() == TipoItem.AMULETO);
    }

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNomeAventureiro() { return nomeAventureiro; }
    public void setNomeAventureiro(String nomeAventureiro) { this.nomeAventureiro = nomeAventureiro; }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getForcaBase() { return forcaBase; }
    public void setForcaBase(int forcaBase) { this.forcaBase = forcaBase; }

    public int getDefesaBase() { return defesaBase; }
    public void setDefesaBase(int defesaBase) { this.defesaBase = defesaBase; }

    public List<ItemMagico> getItens() { return itens; }
    public void setItens(List<ItemMagico> itens) { this.itens = itens; }
}
