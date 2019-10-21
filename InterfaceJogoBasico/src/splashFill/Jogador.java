package splashFill;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Jogador implements Jogada{
	protected String name = "";
	protected int plays = 30;
	
	protected boolean turn;
	protected boolean winner = false;
	
	public Jogador() {
		
	}

	
	public Jogador(String name) {
		this.name = name;
	}
	
	public void iniciarJogador() {
		winner = false;
		turn = false;
	}

	
	/* GETTERS AND SETTERS */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlays() {
		return plays;
	}

	public void setPlays(int plays) {
		this.plays = plays;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	

}
