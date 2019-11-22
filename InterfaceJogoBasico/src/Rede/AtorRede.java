package Rede;

import java.awt.Color;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorRede implements OuvidorProxy {
	static final long serialVersionUID = 1L;
	protected Proxy proxy;
	
	protected OuvidorProxy ouvidor;
	
	
	protected AtorJogador atorJogador;
	protected boolean playersTurn;
	
	public AtorRede(AtorJogador atorJogador) {
		super();
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
		this.atorJogador = atorJogador;
		
	}
	
	public String conectar(String servidor, String nome) {
			try {
				this.proxy.conectar(servidor, nome);
			} catch (JahConectadoException e) {
				//JOptionPane.showMessageDialog(atorJogador.getFrame(), e.getMessage());
				e.printStackTrace();
				return "Voce ja esta conectado";
			} catch (NaoPossivelConectarException e) {
				//JOptionPane.showMessageDialog(atorJogador.getFrame(), e.getMessage());
				e.printStackTrace();
				return "Nao foi possivel conectar";
			} catch (ArquivoMultiplayerException e) {
				//JOptionPane.showMessageDialog(atorJogador.getFrame(), e.getMessage());
				e.printStackTrace();
				return "Voce esqueceu o arquivo de propriedades";
			}
			return "Sucesso: conectado a Netgames Server";
		
	}

	public String desconectar() {
			try {
				proxy.desconectar();
			} catch (NaoConectadoException e) {
				//JOptionPane.showMessageDialog(atorJogador.getFrame(), e.getMessage());
				e.printStackTrace();
				return "Voce nao esta conectado";
			}
			return "Sucesso: desconectado de Netgames Server";
	}

	public String iniciarPartida() {
		try {
			proxy.iniciarPartida(new Integer(2));
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Falha ao tentar enviar solicitacao de inicio enviada a Netgames Server";
		}
		return "Sucesso: solicitacao de inicio enviada a Netgames Server";
	}

	
	/* METODOS A REFAZER */
	
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		this.atorJogador.tratarIniciarPartida(posicao);
		if(posicao == 1) {
			this.atorJogador.interfaceJogo.getTabuleiro().getJogador1().setColor(Color.RED);
		} else { //posicao == 2
			this.atorJogador.interfaceJogo.getTabuleiro().getJogador1().setColor(Color.BLUE);
		}
		this.atorJogador.interfaceJogo.getTabuleiro().getJogador1().setId(posicao);
		System.out.println("to setando a merda do id" + posicao + "setei pra " + this.atorJogador.interfaceJogo.getTabuleiro().getJogador1().getId());
		//proxy.iniciarNovaPartida(posicao);
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub
		this.atorJogador.finalizarPartidaComErro();
		//proxy.finalizarPartidaComErro(message);
	}

	@Override
	public void receberMensagem(String msg) {
		
		// TODO Auto-generated method stub
		//proxy.receberMensagem(msg);
	}

	
	public void enviarJogada(Move move) {
		System.out.println("enviar jogada");
		try {
			proxy.enviaJogada(move);
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String informarNomeAdversario(String idUsuario) {
        String tmp1 = proxy.obterNomeAdversario(1);
        String tmp2 = proxy.obterNomeAdversario(2);
        if (tmp1.equals(idUsuario)){
                return tmp2;
        } else {
                return tmp1;
        }	
    }
	
	@Override
	public void receberJogada(Jogada jogada) {
		Move move = (Move) jogada;
		atorJogador.receberJogada(move);
		
	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
		this.atorJogador.tratarConexaoPerdida();
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub
		
	}
	

}
