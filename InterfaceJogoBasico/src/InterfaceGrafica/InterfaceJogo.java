package InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
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

public class InterfaceJogo {

	private JFrame frame;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private AtorJogador atorJogador;
	
	private final int btnWidth = 80;
	private final int btnHeight = 80;	
	
	private static final int N = 8;
    private final List<JButton> list = new ArrayList<JButton>();

    private JMenuBar menuBar;
    private JTextArea leftTextArea;
    
    
    
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
		
		
		JFrame frame = new JFrame ("Splash Fill!");
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
        
        leftTextArea = new JTextArea (25, 25);

        //adjust size and set layout
        panel.setPreferredSize (new Dimension (950, 580));
        BorderLayout layout = new BorderLayout(0, 0);
        panel.setLayout (layout);

        //add components
        panel.add(matriz, BorderLayout.CENTER);
        panel.add(menuBar, BorderLayout.NORTH);
        panel.add(leftTextArea, BorderLayout.EAST);
        
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
        return list.get(index);
        
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
        
        /* APENAS PARA TESTE DE BOTAO*/
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton gb = getBotaoClicado(linha, coluna);
                JOptionPane panel = new JOptionPane("Voce clicou no botao " + (linha+1) + "x" + (coluna+1));
                panel.createDialog("clicado!");
                System.out.println("Voce clicou no botao " + (linha+1) + "x" + (coluna+1));
                leftTextArea.setText("Voce clicou no botao " + (linha+1) + "x" + (coluna+1));
            }
        });
        return b;
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

    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GridButtonPanel().display();
            }
        });
    } */
	
	/**/
	
	
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
