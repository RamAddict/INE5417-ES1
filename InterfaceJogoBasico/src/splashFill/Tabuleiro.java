package splashFill;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Rede.AtorJogador;
import Rede.Move;

public class Tabuleiro{
	protected ArrayList<Casa> casas = new ArrayList<Casa>();
	protected AtorJogador atorJogador;
	
	protected Jogador jogador;
	protected Jogador adversario;
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;

	protected boolean umaPartidaAcabada = false;

	protected boolean jogador1TemFichaNoTabuleiro;
	protected int qtdFichaJogador1;
	protected boolean jogador2TemFichaNoTabuleiro;
	protected int qtdFichaJogador2;
	
	
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
	
	
	public boolean isJogador1TemFichaNoTabuleiro() {
		return jogador1TemFichaNoTabuleiro;
	}

	public void setJogador1TemFichaNoTabuleiro(boolean jogador1TemFichaNoTabuleiro) {
		this.jogador1TemFichaNoTabuleiro = jogador1TemFichaNoTabuleiro;
	}

	public int getQtdFichaJogador1() {
		return qtdFichaJogador1;
	}

	public void setQtdFichaJogador1(int qtdFichaJogador1) {
		this.qtdFichaJogador1 = qtdFichaJogador1;
	}

	public boolean isJogador2TemFichaNoTabuleiro() {
		return jogador2TemFichaNoTabuleiro;
	}

	public void setJogador2TemFichaNoTabuleiro(boolean jogador2TemFichaNoTabuleiro) {
		this.jogador2TemFichaNoTabuleiro = jogador2TemFichaNoTabuleiro;
	}

	public int getQtdFichaJogador2() {
		return qtdFichaJogador2;
	}

	public void setQtdFichaJogador2(int qtdFichaJogador2) {
		this.qtdFichaJogador2 = qtdFichaJogador2;
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
			setQtdFichaJogador1(fichasJogador-1);

			adversario.setTurn(true);
		} else
		{
			jogador.setTurn(true);

			adversario.setTurn(false);
			adversario.setPlays(fichasAdversario-1);
			setQtdFichaJogador2(fichasAdversario-1);
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
				setJogador2TemFichaNoTabuleiro(true);
		    	for (Casa btn : this.getCasas()) {
		    		if (btn.getDonoID() != this.getJogador1().getId()) {
		    			if (btn.getFichas() != 0) {
		    				setJogador2TemFichaNoTabuleiro(false);
		    				break;
		    			}
		    		}
		    	}
		    	if (isJogador2TemFichaNoTabuleiro()) {
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
			JOptionPane.showMessageDialog(null, "entrou na casa podre");
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
			elem.setFichas(0);
			elem.setDonoID(0);
			elem.setDono(null);
			elem.setBackground(null);
		}
	}
	
	public ArrayList<Casa> getCasasAdjacentes(Casa casa) {
		int linha = casa.getLinha();
		int coluna = casa.getColuna();
		ArrayList<Casa> casas = new ArrayList<Casa>();
		Casa home;
		
		int index = linha*6+coluna;
//		System.out.println("indexxxx "+index);
		switch (index) {
			case 0:case 5:case 30:case 35: {
				switch(index) {
					case 0:{
//						System.out.println("entrou no index 0000000000");
						coluna++;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						linha++;
						coluna--;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						break;
					}
					case 30:{
						linha--;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						coluna++;
						linha++;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						break;
					}
					case 5:{
						coluna--;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						coluna++;
						linha++;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						break;
					}
					case 35:{
						linha--;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						coluna--;
						linha++;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						break;
					}
			}
				break;
			}
			case 1:case 2:case 3:case 4:case 6:case 12:case 18:case 24:case 11:case 17:case 23:case 29:case 31:case 32:case 33:case 34: {
					switch(index) {
					case 6:case 12:case 18:case 24:{//lateral esquerda
						coluna++;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						coluna--;
						linha++;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						linha = linha - 2;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						break;
					}
					
					case 11:case 17:case 23:case 29:{ //lateral direita
						coluna--;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						coluna++;
						linha++;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						linha = linha - 2;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						break;
					}
						case 1:case 2:case 3:case 4:{/// barra superior
						linha++;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						linha--;
						coluna++;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						coluna = coluna - 2;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						break;
					}
						case 31:case 32:case 33:case 34:{/// barra inferior
						linha--;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						linha++;
						coluna++;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						coluna = coluna - 2;
						home = this.getCasa(coluna, linha);
						casas.add(home);
						break;
					}
					
					
			
			}
				break;
			}
			default:{// caso em que fica em alguma parte do meio do tabuleiro
				linha--;
				home = this.getCasa(coluna, linha);
				casas.add(home);
				linha = linha + 2;
				home = this.getCasa(coluna, linha);
				casas.add(home);
				coluna++;
				linha--;
				home = this.getCasa(coluna, linha);
				casas.add(home);
				coluna = coluna - 2;
				home = this.getCasa(coluna, linha);
				casas.add(home);

				break;
		
			}
		
		}
		
		
		
		return casas;
	}
	
	<E> int getSizeArrayList(ArrayList<E> list) {
		return list.size();
	}
	
	
}
