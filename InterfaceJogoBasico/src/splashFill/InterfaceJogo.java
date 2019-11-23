package splashFill;

import java.awt.BorderLayout;
import java.awt.Color;
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

import com.retrogui.messageserver.common.SysUtils;

import Rede.AtorJogador;
import Rede.Move;

import javax.swing.JMenu;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
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
	private AtorJogador atorJogador;
//	private Jogador jogador = new Jogador();
	
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
    	this.atorJogador = new AtorJogador(this);
		String mensagem = atorJogador.conectar(servidor, jogador);
		notificarResultado(mensagem);
		this.atualizarConsole(mensagem);
    }
    
    public List<String> obterServidorEJogador(){
    	List<String> info = new ArrayList<String>();
    	//String servidor = JOptionPane.showInputDialog("Qual servidor deseja se conectar?");
    	//String jogador = JOptionPane.showInputDialog("Qual seu nome?");
    	String servidor = "localhost";
    	String jogador = "suaMae";
    	
    	tabuleiro.criarJogador(jogador);
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
//        	this.jogador.setTurn(true);
        	this.tabuleiro.getJogador1().setTurn(true);
        	this.tabuleiro.getJogador1().setColor(Color.RED);
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
	
	

//	public Jogador getJogador() {
//		return jogador;
//	}
//	public void setJogador(Jogador jogador) {
//		this.jogador = jogador;
//	}
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
	
	
	public void click(int linha, int coluna) {
		//TODO dentro desse if colocar um aviso de "voce não esta conectado" ou "jogando em modo jogador unico"
		
		if(!this.tabuleiro.conectado) {
			Casa casa = tabuleiro.getCasa(coluna, linha); // delet this if
			changeCounter(casa, linha, coluna);
		} else {
			Casa casa = tabuleiro.getCasa(coluna, linha);
			if (casa.getFichas() == -1)
				JOptionPane.showMessageDialog(null, "CASA PODRE");
			if(tabuleiro.getJogador1().isTurn()) {
				/* Check if move is valid */
				boolean jogadaValida = this.tabuleiro.checaJogada(casa);
			      if(jogadaValida) {
			      	changeCounter(casa, linha, coluna);
			      	this.tabuleiro.realizaJogada(casa, this.atorJogador);
			      } else {
			      	rightTextArea.setText("Jogada Inválida!");
			      }
			      
			      atualizarConsole("Voce clicou no botao " + (linha) + "x" + (coluna)+"\n Voce tem mais "+ tabuleiro.getJogador1().getPlays()+" movimentos.");       
			} else {
				atualizarConsole("Não é a sua vez de jogar :/ be patient my friend ");
			}
		}
      
	}
	
	public void clickSplash(int linha, int coluna) {
//		if(!this.tabuleiro.conectado) { //TODO dentro desse if colocar um aviso de "voce não esta conectado" ou "jogando em modo jogador unico"
//			Casa casa = tabuleiro.getCasa(coluna, linha);
//			changeCounter(casa, linha, coluna);
//		} else {
//			Casa casa = tabuleiro.getCasa(coluna, linha);
//			if(tabuleiro.getJogador1().isTurn()) {
//				/* Check if move is valid */
//				boolean jogadaValida = this.tabuleiro.checaJogada(casa);
//			      if(jogadaValida) {
//			      	changeCounter(casa, linha, coluna);
//			      	//this.tabuleiro.realizaJogada(casa, this.atorJogador);
//			      } else {
//			      	rightTextArea.setText("Jogada Inválida!");
//			      }
//
//			      atualizarConsole("Voce clicou no botao " + (linha) + "x" + (coluna)+"\n Voce tem mais "+ tabuleiro.getJogador1().getPlays()+" movimentos.");       
//			} else {
//				atualizarConsole("Não é a sua vez de jogar :/ be patient my friend ");
//			}
//		}
		
		
		Casa casa = tabuleiro.getCasa(coluna, linha);
      	changeCounter(casa, linha, coluna);
	      	//this.tabuleiro.realizaJogada(casa, this.atorJogador);
	}
    
    
    public void changeCounter(Casa gb, int linha, int coluna) {
        boolean isSplash = false;
        String clicks = gb.getText();
        //System.out.println(clicks);
        
        String nextclickcount = "";
        
        if(linha == 0 || linha == N-1) {
        	if(coluna == 0 || coluna == N-1) {
        		switch(clicks) {
                case "1":
                	nextclickcount = "";
                	//splashFill(linha, coluna);
                	isSplash = true;
                	break;

                
                default: // no clicks
                	nextclickcount = "1";
                	break;
                }
        		
        	} else {
        		switch(clicks) {
                case "1":
                	nextclickcount = "2";
                	break;
                
                case "2":
                	nextclickcount = "";
                	//splashFill(linha, coluna);
                	isSplash = true;
                	break;
                	
                default: // no clicks
                	nextclickcount = "1";
                	break;
                }
        		
        	}
        } else if(coluna == 0 || coluna == N-1 && (linha != 0 && linha != N-1)) {
        	switch(clicks) {
            case "1":
            	nextclickcount = "2";
            	break;
            
            case "2":
            	nextclickcount = "";
            	//splashFill(linha, coluna);
            	isSplash = true;
            	break;
            	
            default: // no clicks
            	nextclickcount = "1";
            	break;
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
            	//splashFill(linha, coluna);
            	isSplash = true;
            	break;
            
            default: // no clicks
            	nextclickcount = "1";
            	break;
            }
            
        }
        
        gb.setText(nextclickcount);
        //gb.setFichas(Integer.parseInt(nextclickcount)); !!!!!!!!
        gb.setDonoID(tabuleiro.getJogador1().getId());
        gb.setDono(tabuleiro.getJogador1());
        gb.setBackground(tabuleiro.getJogador1().getColor());

        if(isSplash) {
//        	int idx = gb.getColuna() + gb.getLinha()*6;
//        	Casa casa = this.tabuleiro.getCasa(coluna, linha);
        	gb.setDonoID(0);
        	gb.setDono(null);
        	gb.setBackground(null);
//        	this.tabuleiro.getCasas().get(idx).setBackground(null);
//        	this.tabuleiro.getCasas().get(idx).setDono(null);
//        	this.tabuleiro.getCasas().get(idx).setDonoID(0);
//    		this.tabuleiro.casas.get(idx).setFichas(casa.getFichas());
//    		this.tabuleiro.casas.get(idx).setText(casa.getText());
//        	this.tabuleiro.setCasa(casa);
    		splashFill(linha, coluna);
        }
    }
    
    public void splashFill(int linha, int coluna) {
    	int idx = coluna + linha;
    	if(linha >= 1) {
    		JButton cima = this.tabuleiro.getCasa(coluna, linha-1);
    		cima.setBackground(this.tabuleiro.getJogador1().getColor());
//    		this.tabuleiro.getCasas().get(idx).setBackground(g); --
    		System.out.println("em cima de clicksplash cima");
    		//cima.doClick();
    		clickSplash(linha-1,coluna);
    		//changeCounter((Casa)cima, linha, coluna);
    		System.out.println("em baixo de clicksplash cima");
    	} 
    	
    	if(linha < N-1) {
    		JButton baixo = this.tabuleiro.getCasa(coluna, linha+1);
    		baixo.setBackground(this.tabuleiro.getJogador1().getColor());
    		//baixo.doClick();
    		System.out.println("em cima de clicksplash baixo");
    		clickSplash(linha+1, coluna);
    		System.out.println("em baixo de clicksplash baixo");
    		//changeCounter((Casa)baixo, linha, coluna);
    	} 
    	
    	if(coluna >= 1) {
    		JButton esquerda = this.tabuleiro.getCasa(coluna-1, linha);
    		esquerda.setBackground(this.tabuleiro.getJogador1().getColor());
    		//esquerda.doClick();
    		clickSplash(linha, coluna-1);
//    		changeCounter((Casa)esquerda, linha, coluna);
    	} 
    	
    	if(coluna < N-1) {
    		JButton direita = this.tabuleiro.getCasa(coluna+1, linha);
    		direita.setBackground(this.tabuleiro.getJogador1().getColor());
    		//direita.doClick();
    		clickSplash(linha, coluna+1);
//    		changeCounter((Casa)direita, linha, coluna);
    	}
    }
    
    
//    public Casa getBotaoClicado(int r, int c) {
//        int index = r * N + c;
//        //System.out.println("r: "+r+" / c: "+c+" / index: "+index);
//        
//        return this.tabuleiro.getCasas().get(index);
//    }
    
    
    public boolean isDiferente(Casa essa, Casa outra) {
    	boolean diferente = false;
    	
    	return diferente;
    }
    
    public void atualizarTabuleiro(Move move) {
    	ArrayList<Casa> botoes = move.getBotoes();
    	
    	for(int i = 0; i < botoes.size(); i++) {
    		Casa botao_tabuleiro = this.tabuleiro.getCasas().get(i);
    		Casa botao_remoto= botoes.get(i);
    		
    		botao_tabuleiro.setText(botao_remoto.getText());
    		if (botao_remoto.getDonoID() == 0)
    		{
    			botao_tabuleiro.setText("");
    			botao_tabuleiro.setDono(null);
    			botao_tabuleiro.setBackground(null);
    		}
    		else {
	    		if(botao_tabuleiro.getDonoID() != botao_remoto.getDonoID()) {
	    			botao_tabuleiro.setDonoID(botao_remoto.getDonoID());
	    			botao_tabuleiro.setBackground(botao_remoto.getDono().getColor());
	    		} else
	    		{
//	    			botao_tabuleiro.setBackground();
	    			System.out.println(("eu n deveria printar?"));
	    			System.out.println(botao_remoto.getLinha()+ "," + botao_remoto.getColuna() +" txt :"+ botao_remoto.getText());
	    		}
    		}
    	}
    	
    	for (Casa btn : this.tabuleiro.getCasas())
    	{
//    		btn();
    	}
    	
    	//System.out.println("Eu sai desse atualizar podre");
    }
    
    public void atualizarConsole(String message) {
    	this.rightTextArea.setText(this.rightTextArea.getText() + "\n" + message);
    	return;
    }

    private JPanel criaMatriz() {
        this.panelMatriz = new JPanel(new GridLayout(N, N, 2, 2));
        for (int i = 0; i < N * N; i++) {
            int row = i / N; //
            int col = i % N;
//            Casa gb = createButton(row, col);
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
