package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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

import ElementosInterface.*;

public class InterfaceJogo {

	private JFrame frame;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private AtorJogador atorJogador;
	
	private final int btnWidth = 80;
	private final int btnHeight = 80;	
	
	private static final int N = 6;
    private final List<JButton> list = new ArrayList<JButton>();
    
    private final List<Botao> listab = new ArrayList<Botao>();

    private JMenuBar menuBar;
    private JTextArea rightTextArea;
    
    
    
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
		atorJogador = new AtorJogador();
		
		
		JFrame frame = new JFrame("P O O L P Y   H A Z A R D !");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        
        
        frame.getContentPane().add(criaPainel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		        
		/* Perguntando que tipo de jogo
		JOptionPane opcoesJogo = new JOptionPane("Escolha seu tipo de jogo:");
		opcoesJogo.setBounds(200, 100, 300, 40);
		
		opcoesJogo.createDialog(".");
        */
		
	}
	
	public JPanel criaPainel() {
		JPanel panel = new JPanel();
		

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
	
	
	
	/**/


    private JButton getBotaoClicado(int r, int c) {
        int index = r * N + c;
        System.out.println("r: "+r+" / c: "+c+" / index: "+index);
        return list.get(index);
        
        //return listab.get(index)
        
        /*
         a comparacao de botoes pode ser feita com 
         (b == gb)
         ou com
         (b.equals(gb))
         */
    }

    private JButton createButton(int linha, int coluna) {
        final JButton b = new JButton("");
        
        b.setPreferredSize(new Dimension(btnHeight, btnWidth));
        
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton gb = getBotaoClicado(linha, coluna);
            	
                JOptionPane panel = new JOptionPane("Voce clicou no botao " + (linha) + "x" + (coluna));
                panel.createDialog("clicado!");
                System.out.println("Voce clicou no botao " + (linha) + "x" + (coluna));
                rightTextArea.setText("Voce clicou no botao " + (linha) + "x" + (coluna));
                
                changeCounter(gb, e, linha, coluna);        
            }
        });
        return b;
    }
    
    private void changeCounter(JButton gb, ActionEvent e, int linha, int coluna) {
    	//TODO fazer pegar a cor atual do botao
        changeImage("color");
        
        String clicks = ((JButton)e.getSource()).getText();
        System.out.println(clicks);
        
        String nextclikcount = "";
        
        if(linha == 0 || linha == N-1) {
        	if(coluna == 0 || coluna == N-1) {
        		switch(clicks) {
                case "1":
                	nextclikcount = "";
                	splashFill(linha, coluna);
                	break;

                
                default: // no clicks
                	nextclikcount = "1";
                	break;
                }
                ((JButton)e.getSource()).setText(nextclikcount);
        	} else {
        		switch(clicks) {
                case "1":
                	nextclikcount = "2";
                	break;
                
                case "2":
                	nextclikcount = "";
                	splashFill(linha, coluna);
                	break;
                	
                default: // no clicks
                	nextclikcount = "1";
                	break;
                }
        		((JButton)e.getSource()).setText(nextclikcount);
        	}
        } else {
            switch(clicks) {
            case "1":
            	nextclikcount = "2";
            	break;
            	
            case "2":
            	nextclikcount = "3";
            	break;
            	
            case "3":
            	nextclikcount = "";
            	splashFill(linha, coluna);
            	break;
            
            default: // no clicks
            	nextclikcount = "1";
            	break;
            }
            ((JButton)e.getSource()).setText(nextclikcount);
        }
    }
    
    private void changeImage(String color) {
    	//checa quantos "pontos" tem no botao
    	String image_path = ""; 
    	/**
    	 * switch(pontos){
    	 * case 0:
    	 * 	image_path+="1";
    	 * 	break;
    	 * 	
    	 * case 1:
    	 * 	image_path+="2";
    	 * 	break;
    	 * 
    	 * case 2:
    	 * 	image_path+="3";
    	 * 	break;
    	 * 
    	 * case 3:
    	 *	removeImage();
    	 *	splashFill();
    	 * break;
    	 */
    }
    
    private void splashFill(int linha, int coluna) {
    	if(linha > 1) {
    		JButton cima = getBotaoClicado(linha-1, coluna);
    		cima.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton gb = getBotaoClicado(linha, coluna);
                	
                    JOptionPane panel = new JOptionPane("Voce clicou no botao " + (linha) + "x" + (coluna));
                    panel.createDialog("clicado!");
                    System.out.println("Voce clicou no botao " + (linha) + "x" + (coluna));
                    rightTextArea.setText("Voce clicou no botao " + (linha) + "x" + (coluna));
                    
                    changeCounter(gb, e, linha, coluna);        
                }
            });
    		
    	}
    	
    	if(linha < N-1) {
    		JButton baixo = getBotaoClicado(linha+1, coluna);
    		baixo.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton gb = getBotaoClicado(linha, coluna);
                	
                    JOptionPane panel = new JOptionPane("Voce clicou no botao " + (linha) + "x" + (coluna));
                    panel.createDialog("clicado!");
                    System.out.println("Voce clicou no botao " + (linha) + "x" + (coluna));
                    rightTextArea.setText("Voce clicou no botao " + (linha) + "x" + (coluna));
                    
                    changeCounter(gb, e, linha, coluna);        
                }
            });
    	}
    	
    	if(coluna > 1) {
    		JButton esquerda = getBotaoClicado(linha, coluna-1);
    		esquerda.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton gb = getBotaoClicado(linha, coluna);
                	
                    JOptionPane panel = new JOptionPane("Voce clicou no botao " + (linha) + "x" + (coluna));
                    panel.createDialog("clicado!");
                    System.out.println("Voce clicou no botao " + (linha) + "x" + (coluna));
                    rightTextArea.setText("Voce clicou no botao " + (linha) + "x" + (coluna));
                    
                    changeCounter(gb, e, linha, coluna);        
                }
            });
    	}
    	
    	if(coluna < N-1) {
    		JButton direita = getBotaoClicado(linha, coluna+1);
    		direita.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton gb = getBotaoClicado(linha, coluna);
                	
                    JOptionPane panel = new JOptionPane("Voce clicou no botao " + (linha) + "x" + (coluna));
                    panel.createDialog("clicado!");
                    System.out.println("Voce clicou no botao " + (linha) + "x" + (coluna));
                    rightTextArea.setText("Voce clicou no botao " + (linha) + "x" + (coluna));
                    
                    changeCounter(gb, e, linha, coluna);        
                }
            });
    	}
    }
    

    private JPanel criaMatriz() {
        JPanel p = new JPanel(new GridLayout(N, N, 2, 2));
        for (int i = 0; i < N * N; i++) {
            int row = i / N;
            int col = i % N;
            JButton gb = createButton(row, col);
            list.add(gb);
            p.add(gb);
        }
        return p;
    }	
	
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
			// Necessário definir endereço do servidor e nome do jogador
			String mensagem = atorJogador.conectar("localhost", "nomeJogador?");
			JOptionPane.showMessageDialog(null, mensagem);
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
			String mensagem = atorJogador.desconectar();
			JOptionPane.showMessageDialog(null, mensagem);
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
			String mensagem = atorJogador.iniciarPartida();
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
}
