import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import models.Disciplina;
import models.Planejador;

import org.junit.Before;
import org.junit.Test;

public class MeuCursoTest {

	private Planejador planejador;

	@Before
	public void setUp() throws Exception {
		planejador = new Planejador();
	}

	@Test
	public void deveIniciarComPrimeiroPeriodoAlocado() {
		Collection<Disciplina> esperadas = new LinkedList<Disciplina>();
		esperadas.add(new Disciplina("P1", 4));
		esperadas.add(new Disciplina("LP1", 4));
		esperadas.add(new Disciplina("Cálculo 1", 4));
		esperadas.add(new Disciplina("Vetorial ", 4));
		esperadas.add(new Disciplina("LPT", 4));
		esperadas.add(new Disciplina("IC", 4));

		Collection<Disciplina> iniciais = planejador
				.getDisciplinasAlocadasNoPeriodo(1);

		assertEquals(6, iniciais.size());
		for (Disciplina disciplina : esperadas) {
			System.out.println(disciplina.getNome());
			assertTrue(iniciais.contains(disciplina));
		}

	}

	@Test
	public void deveAtualizarAlocacaoAoAdicionar() {
	}

	@Test
	public void naoDevePermitirAlteracaoNoPrimeiroPeriodo() throws Exception {

	}

	@Test
	public void deveAtualizarAlocacaoAoRemover() {
	}

	@Test
	public void deveRespeitarPrerequisitos() {
	}

	@Test
	public void deveRespeitarLimiteDeCreditos() {
	}

}
