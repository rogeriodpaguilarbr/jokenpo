package com.jokenpo.model;

import java.io.Serializable;

public class Jogada implements Serializable{
	
	private static final long serialVersionUID = 615526290859104921L;
	private Jogador jogador;
	private TipoDeJogada tipoDeJogada;

	
	
	public Jogada(Jogador jogador, TipoDeJogada tipoDeJogada) {
		super();
		this.jogador = jogador;
		this.tipoDeJogada = tipoDeJogada;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public TipoDeJogada getTipoDeJogada() {
		return tipoDeJogada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jogador == null) ? 0 : jogador.hashCode());
		result = prime * result + ((tipoDeJogada == null) ? 0 : tipoDeJogada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogada other = (Jogada) obj;
		if (jogador == null) {
			if (other.jogador != null)
				return false;
		} else if (!jogador.equals(other.jogador))
			return false;
		if (tipoDeJogada == null) {
			if (other.tipoDeJogada != null)
				return false;
		} else if (!tipoDeJogada.equals(other.tipoDeJogada))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Jogada [jogador=" + jogador + ", tipoDeJogada=" + tipoDeJogada + "]";
	}
	
	
}
