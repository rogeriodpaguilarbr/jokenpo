package com.jokenpo.resoure;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jokenpo.model.Jogador;
import com.jokenpo.model.interfaces.GerenciadorJogadoresInterface;

@Service
public class GerenciadorJogadores implements GerenciadorJogadoresInterface {
		
	private static Map<UUID,Jogador> mapaJogadores = new LinkedHashMap<>();			

	//TODO - retirar, apenas para testes
	static {
		for(int i = 0; i < 10; i++) {
			Jogador jogador = new Jogador("Teste Nome " + i, UUID.randomUUID());
			mapaJogadores.put(jogador.getId(), jogador);
		}
		
	}
	@Override
	public synchronized Jogador adicionar(String nome) {
		Jogador jogador = new Jogador(nome, UUID.randomUUID());
		mapaJogadores.put(jogador.getId(), jogador);
		return jogador;
	}

	@Override
	public synchronized Jogador remover(UUID id) {
		return mapaJogadores.remove(id);
	}
	
	@Override
	public synchronized Jogador consultar(UUID id) {
		return mapaJogadores.get(id);
	}
	
	@Override
	public synchronized List<Jogador> listar() {
		List<Jogador> listaJogadores = new LinkedList<>();
		for(Map.Entry<UUID, Jogador> entradaJogador : mapaJogadores.entrySet()) {
			listaJogadores.add(entradaJogador.getValue());
		}
		return listaJogadores;
	}
	
}
