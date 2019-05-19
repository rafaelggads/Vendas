package apresentacao;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import negocio.NFornecedor;
import negocio.NProduto;
import negocio.NUnidade;
import entidade.EFornecedor;
import entidade.EProduto;
import entidade.EUnidade;

public class AProduto extends JInternalFrame implements ActionListener,
		MouseListener {

	private static final long serialVersionUID = 88073612254428227L;
	private JTextField txtCodigo;
	private JTextField txtNomeProduto;
	private JComboBox cboUnidade;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JScrollPane scrollPane;
	private JTable tblProduto;
	private JComboBox cboFornecedor;

	private NProduto nProduto;
	private NUnidade nUnidade;
	private NFornecedor nFornecedor;

	// private NFornecedor nFornecedor;

	/**
	 * Create the frame.
	 */
	public AProduto() {
		nProduto = new NProduto();
		nUnidade = new NUnidade();
		nFornecedor = new NFornecedor();

		setClosable(true);

		setTitle("Cadastro de produtos");
		setBounds(100, 100, 388, 324);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(10, 11, 56, 14);
		getContentPane().add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(86, 8, 76, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEditable(false);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 42, 56, 14);
		getContentPane().add(lblNome);

		txtNomeProduto = new JTextField();
		txtNomeProduto.setBounds(86, 39, 252, 20);
		getContentPane().add(txtNomeProduto);
		txtNomeProduto.setColumns(10);

		JLabel lblUnidade = new JLabel("Unidade:");
		lblUnidade.setBounds(10, 69, 56, 14);
		getContentPane().add(lblUnidade);

		this.preencheComboUnidade();
		
		getContentPane().add(cboUnidade);

		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(10, 129, 347, 36);
		getContentPane().add(panelBotoes);
		panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnIncluir = new JButton("Incluir");
		btnIncluir.setToolTipText("Inclui um registro.");
		panelBotoes.add(btnIncluir);
		btnIncluir.addActionListener(this);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setToolTipText("Altera um registro.");
		btnAlterar.addActionListener(this);
		panelBotoes.add(btnAlterar);
		btnAlterar.setEnabled(false);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setToolTipText("Exclui um registro.");
		panelBotoes.add(btnExcluir);
		btnExcluir.addActionListener(this);
		btnExcluir.setEnabled(false);

		btnSair = new JButton("Sair");
		btnSair.setToolTipText("Sai do cadastro de produtos.");
		panelBotoes.add(btnSair);
		btnSair.addActionListener(this);

		String[] colunasNomes = { "Código", "Nome", "Unidade" ,"Fornecedor"};

		Object[][] data = new Object[][] {};

		DefaultTableModel modelo = new DefaultTableModel(data, colunasNomes);
		tblProduto = new JTable(modelo) {
			private static final long serialVersionUID = -165272208553532548L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		tblProduto.setToolTipText("Selecione o produto para edit\u00E1-lo.");
		tblProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProduto.setPreferredScrollableViewportSize(new Dimension(500, 200));
		tblProduto.addMouseListener(this);

		scrollPane = new JScrollPane(tblProduto);
		getContentPane().add(scrollPane);
		scrollPane.setBounds(10, 176, 350, 111);

		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(10, 104, 67, 14);
		getContentPane().add(lblFornecedor);

		cboFornecedor = new JComboBox();		
		this.preencheComboFornecedor();
		cboFornecedor.setBounds(86, 101, 252, 20);
		getContentPane().add(cboFornecedor);

		this.preencherTabela();
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(btnIncluir)) {
			if (validarEntrada())
				this.adicionarProduto();
		}

		if (evento.getSource().equals(btnAlterar)) {
			if (validarEntrada())
				this.alterarProduto();
		}

		if (evento.getSource().equals(btnExcluir)) {

			int resposta = JOptionPane.showConfirmDialog(this,
					"Confirma remoção do produto?", "Confirmação",
					JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION)
				this.removerProduto();
		}

		if (evento.getSource().equals(btnSair)) {
			this.dispose();
		}
	}

	private boolean validarEntrada() {

		NProduto nProduto = new NProduto();
		/*
		 * EProduto eProduto = new EProduto(Integer.parseInt(this.txtCodigo
		 * .getText().length() == 0 ? "0" : this.txtCodigo.getText()),
		 * this.txtNomeProduto.getText(),
		 * String.valueOf(this.cboUnidade.getSelectedIndex()));
		 */
		EFornecedor eFornecedor = new EFornecedor();
		eFornecedor.setCodigo(this.cboFornecedor.getSelectedIndex());

		EUnidade eUnidade = new EUnidade();
		eUnidade.setId(this.cboUnidade.getSelectedIndex());
		EProduto eProduto = new EProduto(Integer.parseInt(this.txtCodigo
				.getText().length() == 0 ? "0" : this.txtCodigo.getText()),
				this.txtNomeProduto.getText(), eUnidade, eFornecedor);
		try {
			nProduto.validarEntrada(eProduto);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Validação",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void adicionarProduto() {
		DefaultTableModel modelo = (DefaultTableModel) this.tblProduto
				.getModel();
		EProduto eProduto;

		int codigo = 0;
		String nomeProduto = this.txtNomeProduto.getText();
		// String unidade = this.cboUnidade.getSelectedItem().toString();
		EUnidade eUnidade = new EUnidade();
		eUnidade.setId(this.cboUnidade.getSelectedIndex());

		EFornecedor eFornecedor = new EFornecedor();
		eFornecedor.setCodigo(this.cboFornecedor.getSelectedIndex());

		eProduto = new EProduto(0, nomeProduto, eUnidade, eFornecedor);
		try {
			codigo = this.nProduto.incluir(eProduto);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String novaLinha[] = { String.valueOf(codigo), nomeProduto,
				eUnidade.getSigla() };
		modelo.addRow(novaLinha);

		this.limparCampos();
		JOptionPane.showMessageDialog(this, "Registro incluído!", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void limparCampos() {
		this.txtCodigo.setText("");
		this.txtNomeProduto.setText("");

		if (this.cboUnidade.getItemCount() > 0)
			this.cboUnidade.setSelectedIndex(0);

		if (this.cboFornecedor.getItemCount() > 0)
			this.cboFornecedor.setSelectedIndex(0);
		this.txtCodigo.requestFocus();
		this.btnIncluir.setEnabled(true);
		this.btnAlterar.setEnabled(false);
		this.btnAlterar.setEnabled(false);
	}

	@Override
	public void mouseClicked(MouseEvent evento) {
		if (evento.getSource().equals(this.tblProduto))
			this.carregarDadosFormulario(this.tblProduto.getSelectedRow());
	}

	private void carregarDadosFormulario(int linha) {
		DefaultTableModel modelo = (DefaultTableModel) this.tblProduto
				.getModel();

		String codigo = modelo.getValueAt(linha, 0).toString();
		String nomeProduto = modelo.getValueAt(linha, 1).toString();
		String unidade = modelo.getValueAt(linha, 2).toString();
		String fornecedor = modelo.getValueAt(linha, 3).toString();

		this.txtCodigo.setText(codigo);
		this.txtNomeProduto.setText(nomeProduto);

		// Sincroniza combo
		if (unidade.length() > 0)
			for (int i = 0; i < this.cboUnidade.getItemCount(); i++) {
				if (i > 0) {
					String vetUnidade[] = this.cboUnidade.getItemAt(i)
							.toString().split(" ");
					if (vetUnidade[0].equalsIgnoreCase(unidade)) {
						this.cboUnidade.setSelectedIndex(i);
						break;
					}
				}
			}
		else
			this.cboUnidade.setSelectedIndex(0);
		
		if (fornecedor.length() > 0)
			for (int i = 0; i < this.cboFornecedor.getItemCount(); i++)
			 {
				if (i > 0)
				 {
				   String[] vetForn = this.cboFornecedor.getItemAt(i).toString().split(" ");
				   if (vetForn[2].equalsIgnoreCase(fornecedor)) 
				   {
				     this.cboFornecedor.setSelectedIndex(i);
				     break;
				   }
			    }
			 }
			 else
				 this.cboFornecedor.setSelectedIndex(0);


		this.btnIncluir.setEnabled(false);
		this.btnAlterar.setEnabled(true);
		this.btnExcluir.setEnabled(true);
	}

	private void alterarProduto() {
		DefaultTableModel modelo = (DefaultTableModel) this.tblProduto
				.getModel();
		int linha = this.tblProduto.getSelectedRow();
		EProduto eProduto = null;
		EUnidade eUnidade = null;
		EFornecedor eFornecedor = null;

		int codigo = Integer.parseInt(this.txtCodigo.getText());
		String nomeProduto = this.txtNomeProduto.getText();
		// String unidade = this.cboUnidade.getSelectedItem().toString();

		if (this.cboUnidade.getSelectedIndex() > 0) {
			eUnidade = new EUnidade();
			eUnidade.setId(this.cboUnidade.getSelectedIndex());
		}

		if (this.cboFornecedor.getSelectedIndex() > 0) {
			eFornecedor = new EFornecedor();
			eFornecedor.setCodigo(this.cboFornecedor.getSelectedIndex());
		}

		eProduto = new EProduto(codigo, nomeProduto, eUnidade, eFornecedor);
		try {
			this.nProduto.alterar(eProduto);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"Erro ao tentar alterar registro!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		modelo.setValueAt(codigo, linha, 0);
		modelo.setValueAt(nomeProduto, linha, 1);
		modelo.setValueAt(eUnidade.getSigla(), linha, 2);

		this.limparCampos();
		this.preencherTabela();
		JOptionPane.showMessageDialog(this, "Registro alterado!", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void removerProduto() {
		DefaultTableModel modelo = (DefaultTableModel) this.tblProduto
				.getModel();
		int linha = this.tblProduto.getSelectedRow();
		EProduto eProduto;

		int codigo = Integer.parseInt(this.txtCodigo.getText());
		eProduto = new EProduto(codigo, "", null, null);
		try {
			this.nProduto.excluir(eProduto);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"Erro ao tentar remover registro!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		modelo.removeRow(linha);

		this.limparCampos();
		JOptionPane.showMessageDialog(this, "Registro removido!", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void preencherTabela() {
		try {
			ArrayList<EProduto> listaProduto = nProduto.listar();			
			DefaultTableModel modelo = (DefaultTableModel) this.tblProduto
					.getModel();
			modelo.getDataVector().clear();

			if (!listaProduto.isEmpty()) {
				String siglaUnidade = null;
				String nomeFornecedor=null;
				
				for (EProduto eProduto : listaProduto) {

					if (eProduto.getEUnidade() == null)
						siglaUnidade = "";
					else {
						EUnidade eUnidade = this.nUnidade.consultar(eProduto
								.getEUnidade());
						eProduto.setEUnidade(eUnidade);
						siglaUnidade = eProduto.getEUnidade().getSigla();
					}
					
					if(eProduto.getEfornecedor()==null)
						nomeFornecedor ="";
					else
					{
						EFornecedor eFornecedor = this.nFornecedor.consultar(eProduto.getEfornecedor());
						eProduto.setEfornecedor(eFornecedor);
						nomeFornecedor = eProduto.getEfornecedor().getNome();
					}					

					modelo.addRow(new Object[] { eProduto.getCodigo(),
							eProduto.getNome(), siglaUnidade, nomeFornecedor });
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Falha ao listar produtos!"
					+ "Erro:" + e.getMessage(), "Erro de listagem",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void preencheComboUnidade() {
		try {
			List<EUnidade> listaUnidade = nUnidade.listar();
			this.cboUnidade = new JComboBox();
			this.cboUnidade.addItem("<Selecione>");
			for (EUnidade eUnidade : listaUnidade) {
				this.cboUnidade.addItem(eUnidade.getSigla() + " - "
						+ eUnidade.getDescricao());
			}
			this.cboUnidade.setBounds(86, 70, 129, 20);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Validação",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void preencheComboFornecedor()
	{
		try
		{
			List<EFornecedor> listaFornecedor = nFornecedor.listar();
			this.cboFornecedor = new JComboBox<>();
			this.cboFornecedor.addItem("<Selecione>");
			for(EFornecedor eFornecedor:listaFornecedor)
			{
				this.cboFornecedor.addItem(eFornecedor.getCodigo()+ " - "+eFornecedor.getNome());
			}			
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Validação",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
