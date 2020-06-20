package com.jokenpo.model;

import com.jokenpo.model.constantes.TiposDeJogadaEnum;

/**
 * @author Rogério de Paula Aguilar	
 */
public class TipoDeJogadaSpock extends TipoDeJogada{
	
	private static final long serialVersionUID = 8675426768694419072L;

	public TipoDeJogadaSpock() {
		super(TiposDeJogadaEnum.SPOCK);
	}
	
	public boolean ganhaDe (TiposDeJogadaEnum tipoDeJogada) {
		return TiposDeJogadaEnum.PEDRA.equals(tipoDeJogada) || TiposDeJogadaEnum.TESOURA.equals(tipoDeJogada);
	}

}
