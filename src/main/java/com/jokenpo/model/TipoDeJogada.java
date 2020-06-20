package com.jokenpo.model;
	
import java.io.Serializable;

import com.jokenpo.model.constantes.TiposDeJogadaEnum;

/**
 * @author Rogério de Paula Aguilar
 */	
@SuppressWarnings("serial")
public abstract class TipoDeJogada implements Serializable{

	private TiposDeJogadaEnum tipoDeJogadaEnum;

	public TiposDeJogadaEnum getTipoDeJogadaEnum() {	
		return tipoDeJogadaEnum;
	}

	
	public abstract boolean ganhaDe (TiposDeJogadaEnum tipoDeJogada);
	
	public TipoDeJogada(TiposDeJogadaEnum tipoDeJogada) {
		this.tipoDeJogadaEnum = tipoDeJogada;
	}

	@Override
	public String toString() {
		return "TipoDeJogada [tipoDeJogada=" + tipoDeJogadaEnum + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipoDeJogadaEnum == null) ? 0 : tipoDeJogadaEnum.hashCode());
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
		TipoDeJogada other = (TipoDeJogada) obj;
		if (tipoDeJogadaEnum != other.tipoDeJogadaEnum)
			return false;
		return true;
	}

	
	
}
