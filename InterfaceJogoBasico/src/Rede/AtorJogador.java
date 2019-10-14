package Rede;

import DominioDoProblema.ElementoDominioProblema;
import splashFill.Botao;
import splashFill.InterfaceJogo;
import splashFill.Jogador;

public class AtorJogador {
	
	protected AtorRede ngServer;
	protected ElementoDominioProblema domProblema;
	protected String idUser;
	protected InterfaceJogo interfaceJogo;

	public AtorJogador() {
		ngServer = new AtorRede(this);
		domProblema = new ElementoDominioProblema();
	}

	public String conectar(String string, String string2) {
		String mensagem = "Condicao para conexao nao atendida (defina qual)";
		boolean permitido = domProblema.permitidoConectar();
		if (permitido) {
			mensagem = ngServer.conectar(string, string2);
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
			mensagem = ngServer.desconectar();
			if (mensagem.equals("Sucesso: desconectado de Netgames Server")) {
				domProblema.definirConectado(false);
			}
		}
		return mensagem;
	}
	
	public String iniciarPartida() {
		String mensagem = "Condicao para iniciar partida nao atendida (defina qual)";
		boolean permitido = domProblema.permitidoIniciarPartida();
		if (permitido) {
			mensagem = ngServer.iniciarPartida();
		}
		return mensagem;
	}
	
	public boolean checaJogada(Botao botao) {
    	/* Checks if the player still have moves to do */
    	if (botao.getPlayer() == 0) { // botao nao tem dono ainda
    		
    	} else if (botao.getPlayer() == 1) { // botao pertence ao jogador 1
    		
    	} else { // botao pertence ao jogador 2
    		
    	}
    	/*if(player.getMoves <= 0) {
    		return false;
    		END GAME
    	}*/
    	
    	/* Checks if the button belongs to the player*/
    	
    	
    	/*  */
		
		return true;
	}
	
	public void receberJogada(Move move) {
		interfaceJogo.atualizarInterface(move);
	}

}
