package com.jokenpo.resoure;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jokenpo.model.Jogada;
import com.jokenpo.model.Jogador;
import com.jokenpo.model.TipoDeJogada;
import com.jokenpo.model.TipoDeJogadaLagarto;
import com.jokenpo.model.TipoDeJogadaPapel;
import com.jokenpo.model.TipoDeJogadaPedra;
import com.jokenpo.model.TipoDeJogadaSpock;
import com.jokenpo.model.TipoDeJogadaTesoura;
import com.jokenpo.model.constantes.TiposDeJogadaEnum;
import com.jokenpo.model.controller.ControleJogo;
import com.jokenpo.model.excessao.JogadaNaoInformadaExcecao;
import com.jokenpo.model.excessao.JogadorNaoEstaNoJogoExcecao;
import com.jokenpo.model.interfaces.GerenciadorJogadoresInterface;

@RestController
@RequestMapping("/jogada")
public class JogadaResource {

	private static ControleJogo controleJogo = ControleJogo.newInstance();
	
	@Autowired	
	private GerenciadorJogadoresInterface gerenciadorJogadores;

	@GetMapping("/teste")
	public String teste() {
		return "Teste " + gerenciadorJogadores + " " + controleJogo;
	}
	
	
	@GetMapping("/adicionar/{uuid}/{tipodejogada}")
	public List<Jogada> adicionarJogada(@PathVariable("uuid") String uuid, @PathVariable("tipodejogada") String tipoDeJogadaParametro) {
		
		UUID id = UUID.fromString(uuid);
		Jogador jogador = gerenciadorJogadores.consultar(id);
		TipoDeJogada tipoDeJogada = verificcarTipoDeJagada(tipoDeJogadaParametro);
		List<Jogada> jogadas = null;
		
		try {
			controleJogo.adicionarJogada(jogador, tipoDeJogada);
			jogadas = controleJogo.getListaDeJogadas();
		} catch (JogadorNaoEstaNoJogoExcecao e) {
			e.printStackTrace();
		} catch (JogadaNaoInformadaExcecao e) {
			e.printStackTrace();
		}
		
		if(jogadas == null) {
			jogadas = Collections.<Jogada>emptyList();
		}
		
		return jogadas;

	}
	
	@GetMapping("/verificarvencedor")
	public Jogada verificarVenccedor() {
		Jogada jogadaVencedora = null;
		jogadaVencedora = controleJogo.verificarJogadaVencedora();
		return jogadaVencedora;
	}

	

	private TipoDeJogada verificcarTipoDeJagada(String tipoDeJogadaParametro) {
		TipoDeJogada tipoDeJogada = null;
		
		if(TiposDeJogadaEnum.LAGARTO.name().equals(tipoDeJogadaParametro)) {
			tipoDeJogada = new TipoDeJogadaLagarto();
		} else if(TiposDeJogadaEnum.TESOURA.name().equals(tipoDeJogadaParametro)) {
			tipoDeJogada = new TipoDeJogadaTesoura();
		} else if(TiposDeJogadaEnum.PAPEL.name().equals(tipoDeJogadaParametro)) {
			tipoDeJogada = new TipoDeJogadaPapel();
		} else if(TiposDeJogadaEnum.PEDRA.name().equals(tipoDeJogadaParametro)) {
			tipoDeJogada = new TipoDeJogadaPedra();
		} else if(TiposDeJogadaEnum.SPOCK.name().equals(tipoDeJogadaParametro)) {
			tipoDeJogada = new TipoDeJogadaSpock();
		} else {
			throw new IllegalArgumentException("Tipo de Jogada inválida: " + tipoDeJogadaParametro);
		}
		return tipoDeJogada;
	}

	
}
	