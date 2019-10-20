package Rede;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.inf.leobr.cliente.Jogada;
import splashFill.Casa;

public class Move implements Jogada {
	
	/**
	 * A move is going to be the list of buttons after the last move is done
	 */
	private static final long serialVersionUID = 8319270686294033589L;
	
	
	List<Casa> botoes = new ArrayList<Casa>();

	public Move(List<Casa> botoes) {
		super();
		this.botoes = botoes;
	}

	public List<Casa> getBotoes() {
		return botoes;
	}

	public void setBotoes(List<Casa> botoes) {
		this.botoes = botoes;
	}
	
	
	
}
