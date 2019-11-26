package splashFill;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Rede.AtorJogador;
import Rede.Move;

public class Tabuleiro{
	private ArrayList<Casa> casas = new ArrayList<Casa>();
	private AtorJogador atorJogador;
	
	private Jogador jogador;
	private Jogador adversario;
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;

	protected boolean umaPartidaAcabada = false;
	
	public boolean isUmaPartidaAcabada() {
		return umaPartidaAcabada;
	}

	public void setUmaPartidaAcabada(boolean umaPartidaAcabada) {
		this.umaPartidaAcabada = umaPartidaAcabada;
	}

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
			JOptionPane.showMessageDialog(null, "Essa casa não te pertence, escolha outra!");
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
			JOptionPane.showMessageDialog(null, "Você não possui peças no teu tabuleiro! Derrota!");
			this.jogador.setWinner(false);
			this.adversario.setWinner(true);
		} else
		if (codigo == 1) {
			JOptionPane.showMessageDialog(null, "Acabaram as peças, empate!");
			this.jogador.setWinner(false);
			this.adversario.setWinner(false);
		} else if (codigo == 2) {
			JOptionPane.showMessageDialog(null, "Você ganhou!");
			this.jogador.setWinner(true);
			this.adversario.setWinner(false);
		}
		this.definirPartidaAndamento(false);
		this.setUmaPartidaAcabada(true);
	}
	
	public void realizaJogada(Casa casa, AtorJogador atorJogador) {
	      	Move move = new Move();
			ArrayList<Casa> casas_movimento = new ArrayList<Casa>();
			
			
			for (Casa c : this.getCasas()) {
				casas_movimento.add(c);
			}
			move.setBotoes(casas_movimento);
			if (this.getJogador1().getPlays() < 30) {
		    	boolean acabaram_as_fichas_adversario = true;
		    	for (Casa btn : this.getCasas()) {
		    		if (btn.getDonoID() != this.getJogador1().getId()) {
		    			if (btn.getText() != "") {
		    				acabaram_as_fichas_adversario = false;
		    				break;
		    			}
		    		}
		    	}
		    	if (acabaram_as_fichas_adversario) {
		    		this.finalizarPartida(2);
		    	}
			}
			
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
	
	public void limparTabuleiro() {
		for (Casa elem : this.casas)
		{
			elem.setText("");
			elem.setDonoID(0);
			elem.setDono(null);
			elem.setBackground(null);
		}
	}
	
}
