package splashFill;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Rede.AtorJogador;
import Rede.Move;

public class Tabuleiro{
	private List<Casa> casas = new ArrayList<Casa>();
	private AtorJogador atorJogador;
	
	private Jogador jogador;
	private Jogador adversario;
	
	private JPanel matriz = new JPanel(new GridLayout(6, 6, 2, 2));
	
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;
	
	
	
	public Tabuleiro() {
		
	}
	
	public Tabuleiro(List<Casa> casas) {
		this.casas = casas;
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
		// n�o tem condi�oes para conectar
		return !conectado;
	}
	
	public int permitidoDesconectar() {
		int permitido = 3;
		if(conectado) {
			if(partidaAndamento) {
				int opt = questionarRealizaDesconexao();
				//0 = yes ; 1 = no / 2 = cancel
				permitido = opt;
			} else { // conectado mas a partida nao esta em andamento
				permitido = 0;
			}
		} else {
			permitido = 3; // nao esta conectado
		}
		
		return permitido;
	}
	
	/**
	 * Pergunta se quer desconectar tendo em vista a partida em andamento
	 * @return 0 = yes ; 1 = no ; 2 = cancel
	 */
	public int questionarRealizaDesconexao() {
		return JOptionPane.showConfirmDialog(null, "A partida est� em andamento, tem certeza que quer sair?");
	}

	public int permitidoIniciarPartida() {
		// s� � permitido iniciar a partida se tiverem dois jogadores (tratado automaticamente pelo NetGames)
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
	
	
	public void criarJogador(String name) {
		if(jogador == null) {
			jogador = new Jogador(name);
			jogador.iniciarJogador();
		} else {
			adversario = new Jogador(name);
			adversario.iniciarJogador();
		}
	}
	
//	public void setTurno(Jogador jogador) {
//		if(jogador == this.jogador) {
//			this.jogador.setTurn(true);
//		} else {
//			this.adversario.setTurn(true);
//		}
//	}
	
	public void limparTabuleiro(InterfaceJogo interJogo) {
		this.criaMatriz(interJogo);
	}
	
	public List<Casa> getCasas() {
		return casas;
	}

	public void setCasas(List<Casa> casas) {
		this.casas = casas;
	}

	public AtorJogador getAtorJogador() {
		return atorJogador;
	}

	public void setAtorJogador(AtorJogador atorJogador) {
		this.atorJogador = atorJogador;
	}
	
	

	public Jogador getJogador1() {
		return jogador;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador = jogador1;
	}

	public Jogador getJogador2() {
		return adversario;
	}

	public void setJogador2(Jogador jogador2) {
		this.adversario = jogador2;
	}
	

	public JPanel getMatriz() {
		return matriz;
	}

	public void setMatriz(JPanel matriz) {
		this.matriz = matriz;
	}

	public void criaMatriz(InterfaceJogo interJogo) {
        for (int i = 0; i < 36; i++) {
            int row = i / 6;
            int col = i % 6;
            Casa gb = createButton(row, col, interJogo);
            casas.add(gb);
            this.matriz.add(gb);
        }
    }
	
	public Casa createButton(int linha, int coluna, InterfaceJogo interJogo) {
        //final JButton b = new JButton("");
    	final Casa b = new Casa();
        
        b.setPreferredSize(new Dimension(80, 80));

        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	interJogo.click(linha, coluna /*, e*/);    
            }
        });
        
        this.casas.add(b);
        return b;
    }
	
	
//	public Jogador checaJogador(Jogador player) {
//		Jogador jogador = null;
//		
//		if(player.getId() == this.jogador.id) {
//			jogador = this.jogador;
//		} else {
//			jogador = this.adversario;
//		}
//		
//		return jogador;
//	}
	
//	public Jogador checaAdversario(Jogador jogador) {
//		Jogador adversario = null;
//		
//		if(jogador.getId() == this.jogador.id) {
//			adversario = this.adversario;
//		} else {
//			adversario = this.jogador;
//		}
//		
//		return adversario;
//	}
	
	public void notificarResultado(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}
	
	public boolean checaJogada(Casa botao, Jogador player) {
//		Jogador jogador = checaJogador(player);
//		Jogador adversario = checaAdversario(jogador);
//    	/* Checks if the player still have moves to do */
//		if(jogador.getPlays() > 0) {
//			jogador.setPlays(jogador.getPlays() - 1);
//		} else {
//			notificarResultado("Voce nao tem mais movimentos!");
//			return false;
//		}
//		
//		/* If the player still have plays left, continue checking */
//    	if (botao.getPlayer() == 0) { // botao nao tem dono ainda
//    		botao.setPlayer(jogador.getId());
//    	} else if (botao.getPlayer() == jogador.getId()) { // botao pertence ao jogador que clicou
//    		return true;
//    	} else { // botao pertence ao outro jogador
//		notificarResultado("Esse botao não te pertence, escolha outro");
//    		return false;
//    	}
//    	
//    	// como o jogador ja fez a jogada, coloca como turno dele falso
//    	jogador.setTurn(false);
//    	adversario.setTurn(true);

		return true;
	}
	
	public boolean realizaJogada(Casa casa, Jogador jogador, AtorJogador atorJogador) {
		boolean jogadaRealizada = false;
		if(checaJogada(casa, jogador)) {
			/* move */
	      	//changeCounter(gb, e, linha, coluna);
	    	
			this.getCasas().set(casa.getLinha()*6 + casa.getColuna(), casa);
	      	Move move = new Move(this.getCasas());
			
	      	atorJogador.enviarJogada(move);
			
			jogadaRealizada = true;
		} else {
			
		}
		
		return jogadaRealizada;
	}
}
