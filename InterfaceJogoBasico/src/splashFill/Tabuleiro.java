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

public class Tabuleiro {
	private List<Casa> casas = new ArrayList<Casa>();
	private AtorJogador atorJogador;
	
	private Jogador jogador;
	private Jogador adversario;
	
	private JPanel matriz = new JPanel(new GridLayout(6, 6, 2, 2));
	
	public Tabuleiro() {
		
	}
	
	public Tabuleiro(List<Casa> casas) {
		this.casas = casas;
	}
	
//	public Tabuleiro iniciar(InterfaceJogo interJogo) {
//		Tabuleiro tabuleiro = new Tabuleiro();
//		
//		tabuleiro.criaMatriz(interJogo);
//		tabuleiro.setJogador1(null);
//		tabuleiro.setJogador2(null);
//		
//		
//		return tabuleiro;
//	}

	
	public void criarJogador(String name) {
		if(jogador == null) {
			jogador = new Jogador(name);
			jogador.iniciarJogador();
		} else {
			adversario = new Jogador(name);
			adversario.iniciarJogador();
		}
	}
	
	public void criarJogador() {
		if(jogador == null) {
			jogador = new Jogador("Jogador 1");
			jogador.iniciarJogador();
		} else {
			adversario = new Jogador("Jogador 2");
			adversario.iniciarJogador();
		}
	}
	
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
            	interJogo.click(linha, coluna, e);    
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
}
