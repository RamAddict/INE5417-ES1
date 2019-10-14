package DominioDoProblema;

import javax.swing.JOptionPane;

public class ElementoDominioProblema {
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;

	public ElementoDominioProblema() {
		// TODO Auto-generated constructor stub
	}

	public void definirConectado(boolean valor) {
		conectado = valor;
	}
	
	public boolean estaConectado() {
		return conectado;
	}
	
	public void definirPartidaAndamento(boolean valor) {
		partidaAndamento = valor;
	}
	
	public boolean informarPartidaAndamento() {
		return partidaAndamento;
	}
	
	public boolean permitidoConectar() {
		// não tem condiçoes para conectar
		return !conectado;
	}
	
	public boolean permitidoDesconectar() {
		boolean permitido = false;
		if(partidaAndamento) {
			int opt = JOptionPane.showConfirmDialog(null, "A partida está em andamento, tem certeza que quer sair?");
			//0 = yes ; 1 = no / 2 = cancel
			if(opt == 0) {
				permitido = conectado;
			} else {
				permitido = !conectado;
			}
		}
		return permitido;
	}

	public boolean permitidoIniciarPartida() {
		//só é permitido iniciar a partida se tiverem dois jogadores (tratado automaticamente pelo NetGames)
		return !partidaAndamento;
	}


}
