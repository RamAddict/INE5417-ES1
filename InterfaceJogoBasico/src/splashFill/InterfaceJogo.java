package splashFill;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import Rede.AtorJogador;
import Rede.Move;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Action;

public class InterfaceJogo {

	//private JFrame frame;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private AtorJogador atorJogador;
	
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
    	if (!this.tabuleiro.isUmaPartidaAcabada())
    	{
	    	List<String> info = obterServidorEJogador();
	    	String servidor = info.get(0);
	    	String jogador = info.get(1);
	    	this.atorJogador = new AtorJogador(this);
			String mensagem = atorJogador.conectar(servidor, jogador);
			notificarResultado(mensagem);
    	}
	}
    
    public List<String> obterServidorEJogador(){
    	List<String> info = new ArrayList<String>();
    	String servidor = JOptionPane.showInputDialog("Qual servidor deseja se conectar?");
    	String jogador = JOptionPane.showInputDialog("Qual seu nome?");
    	
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
    	if (!this.tabuleiro.umaPartidaAcabada)
    	{
			String mensagem = atorJogador.desconectar();
			notificarResultado(mensagem);
			atualizarConsole(mensagem);
    	}
    }
    
    /**
     * Initiates game when button is clicked
     */
    public void iniciarPartida() {
    	if (!this.tabuleiro.umaPartidaAcabada)
    	{
	        String mensagem = atorJogador.iniciarPartida();
	        
	        if(mensagem.equals("Sucesso: solicitacao de inicio enviada a Netgames Server")) {
	
	        	this.tabuleiro.getJogador1().setTurn(true);
	        	this.tabuleiro.getJogador1().setColor(Color.RED);
	        }
	        notificarResultado(mensagem);
	        this.atualizarConsole(mensagem);
    	}
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
		if(this.tabuleiro.conectado) {
			Casa casa = tabuleiro.getCasa(coluna, linha);
			if (casa.getFichas() == -1) {
				notificarResultado("Algo deu errado, tente novamente");
			} else {
				if (this.tabuleiro.umaPartidaAcabada)
					rightTextArea.setText("O jogo acabou!, se quiser jogar de novo fecha e abre denovo");
				else
				if(tabuleiro.getJogador1().isTurn()) {
					/* Check if move is valid */
					boolean jogadaValida = this.tabuleiro.checaJogada(casa);
				      if(jogadaValida) {
				      	changeCounter(casa, linha, coluna);
				      	this.tabuleiro.realizaJogada(casa, this.atorJogador);
				      } else {
				      	rightTextArea.setText("Jogada Inválida!");
				      }
				      
				      atualizarConsole("Voce tem mais "+ tabuleiro.getJogador1().getPlays()+" fichas.");       
				} else {
					if (this.tabuleiro.partidaAndamento)
						atualizarConsole("Não é a sua vez de jogar :/ ");
					else
						atualizarConsole("Inicie uma partida primeiro");
				}
			}
		}
		else {
			notificarResultado("Conecte-se primeiro");
		}
      
	}
    
    public void changeCounter(Casa gb, int linha, int coluna) {
    	tabuleiro.getCasasAdjacentes(gb).size();
    	
//    	String str="";
//    	for (int i = 0; i <tabuleiro.getCasasAdjacentes(gb).size(); i++ ) {
//    		int lin = tabuleiro.getCasasAdjacentes(gb).get(i).getLinha();
//    		int col = tabuleiro.getCasasAdjacentes(gb).get(i).getColuna();
//    		str ="[linha "+lin+""+" coluna "+col+"]";
//    		System.out.println("casas adjjjjjjj "+str);
//    	}
//    	
    	boolean isSplash = false;
        ArrayList<Casa> adjacentes = tabuleiro.getCasasAdjacentes(gb);
        int fichaCasa = gb.getFichas();
        int numAdjacentes = adjacentes.size();
        if (numAdjacentes == 2) {
        	if(gb.avaliaSeCasaCheia()) {
        		isSplash = true;
        	} else {
        		gb.setFichas(1);
        		gb.setText("1");
        	}
        	
        } else if (numAdjacentes == 3)  { // num casas igual a 3
        	if(gb.avaliaSeCasaCheia()) {
        		isSplash = true;
        	} else if(fichaCasa == 1) {
        		gb.setFichas(2);
        		gb.setText("2");
        	} else {
        		gb.setFichas(1);
        		gb.setText("1");
        	}
        	
        	
        } else { // num casas igual a 4
        	if(gb.avaliaSeCasaCheia()) {
        		isSplash = true;
        	} else if(fichaCasa == 2) {
        		gb.setFichas(3);
        		gb.setText("3");
        	} else if(fichaCasa == 1) {
        		gb.setFichas(2);
        		gb.setText("2");
        	} else {
        		gb.setFichas(1);
        		gb.setText("1");
        	}
        
        }
        
        
        //TODO fix here
        //gb.setFichas(Integer.parseInt(nextclickcount));
        gb.setDonoID(tabuleiro.getJogador1().getId());
        gb.setDono(tabuleiro.getJogador1());
        gb.setBackground(tabuleiro.getJogador1().getColor());

        if(isSplash) {
        	gb.setFichas(0);
        	gb.setText("");
        	gb.setDonoID(0);
        	gb.setDono(null);
        	gb.setBackground(null);
    		splashFill(linha, coluna);
        }
    }
    
    public void splashFill(int linha, int coluna) {
    	if(linha >= 1) {
    		Casa cima = this.tabuleiro.getCasa(coluna, linha-1);
    		changeCounter(cima, linha-1, coluna);
    	} 
    	
    	if(linha < N-1) {
    		Casa baixo = this.tabuleiro.getCasa(coluna, linha+1);
    		changeCounter(baixo, linha+1, coluna);
    	} 
    	
    	if(coluna >= 1) {
    		Casa esquerda = this.tabuleiro.getCasa(coluna-1, linha);
    		changeCounter(esquerda, linha, coluna-1);
    	} 
    	
    	if(coluna < N-1) {
    		Casa direita = this.tabuleiro.getCasa(coluna+1, linha);
    		changeCounter(direita, linha, coluna+1);
    	}
    }
    
    
    public void atualizarTabuleiro(Move move) {
    	ArrayList<Casa> botoes = move.getBotoes();
    	
    	
    	for(int i = 0; i < botoes.size(); i++) {
    		Casa botao_tabuleiro = this.tabuleiro.getCasas().get(i);
    		Casa botao_remoto= botoes.get(i);
    		
    		botao_tabuleiro.setText(botao_remoto.getText());
    		if (botao_remoto.getDonoID() == 0) {
    			botao_tabuleiro.setText("");
    			botao_tabuleiro.setFichas(0);
    			botao_tabuleiro.setDono(null);
    			botao_tabuleiro.setBackground(null);
    			botao_tabuleiro.setDonoID(0);
    		} else {
	    		if(botao_tabuleiro.getDonoID() != botao_remoto.getDonoID()) {
	    			botao_tabuleiro.setDonoID(botao_remoto.getDonoID());
	    			botao_tabuleiro.setBackground(botao_remoto.getDono().getColor());
	    			botao_tabuleiro.setFichas(botao_remoto.getFichas());
	    		}
    		}
    		botao_tabuleiro.updateUI();
    	}
    	
    	int fichasJogador = tabuleiro.getJogador1().getPlays();
    	if (fichasJogador < 30) {
    		// this works
			if (fichasJogador == 0) {
				tabuleiro.finalizarPartida(1);
				return;
			}
	    	
		    	this.tabuleiro.setJogador1TemFichaNoTabuleiro(true);
		    	for (Casa btn : this.tabuleiro.getCasas()) {
		    		if (btn.getDonoID() == this.tabuleiro.getJogador1().getId()) {
		    			if (btn.getText() != "") {
		    				this.tabuleiro.setJogador1TemFichaNoTabuleiro(false);
		    				break;
		    			}
		    		}
		    	}
		    	if (this.tabuleiro.isJogador1TemFichaNoTabuleiro()) {
		    		this.tabuleiro.finalizarPartida(0);
		    	}
    	}

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
