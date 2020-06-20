package com.jokenpo.model;

import com.jokenpo.model.constantes.TiposDeJogadaEnum;

/**
 * @author Rogério de Paula Aguilar
 */
public class TipoDeJogadaTesoura extends TipoDeJogada{
	
	private static final long serialVersionUID = -6584133041892251533L;

	public TipoDeJogadaTesoura() {
		super(TiposDeJogadaEnum.TESOURA);
	}
	
	public boolean ganhaDe (TiposDeJogadaEnum tipoDeJogada) {
		return TiposDeJogadaEnum.PAPEL.equals(tipoDeJogada) || TiposDeJogadaEnum.LAGARTO.equals(tipoDeJogada);
	}

	

}
