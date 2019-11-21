package splashFill;

import java.awt.Color;
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
	
	private Jogador jogador; // = new Jogador(Color.RED);
	private Jogador adversario; //  =new Jogador(Color.BLUE);
	
	private JPanel matriz = new JPanel(new GridLayout(6, 6, 2, 2));
	
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;
	
	private boolean decrementar = true;
	
	
	
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
		// nï¿½o tem condiï¿½oes para conectar
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
		return JOptionPane.showConfirmDialog(null, "A partida estï¿½ em andamento, tem certeza que quer sair?");
	}

	public int permitidoIniciarPartida() {
		// sï¿½ ï¿½ permitido iniciar a partida se tiverem dois jogadores (tratado automaticamente pelo NetGames)
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
			adversario = new Jogador("adversario");
			adversario.iniciarJogador();
			System.out.println("pnigus");
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
		return this.casas;
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
            	
            	interJogo.click(linha, coluna);    
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
	
	public boolean checaJogada(Casa casa) {
		boolean valida = false;
//		Jogador jogador = checaJogador(player);
//		
//		Jogador adversario = checaAdversario(jogador);
		
		
		
//		if(decrementar) {
			/* Checks if the player still have moves to do */
//			if(jogador.getPlays() > 0) {
//				jogador.setPlays(jogador.getPlays() - 1);
//				decrementar = false;
//			} else {
//				notificarResultado("Voce nao tem mais movimentos!");
//				return false;
//			}
//		}
    	
		
//		/* If the player still have plays left, continue checking */
//    	if (botao.getPlayer() == "") { // botao nao tem dono ainda
//    		botao.setPlayer(jogador.getName());
//    	} else if (botao.getPlayer() == jogador.getName()) { // botao pertence ao jogador que clicou
//    		return true;
//    	} else { // botao pertence ao outro jogador
//		notificarResultado("Esse botao não te pertence, escolha outro");
//    		return false;
//    	}
//    	
		if(casa.getDono() == null) {
			casa.setDono(this.jogador);
			//passaTurno();
			casa.setBackground(this.jogador.getColor());
			valida = true;
		} else if(casa.getDono() == this.jogador) {
			//passaTurno();
			valida = true;
		} else { //casa pertence ao outro jogador
			JOptionPane.showMessageDialog(null, "Essa casa não te pertence, escolha outra!");
		}
    	/*TODO implementar função passaTurno*/
		//passaTurno();
		System.out.println("visao especifica de interação J " + this.jogador.isTurn() + " Fichas " + this.jogador.getPlays());
		System.out.println("visao especifica de interação A " + this.adversario.isTurn() + " Fichas " + this.adversario.getPlays());
		/*PEGAR CASAS ADJACENTES E MUDAR CONTAGEM DELAS*/
		
		
		return valida;
	}
	
	/* Passa o turno
	 * 
	 */
	public void passaTurno() {
		int fichasJogador = jogador.getPlays();
		System.out.println(adversario.getPlays());
		int fichasAdversario = adversario.getPlays();
		
		if (fichasJogador == 0 && fichasAdversario == 0)
		{
			finalizarPartida(0);
		}
		
		if (jogador.isTurn())
		{
			jogador.setTurn(false);
			jogador.setPlays(fichasJogador-1);
			
			adversario.setTurn(true);
		} else
		{
			jogador.setTurn(true);
			
			adversario.setTurn(false);
			adversario.setPlays(fichasAdversario-1);
		}
	}
	
	public void finalizarPartida(int codigo) {
		if (codigo == 0) {
			JOptionPane.showInputDialog("fodeo");
		}
	}
	
	public void realizaJogada(Casa casa, AtorJogador atorJogador) {
		//boolean jogadaRealizada = false;
		//if(checaJogada(casa, jogador)) {
			/* move */
	      	//changeCounter(gb, e, linha, coluna);
	    	
			//this.getCasas().set(casa.getLinha()*6 + casa.getColuna(), casa);
	      	Move move = new Move(this.getCasas());
			
	      	atorJogador.enviarJogada(move);
	      	
	      	passaTurno();
			
			//jogadaRealizada = true;
		//} else {
			
		//}
		
		//return jogadaRealizada;
	}
	
	public Casa getCasa(int coluna, int linha) {
		Casa casa;
		if(linha < 0 || linha > 5 || coluna < 0 || coluna > 5) {
			casa = new Casa();
			casa.setFichas(-1);
		} else {
			int indiceVetorCasas = linha*6 + coluna;
			casa = this.casas.get(indiceVetorCasas);
		}
		
		return casa;
	}
	
}
