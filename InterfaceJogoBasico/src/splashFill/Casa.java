package splashFill;

import java.awt.Font;

import javax.swing.JButton;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Casa extends JButton implements Jogada{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	//private String color;
	private JButton botao;
	private int linha, coluna;
	private String player;
	private int fichas;
	
	private Jogador dono;
	
	private int donoID;
	
	public Casa() {
		super();
		Font f = new Font("serif", Font.PLAIN, 40);
		this.setFont(f);
		this.donoID = 0;
	}
	
	public Casa(int linha, int coluna) {
		super();
		this.botao = new JButton("");
		Font f = new Font("serif", Font.PLAIN, 40);
		this.setFont(f);
		this.player = "";
		this.linha = linha;
		this.coluna = coluna;
		this.fichas = 0;
		this.donoID = 0;
	}
	
	public Casa(int id, String color, JButton botao, int linha, int coluna) {
		super();
		this.id = id;
		//this.color = "/resources/"+color+"_1.png";
		this.botao = botao;
		this.player = "";
		this.linha = linha;
		this.coluna = coluna;
		this.fichas = 0;
		this.donoID = 0;
	}

	
	/*GETTERS & SETTERS*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getColor() {
//		return color;
//	}
//
//	public void setColor(String color) {
//		this.color = color;
//	}
	
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

	public int getFichas() {
		return fichas;
	}

	public void setFichas(int fichas) {
		this.fichas = fichas;
	}

	public int getDonoID() {
		return donoID;
	}

	public void setDonoID(int donoID) {
		this.donoID = donoID;
	}

	public Jogador getDono() {
		return dono;
	}

	public void setDono(Jogador dono) {
		this.dono = dono;
	}
	
	
	
	
	
	

}
