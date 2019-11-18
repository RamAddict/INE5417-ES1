package splashFill;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Jogador implements Jogada{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3639029510344563679L;
	
	protected String name = "";
	protected int plays;
	protected String color = "";
	protected boolean turn;
	protected boolean winner = false;
	
	public Jogador() {
		this.plays = 30;
	}

	
	public Jogador(String name) {
		this.name = name;
		this.plays = 30;
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


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	

}
