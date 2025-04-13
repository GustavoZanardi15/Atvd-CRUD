package com.example.game.controller;

import com.example.game.model.ItemMagico;
import com.example.game.model.Personagem;
import com.example.game.service.PersonagemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    private final PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService) {
        this.personagemService = personagemService;
    }

    @PostMapping
    public Personagem criar(@RequestBody Personagem personagem) {
        return personagemService.criarPersonagem(personagem);
    }

    @GetMapping
    public List<Personagem> listar() {
        return personagemService.listarTodos();
    }

    @GetMapping("/{id}")
    public Personagem buscarPorId(@PathVariable Long id) {
        return personagemService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    }

    @PutMapping("/{id}/nome-aventureiro")
    public Personagem atualizarNome(@PathVariable Long id, @RequestBody String novoNome) {
        return personagemService.atualizarNomeAventureiro(id, novoNome);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        personagemService.removerPersonagem(id);
    }

    @PostMapping("/{id}/itens")
    public ItemMagico adicionarItem(@PathVariable Long id, @RequestBody ItemMagico item) {
        return personagemService.adicionarItem(id, item);
    }

    @GetMapping("/{id}/itens")
    public List<ItemMagico> listarItens(@PathVariable Long id) {
        return personagemService.listarItensDoPersonagem(id);
    }

    @DeleteMapping("/itens/{itemId}")
    public void removerItem(@PathVariable Long itemId) {
        personagemService.removerItemDoPersonagem(itemId);
    }

    @GetMapping("/{id}/amuleto")
    public ItemMagico buscarAmuleto(@PathVariable Long id) {
        return personagemService.buscarAmuletoDoPersonagem(id);
    }
}
