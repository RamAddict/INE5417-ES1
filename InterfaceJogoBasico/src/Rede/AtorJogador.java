package Rede;

import javax.swing.JOptionPane;

import DominioDoProblema.ElementoDominioProblema;
import splashFill.Casa;
import splashFill.InterfaceJogo;
import splashFill.Jogador;

public class AtorJogador {
	
	protected AtorRede atorRede;
	protected ElementoDominioProblema domProblema;
	protected int idUser;
	protected InterfaceJogo interfaceJogo;

	public AtorJogador(InterfaceJogo interfaceJogo) {
		atorRede = new AtorRede(this);
		domProblema = new ElementoDominioProblema();
		this.interfaceJogo = interfaceJogo;
	}

	public String conectar(String string, String string2) {
		String mensagem = "Você já está conectado!";
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
		String mensagem = "Você não esta conectado ou a partida esta em andamento.";
		int permitido = domProblema.permitidoDesconectar();
		switch(permitido) {
		case 0:
			mensagem = this.atorRede.desconectar();
			if (mensagem.equals("Sucesso: desconectado de Netgames Server")) {
				domProblema.definirConectado(false);
				domProblema.definirPartidaAndamento(false);
			}
			
			break;
		case 1:
			mensagem="Voce optou por nao parar a partida.";
			break;
		case 2:
			mensagem="Voce optou por nao parar a partida.";
			break;
		case 3:
			mensagem="Você não está conectado.";
			break;
		default:
			
		}

		return mensagem;
	}
	
	public String iniciarPartida() {
		String mensagem = "Nao existem dois jogadores conectados no servidor";
		int permitido = domProblema.permitidoIniciarPartida();
		
		switch(permitido) {
		case 0:
			mensagem = this.atorRede.iniciarPartida();
			
			break;
		
		case 1:
			mensagem = "Você não está conectado ao NetGames.";
			break;
			
		case 2:
			mensagem = "Uma partida já está em andamento.";
			break;
		
		default:
			
			break;
			
		
		}
		return mensagem;
	}
	
	
	public void receberJogada(Move move) {
		this.interfaceJogo.atualizarTabuleiro(move);
	}
	
	public void tratarIniciarPartida(Integer posicao) {
		//this.interfaceJogo.getTabuleiro().criarJogador();
		this.domProblema.definirPartidaAndamento(true);
		this.interfaceJogo.atualizarConsole("JOGO INICIADO");
		
	}
	
	public void tratarConexaoPerdida() {
		// mostra erro no console do jogo
		this.domProblema.definirConectado(false);
		this.interfaceJogo.atualizarConsole("Conexão perdida! :( ");
		//System.exit(0);
	}
	
	public void finalizarPartidaComErro() {
		// mostra erro no console do jogo
		this.domProblema.definirPartidaAndamento(false);
		this.interfaceJogo.atualizarConsole("O adversário foi desconectado :( ");
		
		//System.exit(0);
		
	}

}
