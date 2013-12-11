package models;

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

	public Set<Disciplina> getDisciplinasAlocadasNoPeriodo(int periodo) {
		return this.alocadas.keySet().contains(periodo) ? this.alocadas
				.get(periodo) : new HashSet<Disciplina>();
	}

	public void alocar(int periodo, Disciplina disciplina)
			throws AlocacaoNaoPermitidaException {
		if (periodo == 1) {
			throw new AlocacaoNaoPermitidaException(
					"Nenhuma alocacao pode ser feita no primeiro periodo");
		}
		Set<Disciplina> alocadas = getDisciplinasAlocadasNoPeriodo(periodo);
		if (verificaPreRequisitos(disciplina, periodo)) {
			alocadas.add(disciplina);
			this.alocadas.put(periodo, alocadas);
		}
		else{
			throw new AlocacaoNaoPermitidaException(
					"Quebra de pré-requisito.");
		}
	}

	private boolean verificaPreRequisitos(Disciplina disciplina, int periodoDaDisciplina) {
		Set<Disciplina> pagas = new HashSet<Disciplina>();
		for(int i=1; i < periodoDaDisciplina; i++){
			pagas.addAll(getDisciplinasAlocadasNoPeriodo(i));
		}
		for (Disciplina disc : disciplina.getPreRequisitos()) {
			if(!pagas.contains(disc)){
				return false;
			}
		}
		return true;
	}

	public int getTotalCreditos(int periodo) {
		int total = 0;
		for (Disciplina disc : getDisciplinasAlocadasNoPeriodo(periodo)) {
			total += disc.getCreditos();
		}
		return total;
	}

	public void desalocar(int periodo, Disciplina p2) {
		getDisciplinasAlocadasNoPeriodo(periodo).remove(p2);
	}

}
