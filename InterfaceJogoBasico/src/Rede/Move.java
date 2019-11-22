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
	
	
	ArrayList<Casa> botoes;/* = new ArrayList<Casa>(); */
	
	public Move() {
		botoes = new ArrayList<Casa>();
	}
	
	public Move(ArrayList<Casa> casas) {
		super();
		this.botoes = casas;
	}
	
//	public Move(Casa casa) {
//		
//	}

	public ArrayList<Casa> getBotoes() {
		return botoes;
	}

	public void setBotoes(ArrayList<Casa> botoes) {
		for (Casa c : botoes) {
			this.botoes.add(c);
		}
	}
	
	
	
	
}
