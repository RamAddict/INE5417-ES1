package Rede;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.inf.leobr.cliente.Jogada;
import splashFill.Botao;

public class Move implements Jogada {
	
	/**
	 * A move is going to be the list of buttons after the last move is done
	 */
	private static final long serialVersionUID = 8319270686294033589L;
	
	
	List<Botao> botoes = new ArrayList<Botao>();

	public Move(List<Botao> botoes) {
		super();
		this.botoes = botoes;
	}

	public List<Botao> getBotoes() {
		return botoes;
	}

	public void setBotoes(List<Botao> botoes) {
		this.botoes = botoes;
	}
	
	
	
}
