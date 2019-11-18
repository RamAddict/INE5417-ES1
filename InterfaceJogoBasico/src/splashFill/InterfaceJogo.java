package splashFill;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
//import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import java.awt.Toolkit;

import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Rede.AtorJogador;
import Rede.Move;

import javax.swing.JMenu;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class InterfaceJogo {

	private JFrame frame;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private AtorJogador atorJogador = new AtorJogador(this);
	private Jogador jogador = new Jogador();
	
	private static final int N = 6;    

    private JMenuBar menuBar;
    private JTextArea rightTextArea;
    private JPanel panel;
    private JPanel panelMatriz;
    
    private Tabuleiro tabuleiro = new Tabuleiro();
    
    /**
     * Initiates the connection when the connect button is clicked
     */
    public void conectar() {
    	List<String> info = obterServidorEJogador();
    	String servidor = info.get(0);
    	String jogador = info.get(1);
		String mensagem = atorJogador.conectar(servidor, jogador);
		
		notificarResultado(mensagem);
		this.atualizarConsole(mensagem);
    }
    
    public List<String> obterServidorEJogador(){
    	List<String> info = new ArrayList<String>();
    	String servidor = JOptionPane.showInputDialog("Qual servidor deseja se conectar?");
    	String jogador = JOptionPane.showInputDialog("Qual seu nome?");
    	info.add(servidor);
    	info.add(jogador);
    	return info;
    }
    
    /**
     * Tell user about the result of an action
     * @param String mensagem
     */
    public void notificarResultado(String mensagem) {
    	JOptionPane.showMessageDialog(null, mensagem);
    }
    
    /**
     * Initiates disconnection process when button is clicked
     */
    public void desconectar() {
		String mensagem = atorJogador.desconectar();
		notificarResultado(mensagem);
		atualizarConsole(mensagem);
    }
    
    /**
     * Initiates game when button is clicked
     */
    public void iniciarPartida() {
        String mensagem = atorJogador.iniciarPartida();
        if(mensagem.equals("Sucesso: solicitacao de inicio enviada a Netgames Server")) {
        	this.jogador.setTurn(true);
        	//this.tabuleiro.setTurno(this.jogador);
        }
        notificarResultado(mensagem);
        this.atualizarConsole(mensagem);
    }
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceJogo window = new InterfaceJogo();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public JTextArea getRightTextArea() {
		return rightTextArea;
	}
	public void setRightTextArea(JTextArea rightTextArea) {
		this.rightTextArea = rightTextArea;
	}
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	/**
	 * Create the application.
	 */
	public InterfaceJogo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() {		
		
		JFrame frame = new JFrame("S P L A S H   F I L L !");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);        
        
        frame.getContentPane().add(criaPainel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);		
	}
	
	
	public JPanel criaPainel() {
		this.panel = new JPanel();
		

        //construct components
        JPanel matriz = criaMatriz();
        menuBar = criaMenu();
        
        rightTextArea = new JTextArea (25, 25);

        //adjust size and set layout
        panel.setPreferredSize (new Dimension (950, 580));
        BorderLayout layout = new BorderLayout(0, 0);
        panel.setLayout (layout);

        //add components
        panel.add(matriz, BorderLayout.CENTER);
        panel.add(menuBar, BorderLayout.NORTH);
        panel.add(rightTextArea, BorderLayout.EAST);
        
        return panel;
    }
	
	
	private JMenuBar criaMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mnNewMenu = new JMenu("Jogo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmConectar = new JMenuItem("conectar");
		mntmConectar.setAction(action);
		mnNewMenu.add(mntmConectar);
		
		JMenuItem mntmDesconectar = new JMenuItem("desconectar");
		mntmDesconectar.setAction(action_1);
		mnNewMenu.add(mntmDesconectar);
		
		JMenuItem mntmIniciarPartida = new JMenuItem("iniciar partida");
		mntmIniciarPartida.setAction(action_2);
		mnNewMenu.add(mntmIniciarPartida);
		
		return menuBar;
		
	}
	
	
	public void click(int linha, int coluna/*, ActionEvent e*/) {
		
		Casa gb = getBotaoClicado(linha, coluna);
		
		boolean turno = this.jogador.isTurn();
		
		if(turno) {
			/* Check if move is valid */
			boolean jogadaRealizada = this.tabuleiro.realizaJogada(gb, this.jogador, this.atorJogador);
		      if(jogadaRealizada) {
		      	changeCounter(gb, /*e,*/ linha, coluna);
		      } else {
		      	rightTextArea.setText("Jogada Inválida!");
		      }
		      
		      atualizarConsole("Voce clicou no botao " + (linha) + "x" + (coluna)+"\n Voce tem mais "+this.jogador.getPlays()+" movimentos.");       
		} else {
			atualizarConsole("Não é a sua vez de jogar :/ be patient my friend ");
		}
      
	}
    
    
    public void changeCounter(Casa gb, /*ActionEvent e,*/ int linha, int coluna) {
        
        String clicks = gb.getText();//((Casa)e.getSource()).getText();
        System.out.println(clicks);
        
        String nextclickcount = "";
        
        if(linha == 0 || linha == N-1) {
        	if(coluna == 0 || coluna == N-1) {
        		switch(clicks) {
                case "1":
                	nextclickcount = "";
                	splashFill(linha, coluna);
                	break;

                
                default: // no clicks
                	nextclickcount = "1";
                	break;
                }
        		
                //((JButton)e.getSource()).setText(nextclikcount);
        	} else {
        		switch(clicks) {
                case "1":
                	nextclickcount = "2";
                	break;
                
                case "2":
                	nextclickcount = "";
                	splashFill(linha, coluna);
                	break;
                	
                default: // no clicks
                	nextclickcount = "1";
                	break;
                }
        		//((JButton)e.getSource()).setText(nextclikcount);
        	}
        } else {
            switch(clicks) {
            case "1":
            	nextclickcount = "2";
            	break;
            	
            case "2":
            	nextclickcount = "3";
            	break;
            	
            case "3":
            	nextclickcount = "";
            	splashFill(linha, coluna);
            	break;
            
            default: // no clicks
            	nextclickcount = "1";
            	break;
            }
            //((JButton)e.getSource()).setText(nextclikcount);
        }
        //System.out.println("Mudando contagem");
        gb.setText(nextclickcount);
        //((Casa)e.getSource()).setText(nextclickcount);
    }
    
    public void splashFill(int linha, int coluna) {
    	if(linha >= 1) {
    		JButton cima = getBotaoClicado(linha-1, coluna);
    		//cima.doClick(); 
    		changeCounter((Casa)cima, linha, coluna);
    	}
    	
    	if(linha < N-1) {
    		JButton baixo = getBotaoClicado(linha+1, coluna);
    		//baixo.doClick();
    		changeCounter((Casa)baixo, linha, coluna);
    	}
    	
    	if(coluna >= 1) {
    		JButton esquerda = getBotaoClicado(linha, coluna-1);
    		//esquerda.doClick();
    		changeCounter((Casa)esquerda, linha, coluna);
    	}
    	
    	if(coluna < N-1) {
    		JButton direita = getBotaoClicado(linha, coluna+1);
    		//direita.doClick();
    		changeCounter((Casa)direita, linha, coluna);
    	}
    }
    
    
    public Casa getBotaoClicado(int r, int c) {
        int index = r * N + c;
        //System.out.println("r: "+r+" / c: "+c+" / index: "+index);
        
        return this.tabuleiro.getCasas().get(index);
    }
    
    public void atualizarTabuleiro(Move move) {
    	System.out.println("Atualizar tabuleiro");
    	List<Casa> botoes = move.getBotoes();
    	List<Casa> lista = getTabuleiro().getCasas();
    	for(int i = 0; i < botoes.size(); i++) {
    		lista.set(i, botoes.get(i));
    	}
    	//atualizarpontos();
    }
    
    public void atualizarConsole(String message) {
    	this.rightTextArea.setText(this.rightTextArea.getText() + "\n" + message);
    	return;
    }

    private JPanel criaMatriz() {
        this.panelMatriz = new JPanel(new GridLayout(N, N, 2, 2));
        for (int i = 0; i < N * N; i++) {
            int row = i / N;
            int col = i % N;
            //Casa gb = createButton(row, col);
            Casa gb = this.tabuleiro.createButton(row, col, this);
            
            this.panelMatriz.add(gb);
        }
        return this.panelMatriz;
    }
    
    
    
    /**-------------------------*/
	
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "conectar");
			putValue(SHORT_DESCRIPTION, "conectar a Netgames Server");
		}
		public void actionPerformed(ActionEvent e) {
			// Necessï¿½rio definir endereï¿½o do servidor e nome do jogador
			conectar();
		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "desconectar");
			putValue(SHORT_DESCRIPTION, "desconectar de Netgames Server");
		}
		public void actionPerformed(ActionEvent e) {
			desconectar();
		}
	}
	private class SwingAction_2 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "iniciar partida");
			putValue(SHORT_DESCRIPTION, "iniciar partida do seu jogo");
		}
		public void actionPerformed(ActionEvent e) {
                    iniciarPartida();
		}
	}
}
