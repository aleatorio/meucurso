package models;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Controlador fora do play.
 * 
 * @author nazareno
 * 
 */
public class Planejador {

	private Map<Integer, Set<Disciplina>> alocadas;

	public Planejador() {
		alocadas = new HashMap<Integer, Set<Disciplina>>();
		alocaPrimeiroPeriodo();
	}

	private void alocaPrimeiroPeriodo() {
		Set<Disciplina> primeiroPeriodo = new HashSet<Disciplina>();
		primeiroPeriodo.add(new Disciplina("P1", 4));
		primeiroPeriodo.add(new Disciplina("LP1", 4));
		primeiroPeriodo.add(new Disciplina("Cálculo 1", 4));
		primeiroPeriodo.add(new Disciplina("Vetorial ", 4));
		primeiroPeriodo.add(new Disciplina("LPT", 4));
		primeiroPeriodo.add(new Disciplina("IC", 4));
		alocadas.put(1, primeiroPeriodo);
	}

	public Collection<Disciplina> getDisciplinasAlocadasNoPeriodo(int periodo) {
		return this.alocadas.get(periodo);
	}

}
