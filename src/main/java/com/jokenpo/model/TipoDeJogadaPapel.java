package com.jokenpo.model;

import com.jokenpo.model.constantes.TiposDeJogadaEnum;

/**
 * @author Rogério de Paula Aguilar
 */
public class TipoDeJogadaPapel extends TipoDeJogada{
	
	private static final long serialVersionUID = 8675426768694419072L;
	
	public TipoDeJogadaPapel() {
		super(TiposDeJogadaEnum.PAPEL);
	}
	
	public boolean ganhaDe (TiposDeJogadaEnum tipoDeJogada) {
		return TiposDeJogadaEnum.PEDRA.equals(tipoDeJogada) || TiposDeJogadaEnum.SPOCK.equals(tipoDeJogada);
	}

	

}
