import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import models.AlocacaoNaoPermitidaException;
import models.Disciplina;
import models.Planejador;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MeuCursoTest {

	private Planejador planejador;
	private Disciplina p1;
	private Disciplina lp1;
	private Disciplina p2;
	private Disciplina calculo1;
	private Disciplina calculo2;
	@Before
	public void setUp() throws Exception {
		planejador = new Planejador();
		p1 = new Disciplina("P1", 4);
		lp1 = new Disciplina("LP1", 4);
		p2 = new Disciplina("P2", 4, new Disciplina[] { p1, lp1 });
		calculo1 = new Disciplina("Calculo 1", 4); 
		calculo2 = new Disciplina("Calculo 2", 4, new Disciplina[] {calculo1}); 
	}

	@Test
	public void deveIniciarComPrimeiroPeriodoAlocado() {
		Collection<Disciplina> esperadas = new LinkedList<Disciplina>();
		esperadas.add(p1);
		esperadas.add(lp1);
		esperadas.add(new Disciplina("Cálculo 1", 4));
		esperadas.add(new Disciplina("Vetorial ", 4));
		esperadas.add(new Disciplina("LPT", 4));
		esperadas.add(new Disciplina("IC", 4));

		Collection<Disciplina> iniciais = planejador
				.getDisciplinasAlocadasNoPeriodo(1);

		assertEquals(6, iniciais.size());
		for (Disciplina disciplina : esperadas) {
			assertTrue(iniciais.contains(disciplina));
		}

	}

	@Test
	public void deveAtualizarAlocacaoAoAdicionar()
			throws AlocacaoNaoPermitidaException {

		Collection<Disciplina> alocadas = planejador
				.getDisciplinasAlocadasNoPeriodo(2);
		assertTrue(alocadas.isEmpty());

		planejador.alocar(2, p2);

		alocadas = planejador.getDisciplinasAlocadasNoPeriodo(2);
		assertEquals(1, alocadas.size());
		assertTrue(alocadas.contains(p2));
	}

	@Test
	public void deveAtualizarTotalDeCreditos() throws Exception {
		assertEquals(24, planejador.getTotalCreditos(1));
		assertEquals(0, planejador.getTotalCreditos(2));

		planejador.alocar(2, p2);

		assertEquals(4, planejador.getTotalCreditos(2));
	}

	@Test
	public void naoDevePermitirAlteracaoNoPrimeiroPeriodo() throws Exception {
		try {
			planejador.alocar(1, p2);
			Assert.fail();
		} catch (AlocacaoNaoPermitidaException e) {
			assertEquals("Nenhuma alocacao pode ser feita no primeiro periodo",
					e.getMessage());
		}

	}

	@Test
	public void deveAtualizarAlocacaoAoRemover() throws AlocacaoNaoPermitidaException {
		
		planejador.alocar(2, p2);
		planejador.desalocar(2, p2);
		Assert.assertEquals(0, planejador.getTotalCreditos(2));
		
	}

	@Test
	public void deveRespeitarPreRequisitos() {
		try {
			planejador.alocar(3 , calculo2);
			Assert.fail();
		} catch (AlocacaoNaoPermitidaException e) {
			assertEquals("Quebra de pré-requisito.",
					e.getMessage());
		}
	}

	@Test
	public void deveRespeitarLimiteDeCreditos() {
		try {
			planejador.alocar(2, new Disciplina("Disciplina", 29));
			Assert.fail();
		} catch (AlocacaoNaoPermitidaException e) {
			assertEquals("Ultrapassou o limite de créditos.",
					e.getMessage());
		}
	}

}
