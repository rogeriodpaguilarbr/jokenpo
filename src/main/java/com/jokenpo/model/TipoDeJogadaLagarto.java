package com.jokenpo.model;

import com.jokenpo.model.constantes.TiposDeJogadaEnum;

/**
 * @author Rogério de Paula Aguilar
 */

public class TipoDeJogadaLagarto extends TipoDeJogada{
	
	private static final long serialVersionUID = -3510490781459841992L;

	public TipoDeJogadaLagarto() {
		super(TiposDeJogadaEnum.LAGARTO);
	}
	
	public boolean ganhaDe (TiposDeJogadaEnum tipoDeJogada) {
		return TiposDeJogadaEnum.SPOCK.equals(tipoDeJogada) || TiposDeJogadaEnum.PAPEL.equals(tipoDeJogada);
	}
	

}
