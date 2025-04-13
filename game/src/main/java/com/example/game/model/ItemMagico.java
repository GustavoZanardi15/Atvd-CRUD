package com.example.game.model;

import jakarta.persistence.*;

@Entity
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    private int forca;
    private int defesa;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    @PrePersist
    @PreUpdate
    private void validarItem() {
        if (forca < 0 || forca > 10 || defesa < 0 || defesa > 10) {
            throw new IllegalArgumentException("Força e defesa devem estar entre 0 e 10");
        }

        if (forca == 0 && defesa == 0) {
            throw new IllegalArgumentException("Item deve ter pelo menos força ou defesa");
        }

        if (tipo == TipoItem.ARMA && defesa != 0) {
            throw new IllegalArgumentException("Arma deve ter defesa igual a 0");
        }

        if (tipo == TipoItem.ARMADURA && forca != 0) {
            throw new IllegalArgumentException("Armadura deve ter força igual a 0");
        }
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoItem getTipo() { return tipo; }
    public void setTipo(TipoItem tipo) { this.tipo = tipo; }

    public int getForca() { return forca; }
    public void setForca(int forca) { this.forca = forca; }

    public int getDefesa() { return defesa; }
    public void setDefesa(int defesa) { this.defesa = defesa; }

    public Personagem getPersonagem() { return personagem; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }
}
