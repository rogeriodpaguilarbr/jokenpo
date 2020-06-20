package com.jokenpo.resoure;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jokenpo.model.Jogador;
import com.jokenpo.model.controller.ControleJogo;
import com.jokenpo.model.interfaces.GerenciadorJogadoresInterface;

@RestController
@EnableAutoConfiguration	
@RequestMapping("/jogador")
public class JogadorResource {

	private GerenciadorJogadoresInterface gereniadorJogadores;

	@Autowired
	public JogadorResource(GerenciadorJogadoresInterface gereniadorJogadores) {
		super();
		this.gereniadorJogadores = gereniadorJogadores;
	}

	@GetMapping("/consultar/{uuid}")
	public Jogador procurarJogador(@PathVariable("uuid") String uuid) throws Exception{
		Jogador jogador = gereniadorJogadores.consultar(UUID.fromString(uuid));
		if(jogador == null) {
			throw new Exception("Jogador não encontrado");
		}
		return jogador;
	}
	
	@GetMapping("/remover/{uuid}")
	public Jogador removerJogador(@PathVariable("uuid") String uuid) {
		return gereniadorJogadores.remover(UUID.fromString(uuid));
	}
	
	@GetMapping("/listar")
	public List<Jogador> Jogador() {
		return gereniadorJogadores.listar();
	}
 
	@PostMapping("/adicionar")
	public @ResponseBody Jogador adicionarJogador(@RequestBody String nome) {
		Jogador jogador =  gereniadorJogadores.adicionar(nome);
		return jogador;
	}

	
	
	public static void main(String[] args) {
		SpringApplication.run(new Class[] { JogadorResource.class, JogadaResource.class, GerenciadorJogadores.class, ControleJogo.class }, args);
	}

}
