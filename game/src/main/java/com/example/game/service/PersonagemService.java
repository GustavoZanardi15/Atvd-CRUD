package com.example.game.service;

import com.example.game.model.ItemMagico;
import com.example.game.model.Personagem;
import com.example.game.model.TipoItem;
import com.example.game.repository.ItemMagicoRepository;
import com.example.game.repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private final ItemMagicoRepository itemMagicoRepository;

    public PersonagemService(PersonagemRepository personagemRepository, ItemMagicoRepository itemMagicoRepository) {
        this.personagemRepository = personagemRepository;
        this.itemMagicoRepository = itemMagicoRepository;
    }

    public List<Personagem> listarTodos() {
        return personagemRepository.findAll();
    }

    public Optional<Personagem> buscarPorId(Long id) {
        return personagemRepository.findById(id);
    }

    public Personagem criarPersonagem(Personagem personagem) {
        int total = personagem.getForcaBase() + personagem.getDefesaBase();
        if (total > 10) {
            throw new IllegalArgumentException("Força e Defesa base devem somar no máximo 10 pontos");
        }
        return personagemRepository.save(personagem);
    }

    public Personagem atualizarNomeAventureiro(Long id, String novoNome) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        personagem.setNomeAventureiro(novoNome);
        return personagemRepository.save(personagem);
    }

    public void removerPersonagem(Long id) {
        personagemRepository.deleteById(id);
    }

    public ItemMagico adicionarItem(Long personagemId, ItemMagico item) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        if (item.getTipo() == TipoItem.AMULETO && personagem.possuiAmuleto()) {
            throw new RuntimeException("Personagem já possui um amuleto");
        }

        item.setPersonagem(personagem);
        return itemMagicoRepository.save(item);
    }

    public List<ItemMagico> listarItensDoPersonagem(Long personagemId) {
        return itemMagicoRepository.findByPersonagemId(personagemId);
    }

    public void removerItemDoPersonagem(Long itemId) {
        itemMagicoRepository.deleteById(itemId);
    }

    public ItemMagico buscarAmuletoDoPersonagem(Long personagemId) {
        return itemMagicoRepository.findByPersonagemId(personagemId).stream()
                .filter(item -> item.getTipo() == TipoItem.AMULETO)
                .findFirst()
                .orElse(null);
    }
}
