package Rede;

import javax.swing.JOptionPane;

import DominioDoProblema.ElementoDominioProblema;
import splashFill.Botao;
import splashFill.InterfaceJogo;
import splashFill.Jogador;

public class AtorJogador {
	
	protected AtorRede atorRede;
	protected ElementoDominioProblema domProblema;
	protected int idUser;
	protected InterfaceJogo interfaceJogo;

	public AtorJogador() {
		atorRede = new AtorRede(this);
		domProblema = new ElementoDominioProblema();
	}

	public String conectar(String string, String string2) {
		String mensagem = "Condicao para conexao nao atendida (defina qual)";
		boolean permitido = domProblema.permitidoConectar();
		if (permitido) {
			mensagem = this.atorRede.conectar(string, string2);
			if (mensagem.equals("Sucesso: conectado a Netgames Server")) {
				domProblema.definirConectado(true);
			}
		}
		return mensagem;
	}
	
	public String desconectar() {
		String mensagem = "Condicao para desconexao nao atendida (defina qual)";
		boolean permitido = domProblema.permitidoDesconectar();
		if (permitido) {
			mensagem = this.atorRede.desconectar();
			if (mensagem.equals("Sucesso: desconectado de Netgames Server")) {
				domProblema.definirConectado(false);
			}
		}
		return mensagem;
	}
	
	public String iniciarPartida() {
		String mensagem = "Nao existem dois jogadores conectados no servidor";
		boolean permitido = domProblema.permitidoIniciarPartida();
		if (permitido) {
			mensagem = this.atorRede.iniciarPartida();
		}
		return mensagem;
	}
	
	public boolean checaJogada(Botao botao, Jogador player) {
    	/* Checks if the player still have moves to do */
		if(player.getPlays() > 0) {
			player.setPlays(player.getPlays() - 1);
		} else {
			JOptionPane.showMessageDialog(null, "Voce nao tem mais movimentos!");
			return false;
		}
		
		/* If the player still have plays left, continue checking */
    	if (botao.getPlayer() == 0) { // botao nao tem dono ainda
    		botao.setPlayer(player.getId());
    	} else if (botao.getPlayer() == player.getId()) { // botao pertence ao jogador que clicou
    		return true;
    	} else { // botao pertence ao outro jogador
    		JOptionPane.showMessageDialog(null, "Esse botao não te pertence, escolha outro");
    		return false;
    	}

		return true;
	}
	
	public void receberJogada(Move move) {
		interfaceJogo.atualizarInterface(move);
	}

}
