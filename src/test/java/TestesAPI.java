
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.jokenpo.model.Jogada;
import com.jokenpo.model.Jogador;
import com.jokenpo.model.TipoDeJogadaPapel;
import com.jokenpo.model.TipoDeJogadaPedra;
import com.jokenpo.model.TipoDeJogadaTesoura;
import com.jokenpo.model.constantes.TiposDeJogadaEnum;
import com.jokenpo.model.controller.ControleJogo;
import com.jokenpo.model.excessao.JogadaNaoInformadaExcecao;
import com.jokenpo.model.excessao.JogadorNaoEstaNoJogoExcecao;
import com.jokenpo.model.interfaces.GerenciadorJogadoresInterface;
import com.jokenpo.resoure.GerenciadorJogadores;

public class TestesAPI {

	protected Jogador jogadorUm = null;
	protected Jogador jogadorDois = null;
	protected Jogador jogadorTres = null;
	protected GerenciadorJogadoresInterface gerenciadorJogadores = null;

	@Before
	public void setUp() throws Exception {
		jogadorUm = new Jogador("Jogador UM", UUID.randomUUID());
		jogadorDois = new Jogador("Jogador DOIS", UUID.randomUUID());
		jogadorTres = new Jogador("Jogdor TRÊS", UUID.randomUUID());
		gerenciadorJogadores = new GerenciadorJogadores();		
	}

	@Test
	public void testarInclusaoUsuario()
	{
		try {
			setUp();
			Jogador jogador = gerenciadorJogadores.adicionar("José da Silva");
			verificarObjJogadorPreenchido(jogador);
			jogador = gerenciadorJogadores.consultar(jogador.getId());
			assertNotNull(jogador);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
		
	}
	
	@Test
	public void testarExclusaoUsuario()
	{
		try {
			setUp();
			Jogador jogador = gerenciadorJogadores.adicionar("José da Silva");
			verificarObjJogadorPreenchido(jogador);
			jogador = gerenciadorJogadores.remover(jogador.getId());
			verificarObjJogadorPreenchido(jogador);
			jogador = gerenciadorJogadores.consultar(jogador.getId());
			assertNull(jogador);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
		
	}

	@Test
	public void testarListarUsuarios()
	{
		try {
			setUp();
			for(int i = 0; i < 10; i++) {
				Jogador jogador = gerenciadorJogadores.adicionar("Teste José " + i);
				verificarObjJogadorPreenchido(jogador);
				Jogador jogadorAdicionado = gerenciadorJogadores.consultar(jogador.getId());
				verificarObjJogadorPreenchido(jogadorAdicionado);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
		
	}

	private void verificarObjJogadorPreenchido(Jogador jogador) {
		assertNotNull(jogador);
		assertNotNull(jogador.getId());
	}

	
	
	@Test
	public void testarJogadaDoExemplo() {

		ControleJogo controleJogo = new ControleJogo();
		try {
			controleJogo.adicionarJogada(jogadorUm, new TipoDeJogadaPedra());
			controleJogo.adicionarJogada(jogadorDois, new TipoDeJogadaTesoura());
			controleJogo.adicionarJogada(jogadorTres, new TipoDeJogadaTesoura());
			Jogada jogadaVencedora = controleJogo.verificarJogadaVencedora();
			System.out.println(jogadaVencedora);
			assertNotNull(jogadaVencedora);
			assertNotNull(jogadaVencedora.getTipoDeJogada());
			assertNotNull(jogadaVencedora.getTipoDeJogada().getTipoDeJogadaEnum());
			assertEquals(TiposDeJogadaEnum.PEDRA, jogadaVencedora.getTipoDeJogada().getTipoDeJogadaEnum());
		} catch (JogadorNaoEstaNoJogoExcecao | JogadaNaoInformadaExcecao e) {
			throw new IllegalStateException(e);
		}
	}

	@Test
	public void testarJogadaPapelPedra() {
		ControleJogo controleJogo = new ControleJogo();
		try {
			controleJogo.adicionarJogada(jogadorUm, new TipoDeJogadaPapel());
			controleJogo.adicionarJogada(jogadorDois, new TipoDeJogadaPedra());
			Jogada jogadaVencedora = controleJogo.verificarJogadaVencedora();
			System.out.println(jogadaVencedora);
			assertNotNull(jogadaVencedora);
			assertNotNull(jogadaVencedora.getTipoDeJogada());
			assertNotNull(jogadaVencedora.getTipoDeJogada().getTipoDeJogadaEnum());
			assertEquals(TiposDeJogadaEnum.PAPEL, jogadaVencedora.getTipoDeJogada().getTipoDeJogadaEnum());
		} catch (JogadorNaoEstaNoJogoExcecao | JogadaNaoInformadaExcecao e) {
			throw new IllegalStateException(e);
		}
	}
	
	
}
