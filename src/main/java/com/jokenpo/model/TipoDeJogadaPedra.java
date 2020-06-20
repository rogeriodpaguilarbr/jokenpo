package com.jokenpo.model;

import com.jokenpo.model.constantes.TiposDeJogadaEnum;

/**	
 * @author Rogério de Paula Aguilar
 */
public class TipoDeJogadaPedra extends TipoDeJogada{
	
	private static final long serialVersionUID = 6577850319132567569L;

	public TipoDeJogadaPedra() {
		super(TiposDeJogadaEnum.PEDRA);
	}
	
	public boolean ganhaDe (TiposDeJogadaEnum tipoDeJogada) {
		return TiposDeJogadaEnum.LAGARTO.equals(tipoDeJogada) || TiposDeJogadaEnum.TESOURA.equals(tipoDeJogada);
	}
	
	

}
