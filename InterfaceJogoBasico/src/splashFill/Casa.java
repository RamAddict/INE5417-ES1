package splashFill;

import javax.swing.JButton;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Casa extends JButton implements Jogada{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String color;
	private JButton botao;
	private int linha, coluna;
	private String player;
	private int pontos;
	
	public Casa() {
		super();
	}
	
	public Casa(String nome, int linha, int coluna) {
		super();
		this.botao = new JButton("");
		this.player = "";
		this.linha = linha;
		this.coluna = coluna;
		this.pontos = 0;
	}
	
	public Casa(int id, String color, JButton botao, int linha, int coluna) {
		super();
		this.id = id;
		this.color = "/resources/"+color+"_1.png";
		this.botao = botao;
		this.player = "";
		this.linha = linha;
		this.coluna = coluna;
		this.pontos = 0;
	}

	
	/*GETTERS & SETTERS*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public JButton getBotao() {
		return botao;
	}

	public void setBotao(JButton botao) {
		this.botao = botao;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	
	
	

}
