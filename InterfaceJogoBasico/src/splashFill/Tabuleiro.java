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
	
	public Tabuleiro() {
		
	}
	
	public Tabuleiro(List<Casa> casas) {
		this.casas = casas;
	}

	public void limparTabuleiro() {
		//this.casas = 
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

	public JPanel criaMatriz(int N, InterfaceJogo interJogo) {
        JPanel p = new JPanel(new GridLayout(N, N, 2, 2));
        for (int i = 0; i < N * N; i++) {
            int row = i / N;
            int col = i % N;
            Casa gb = createButton(row, col, interJogo);
            casas.add(gb);
            p.add(gb);
            System.out.println("Criado botao "+i);
        }
        return p;
    }
	
	public Casa createButton(int linha, int coluna, InterfaceJogo interJogo) {
        //final JButton b = new JButton("");
    	final Casa b = new Casa();
        
        b.setPreferredSize(new Dimension(80, 80));

        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	interJogo.click(linha, coluna, e);
//                Casa gb = interJogo.getBotaoClicado(linha, coluna);
//                
//				/* Check if the move is valid */
//                if(atorJogador.checaJogada(gb, interJogo.getJogador())) {
//                	/* move */
//                	interJogo.changeCounter(gb, e, linha, coluna); 
//                } else {
//                	interJogo.getRightTextArea().setText("Jogada Inválida!");
//                	//interJogo.rightTextArea.setText("Jogada Inválida!");
//                }
//                
//                JOptionPane panel = new JOptionPane("Voce clicou no botao " + (linha) + "x" + (coluna));
//                panel.createDialog("clicado!");
//                System.out.println("Voce clicou no botao " + (linha) + "x" + (coluna));
//                interJogo.getRightTextArea().setText("Voce clicou no botao " + (linha) + "x" + (coluna)+"\n Voce tem mais "+interJogo.getJogador().getPlays()+" movimentos.");       
            }
        });
        this.casas.add(b);
        return b;
    }
}
