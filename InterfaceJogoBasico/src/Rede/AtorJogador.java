package Rede;

import java.awt.Color;

import javax.swing.JOptionPane;

import splashFill.Casa;
import splashFill.InterfaceJogo;
import splashFill.Jogador;
import splashFill.Tabuleiro;

public class AtorJogador {
	
	protected AtorRede atorRede;
	protected int idUser;
	protected InterfaceJogo interfaceJogo;

	public AtorJogador(InterfaceJogo interfaceJogo) {
		atorRede = new AtorRede(this);
		this.interfaceJogo = interfaceJogo;
	}

	public String conectar(String string, String string2) {
		String mensagem = "Você já está conectado!";
		boolean permitido = this.interfaceJogo.getTabuleiro().permitidoConectar();
		if (permitido) {
			mensagem = this.atorRede.conectar(string, string2);
			if (mensagem.equals("Sucesso: conectado a Netgames Server")) {
				this.interfaceJogo.getTabuleiro().definirConectado(true);
			}
		}
		return mensagem;
	}
	
	public String desconectar() {
		String mensagem = "Você não esta conectado ou a partida esta em andamento.";
		int permitido = this.interfaceJogo.getTabuleiro().permitidoDesconectar();
		switch(permitido) {
		case 0:
			mensagem = this.atorRede.desconectar();
			if (mensagem.equals("Sucesso: desconectado de Netgames Server")) {
				this.interfaceJogo.getTabuleiro().definirConectado(false);
				this.interfaceJogo.getTabuleiro().definirPartidaAndamento(false);
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
		int permitido = this.interfaceJogo.getTabuleiro().permitidoIniciarPartida();
		
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
	
	public void enviarJogada(Move move) {
		System.out.println("AtorJogador enviando jogada");
		this.atorRede.enviarJogada(move);
	}
	
	public void receberJogada(Move move) {
		this.interfaceJogo.getTabuleiro().getJogador1().setTurn(true);
		this.interfaceJogo.getTabuleiro().getJogador2().setTurn(false);
		this.interfaceJogo.atualizarTabuleiro(move);
	}
	
	public void tratarIniciarPartida(Integer posicao) {
		this.interfaceJogo.getTabuleiro().definirPartidaAndamento(true);
		this.interfaceJogo.atualizarConsole("JOGO INICIADO");
		
	}
	
	public void tratarConexaoPerdida() {
		this.interfaceJogo.getTabuleiro().definirConectado(false);
		this.interfaceJogo.atualizarConsole("Conexão perdida! :( ");
	}
	
	public void finalizarPartidaComErro() {
		this.interfaceJogo.getTabuleiro().definirPartidaAndamento(false);
		this.interfaceJogo.atualizarConsole("O adversário foi desconectado :( ");
		
	}

}
