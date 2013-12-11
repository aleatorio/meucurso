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

	private static final int MAX_CREDITOS = 28;
	private Map<Integer, Periodo> alocadas;

	public Planejador() {
		alocadas = new HashMap<Integer, Periodo>();
		alocaPrimeiroPeriodo();
	}

	private void alocaPrimeiroPeriodo() {
		Periodo primeiroPeriodo = new Periodo();
		primeiroPeriodo.addDisciplina(new Disciplina("P1", 4));
		primeiroPeriodo.addDisciplina(new Disciplina("LP1", 4));
		primeiroPeriodo.addDisciplina(new Disciplina("Cálculo 1", 4));
		primeiroPeriodo.addDisciplina(new Disciplina("Vetorial ", 4));
		primeiroPeriodo.addDisciplina(new Disciplina("LPT", 4));
		primeiroPeriodo.addDisciplina(new Disciplina("IC", 4));
		alocadas.put(1, primeiroPeriodo);
		
	}

	public Set<Disciplina> getDisciplinasAlocadasNoPeriodo(int periodo) {
		return this.alocadas.keySet().contains(periodo) ? this.alocadas
				.get(periodo).getDisciplinas() : new HashSet<Disciplina>();
	}

	public void alocar(int periodo, Disciplina disciplina)
			throws AlocacaoNaoPermitidaException {
		if (periodo == 1) {
			throw new AlocacaoNaoPermitidaException(
					"Nenhuma alocacao pode ser feita no primeiro periodo");
		}
		
		if (getTotalCreditos(periodo)+disciplina.getCreditos() > MAX_CREDITOS) {
			throw new AlocacaoNaoPermitidaException(
					"Ultrapassou o limite de créditos.");
		}
		
		if (verificaPreRequisitos(disciplina, periodo)) {
			if (!alocadas.containsKey(periodo)) {
				alocadas.put(periodo, new Periodo());
			}
			this.alocadas.get(periodo).addDisciplina(disciplina);
			
		} else{
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
		if (!this.alocadas.containsKey(periodo)){
			return 0;
		}
		return this.alocadas.get(periodo).getTotalCreditos();
	}

	public void desalocar(int periodo, Disciplina p2) {
		getDisciplinasAlocadasNoPeriodo(periodo).remove(p2);
	}

}
