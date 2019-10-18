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
	
	public int permitidoDesconectar() {
		int permitido = 3;
		if(conectado) {
			if(partidaAndamento) {
//				Object[] options = { "OK", "CANCEL" };
//				JOptionPane.showOptionDialog(null, "Click OK to continue", "A partida está em andamento, tem certeza que quer sair?",
//				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				int opt = JOptionPane.showConfirmDialog(null, "A partida está em andamento, tem certeza que quer sair?");
				//0 = yes ; 1 = no / 2 = cancel
				permitido = opt;
			}
		} else {
			permitido = 3; // nao esta conectado
		}
		
		return permitido;
	}

	public int permitidoIniciarPartida() {
		// só é permitido iniciar a partida se tiverem dois jogadores (tratado automaticamente pelo NetGames)
		// alem de estar conectado e a partida nao tiver sido iniciada anteriormente
		
		int permitido = 1; // nao permitido
		if(!conectado) {
			permitido = 1; // nao esta conectado
		} else {
			if(partidaAndamento) {
				permitido = 2; //partida ja esta em andamento
			} else {
				permitido = 0; //ta conectado mas nao ta em andamento
				definirPartidaAndamento(true);
			}
		}
		
		return permitido;
	}


}
