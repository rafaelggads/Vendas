package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class AMenu extends JFrame implements ActionListener {
	JMenuBar menuBar;
	JMenu mnCadastro;
	JMenuItem mntmProduto;
	JMenuItem mntmFornecedor;
	JSeparator separator;
	JMenuItem mntmSair;
	JInternalFrame janelaAtual;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AMenu frame = new AMenu();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 441);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnCadastro = new JMenu("Cadastro");
		mnCadastro.setMnemonic('C');
		menuBar.add(mnCadastro);
		
		mntmProduto = new JMenuItem("Produto");
		mntmProduto.setMnemonic('P');
		mntmProduto.addActionListener(this);
		mnCadastro.add(mntmProduto);	
		
		mntmFornecedor=new JMenuItem("Fornecedor");
		mntmFornecedor.setMnemonic('F');
		mntmFornecedor.addActionListener(this);
		mnCadastro.add(mntmFornecedor);
		
		separator = new JSeparator();
		mnCadastro.add(separator);
		
		mntmSair = new JMenuItem("Sair");
		mntmSair.setMnemonic('S');
		mntmSair.addActionListener(this);
		mnCadastro.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent evento) 
	{
		if (evento.getSource() == mntmSair) {
			int resposta = JOptionPane
					.showConfirmDialog(this, "Confirma saída?", "Confirmação",
							JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION)
				this.dispose();
			
		} else if (evento.getSource() == mntmProduto) {
			this.janelaAtual = new AProduto();
			contentPane.add(janelaAtual);
			this.janelaAtual.setVisible(true);
		}
		else if(evento.getSource()==mntmFornecedor)
		{
			this.janelaAtual = new AFornecedor();
			contentPane.add(janelaAtual);
			this.janelaAtual.setVisible(true);
		}
		
		
	}

}
