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
	private ArrayList<Casa> casas = new ArrayList<Casa>();
	private AtorJogador atorJogador;
	
	private Jogador jogador;
	private Jogador adversario;
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;

	
	public Tabuleiro() {
		
	}
	
	public Tabuleiro(ArrayList<Casa> casas) {
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
		// so e permitido iniciar a partida se tiverem dois jogadores (tratado automaticamente pelo NetGames)
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
		}
	}
	
	public ArrayList<Casa> getCasas() {
		return this.casas;
	}

	public void setCasas(ArrayList<Casa> casas) {
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
	
	public Casa createButton(int linha, int coluna, InterfaceJogo interJogo) {
    	final Casa b = new Casa(linha, coluna);
        
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
	
	public void notificarResultado(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}
	
	public boolean checaJogada(Casa casa) {
		boolean valida = false;
		
		if(comparaIds(casa.getDonoID(), this.jogador.getId())) {
			valida = true;
		} else { //casa pertence ao outro jogador
			JOptionPane.showMessageDialog(null, "Essa casa n�o te pertence, escolha outra!");
		}
		
		return valida;
	}
	
	public boolean comparaIds(int id1, int id2) {
		boolean resultado = false;
		
		if(id1 == 0) {
			resultado = true;
		} else if(id1 == id2) {
			resultado = true;
		} else { //casa pertence ao outro jogador
			resultado = false;
		}
		
		return resultado;
	}
	
	/* Passa o turno
	 * 
	 */
	public void passaTurno() {
		int fichasJogador = jogador.getPlays();
		int fichasAdversario = adversario.getPlays();
		
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
			JOptionPane.showMessageDialog(null, "Voc� n�o possui pe�as no teu tabuleiro! Derrota!");
			this.jogador.setWinner(false);
			this.adversario.setWinner(true);
		} else
		if (codigo == 1) {
			JOptionPane.showMessageDialog(null, "Acabaram as pe�as, empate!");
			this.jogador.setWinner(false);
			this.adversario.setWinner(false);
		}
	}
	
	public void realizaJogada(Casa casa, AtorJogador atorJogador) {
	      	Move move = new Move();
			ArrayList<Casa> casas_movimento = new ArrayList<Casa>();
			
			
			for (Casa c : this.getCasas()) {
				casas_movimento.add(c);
			}
			move.setBotoes(casas_movimento);
	      	atorJogador.enviarJogada(move);
	      	
	      	passaTurno();
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
	
	public void setCasa(Casa casa) {
		int idx = casa.getColuna() + casa.getLinha()*6;
		this.casas.get(idx).setDonoID(casa.getDonoID());
		this.casas.get(idx).setBackground(casa.getBackground());
		this.casas.get(idx).setFichas(casa.getFichas());
		this.casas.get(idx).setText(casa.getText());
		this.casas.get(idx).setDono(casa.getDono());
	}
	
}
