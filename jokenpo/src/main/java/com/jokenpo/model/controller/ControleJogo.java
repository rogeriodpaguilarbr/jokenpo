package com.jokenpo.model.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.jokenpo.model.Jogada;
import com.jokenpo.model.Jogador;
import com.jokenpo.model.TipoDeJogada;
import com.jokenpo.model.excessao.JogadaNaoInformadaExcecao;
import com.jokenpo.model.excessao.JogadorNaoEstaNoJogoExcecao;

public class ControleJogo {

	private List<Jogador> listaDeJogadores = new ArrayList<>();
	private final Semaphore available = new Semaphore(1);
	private List<Jogada> listaDeJogadas = new LinkedList<>();

	public ControleJogo() {
	}

	public List<Jogada> getListaDeJogadas() {
		try {
			available.acquire();
			return Collections.unmodifiableList(listaDeJogadas);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			available.release();
		}
	}
	
	
	public void adicionarJogada(Jogador jogador, TipoDeJogada tipoDeJogada)
			throws JogadorNaoEstaNoJogoExcecao, JogadaNaoInformadaExcecao {
		try {
			available.acquire();
			listaDeJogadas.add(new Jogada(jogador, tipoDeJogada));
			listaDeJogadores.add(jogador);
			validarJogada(jogador, tipoDeJogada);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			available.release();
		}
	}

	private void validarJogada(Jogador jogador, TipoDeJogada tipoDeJogada)
			throws JogadorNaoEstaNoJogoExcecao, JogadaNaoInformadaExcecao {
		if (!listaDeJogadores.isEmpty() && !listaDeJogadores.contains(jogador)) {
			throw new JogadorNaoEstaNoJogoExcecao("Jogador " + jogador + " não está no jogo!");
		} else if (tipoDeJogada == null) {
			throw new JogadaNaoInformadaExcecao("O tipo de Jogada deve ser informada!");
		}
	}

	public Jogada verificarJogadaVencedora() {
		//Teste de erro if(true)throw new IllegalStateException("Teste de erro");
		Jogada jogadaVencedora = null;
		try {
			available.acquire();
			if(listaDeJogadas.isEmpty()) {
				throw new IllegalStateException("Não foi realizada nenhuma jogada!");
			} else if(listaDeJogadas.size() == 1){
				throw new IllegalStateException("Foi realizada uma jogada apenas!");
			} else {	
				int contador = 0;
				List<Jogada> listaDeJogadasTMP = new LinkedList<Jogada>(listaDeJogadas);
				Jogada proximaJogada = null;
				for(Jogada jogadaAtual : listaDeJogadas) {
					if(contador +1 < listaDeJogadasTMP.size()) {
						proximaJogada = listaDeJogadasTMP.get(contador+1);
						if(jogadaVencedora == null) {
							if(jogadaAtual.getTipoDeJogada().ganhaDe(proximaJogada.getTipoDeJogada().getTipoDeJogadaEnum())){
								jogadaVencedora = jogadaAtual;
							}				
						}
					}
					contador++;
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			available.release();
		}
		
		
		return jogadaVencedora;
	}

	public static ControleJogo newInstance() {
		return new ControleJogo();
	}

}
