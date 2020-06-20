package com.jokenpo.model.interfaces;

import java.util.List;
import java.util.UUID;

import com.jokenpo.model.Jogador;

public interface GerenciadorJogadoresInterface {

	Jogador adicionar(String nome);

	Jogador remover(UUID id);

	Jogador consultar(UUID id);

	List<Jogador> listar();

}