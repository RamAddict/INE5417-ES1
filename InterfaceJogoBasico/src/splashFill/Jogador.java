package splashFill;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Jogador implements Jogada{
	protected int id;
	protected String name = "";
	protected int plays = 30;
	protected String color = "";
	
	protected boolean turn;
	protected boolean winner = false;
	
	public Jogador() {
		
	}
	
	public Jogador(int id, String name, int plays, String color) {
		super();
		this.id = id;
		this.name = name;
		this.plays = plays;
		this.color = color;
	}
	
	public Jogador(String name) {
		this.name = name;
	}
	
	public void iniciarJogador() {
		winner = false;
		turn = false;
		if (this.id == 1) {
			color = "red";
		} else {
			color = "blue";
		}
	}

	
	/* GETTERS AND SETTERS */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
