package ElementosInterface;

import javax.swing.JButton;

public class Botao extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String imagem;
	private JButton botao;
	
	public Botao() {
		super();
	}
	
	public Botao(String nome) {
		super();
		this.botao = new JButton("");
	}
	
	public Botao(int id, String imagem, JButton botao) {
		super();
		this.id = id;
		this.imagem = imagem;
		this.botao = botao;
	}

	
	/*GETTERS & SETTERS*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public JButton getBotao() {
		return botao;
	}

	public void setBotao(JButton botao) {
		this.botao = botao;
	}
	
	
	
	

}
