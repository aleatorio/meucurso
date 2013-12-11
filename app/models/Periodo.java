package models;

import java.util.HashSet;
import java.util.Set;

public class Periodo {
	
	private Set<Disciplina> disciplinas;
	
	public Periodo() {
		this.disciplinas = new HashSet<Disciplina>();
	}
	
	public void addDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}

	public Set<Disciplina> getDisciplinas() {
		return this.disciplinas;
	}

	public int getTotalCreditos() {
		int total = 0;
		for (Disciplina disc : this.disciplinas) {
			total += disc.getCreditos();
		}
		return total;
	}

}
