package apresentacao;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
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
import entidade.EFornecedor;
import entidade.EProduto;

public class AFornecedor extends JInternalFrame implements ActionListener,MouseListener {
	private JTextField txt_codigo;
	private JTextField txt_Nome;
	private JTextField txt_endereco;
	private JTextField txt_telefone;
	private JButton btn_Incluir;
	private JButton btn_Alterar;
	private JButton btn_Excluir;
	private JButton btn_sair;
	private JPanel Jpanel_botoes;
	private JScrollPane scrollPane; 
	private JTable tbl_Fornecedor;
	private JTextField txt_cnpj;
	private NFornecedor nFornecedor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AFornecedor frame = new AFornecedor();
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
	public AFornecedor() {
		super("Cadastro de Fornecedores");
		setClosable(true);
		setBounds(100, 100, 572, 322);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo :");
		lblCodigo.setBounds(10, 11, 46, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setBounds(10, 36, 46, 14);
		getContentPane().add(lblNome);
		
		JLabel lblEnderero = new JLabel("Enderer\u00E7o :");
		lblEnderero.setBounds(10, 61, 66, 14);
		getContentPane().add(lblEnderero);
		
		JLabel lblTelefone = new JLabel("Telefone :");
		lblTelefone.setBounds(10, 90, 56, 14);
		getContentPane().add(lblTelefone);
		
		txt_codigo = new JTextField();
		txt_codigo.setEnabled(false);
		txt_codigo.setBounds(81, 8, 46, 20);
		getContentPane().add(txt_codigo);
		txt_codigo.setColumns(10);
		
		txt_Nome = new JTextField();
		txt_Nome.setText("");
		txt_Nome.setBounds(81, 33, 226, 20);
		getContentPane().add(txt_Nome);
		txt_Nome.setColumns(10);
		
		txt_endereco = new JTextField();
		txt_endereco.setBounds(81, 58, 398, 20);
		getContentPane().add(txt_endereco);
		txt_endereco.setColumns(10);
		
		txt_telefone = new JTextField();
		txt_telefone.setBounds(81, 87, 96, 20);
		getContentPane().add(txt_telefone);
		txt_telefone.setColumns(10);
		
		Jpanel_botoes = new JPanel();
		Jpanel_botoes.setBounds(91, 115, 388, 33);
		getContentPane().add(Jpanel_botoes);
		
		btn_Incluir = new JButton("Incluir");
		Jpanel_botoes.add(btn_Incluir);
		btn_Incluir.addActionListener(this);
		
		btn_Alterar = new JButton("Alterar");
		btn_Alterar.setEnabled(false);
		Jpanel_botoes.add(btn_Alterar);
		btn_Alterar.addActionListener(this);
		
		btn_Excluir = new JButton("Excluir");
		btn_Excluir.setEnabled(false);
		Jpanel_botoes.add(btn_Excluir);
		btn_Excluir.addActionListener(this);
		
		btn_sair = new JButton("Sair");
		Jpanel_botoes.add(btn_sair);
		btn_sair.addActionListener(this);
		
		String[] colunasNomes ={"Codigo", "Nome", "Endereço","Telefone","CNPJ"};
		
		DefaultTableModel modelo = new DefaultTableModel(null,colunasNomes);
		tbl_Fornecedor = new JTable(modelo);
		tbl_Fornecedor.setToolTipText("Selecione um fornecedor edit\u00E1-lo.");
		tbl_Fornecedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl_Fornecedor.setPreferredScrollableViewportSize(new Dimension(500,70));
		tbl_Fornecedor.addMouseListener(this);
		
		scrollPane = new JScrollPane(tbl_Fornecedor);
		scrollPane.setToolTipText("Selecione o Item");
		scrollPane.setBounds(42, 159, 485, 123);
		getContentPane().add(scrollPane);
		
		JLabel lblCnpj = new JLabel("CNPJ :");
		lblCnpj.setBounds(317, 36, 46, 14);
		getContentPane().add(lblCnpj);
		
		txt_cnpj = new JTextField();
		txt_cnpj.setBounds(354, 33, 125, 20);
		getContentPane().add(txt_cnpj);
		txt_cnpj.setColumns(10);

		
		nFornecedor = new NFornecedor();
		this.preencherTabela();
	

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getSource().equals(btn_Incluir)){
			if(validarEntrada()){
				adicionarFornecedor();
		
			}
		}
		if(evento.getSource().equals(btn_Alterar)){
			
			
			alterarProduto();
			//JOptionPane.showConfirmDialog(this, "Confirma","Aviso",JOptionPane.WARNING_MESSAGE);
		}
		if(evento.getSource().equals(btn_Excluir)){
			if(tbl_Fornecedor.getSelectedRow()!=-1){
				int resposta = JOptionPane.showConfirmDialog(this, "Confirma remoção do produto?","Confirmação",
					JOptionPane.YES_NO_OPTION);
				if(resposta == JOptionPane.YES_OPTION && this.tbl_Fornecedor.getSelectedRow()>0)
					this.removerFornecedor();
				}
			else {JOptionPane.showMessageDialog(this,"Selecione uma Linha para Excluir!","Aviso", JOptionPane.WARNING_MESSAGE);}}
			
		if(evento.getSource()== btn_sair){
		this.dispose();
		}
		
	}
	
	private boolean validarEntrada()
	{
		EFornecedor eFornecedor = new EFornecedor(Integer.parseInt(this.txt_codigo.getText().length()==0?"0" : this.txt_codigo.getText()),
								this.txt_Nome.getText(),this.txt_endereco.getText(),this.txt_telefone.getText(),this.txt_cnpj.getText());
		
		try{
			nFornecedor.validarEntrada(eFornecedor);		
		}catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(),"Validação", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	private void adicionarFornecedor()
	{
		DefaultTableModel modelo = (DefaultTableModel) this.tbl_Fornecedor.getModel();
		
		EFornecedor eFornecedor;
		int codigo=0;
		String nomeFornecedor = this.txt_Nome.getText();
		String endereco = this.txt_endereco.getText();
		String telefone = this.txt_telefone.getText();
		String cnpj = this.txt_cnpj.getText();
		
		eFornecedor = new EFornecedor(codigo, nomeFornecedor, endereco, telefone, cnpj);
		try{
			codigo = this.nFornecedor.incluir(eFornecedor);
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(),"Aviso",JOptionPane.ERROR_MESSAGE);			
		}
		String novaLinha[]= {String.valueOf(codigo),nomeFornecedor,endereco,telefone,cnpj};
		modelo.addRow(novaLinha);
		this.limparCampos();
		JOptionPane.showMessageDialog(this, "Registro incluido!","Aviso",JOptionPane.INFORMATION_MESSAGE);
	}
	private void limparCampos()
	{
		this.txt_codigo.setText("");
		this.txt_Nome.setText("");
		this.txt_endereco.setText("");
		this.txt_telefone.setText("");
		this.txt_codigo.requestFocus();
		this.txt_cnpj.setText("");
	}
	
	private void removerFornecedor()
	{
		DefaultTableModel modelo = (DefaultTableModel)this.tbl_Fornecedor.getModel();
		int linha= this.tbl_Fornecedor.getSelectedRow();
		
		EFornecedor eFornecedor;
		int codigo = Integer.parseInt(this.txt_codigo.getText());
		eFornecedor = new EFornecedor(codigo,"","","","");
		
		try{
			this.nFornecedor.excluir(eFornecedor);
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "Erro ao tentar reomover registro","Erro",JOptionPane.ERROR_MESSAGE);
		}
		
		modelo.removeRow(linha);
		
		this.limparCampos();
		
		JOptionPane.showMessageDialog(this, "Registro removido","Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

	private void alterarProduto()
	{
		DefaultTableModel modelo = (DefaultTableModel)this.tbl_Fornecedor.getModel();
		int linha = this.tbl_Fornecedor.getSelectedRow();
		
		EFornecedor efornecedor;
		int codigo= Integer.parseInt(this.txt_codigo.getText());
		String nome = this.txt_Nome.getText();
		String endereco = this.txt_endereco.getText();
		String telefone = this.txt_telefone.getText();
		String cnpj = this.txt_cnpj.getText();
		
		efornecedor = new EFornecedor(codigo, nome, endereco,telefone,cnpj);
		try{
			this.nFornecedor.alterar(efornecedor);
		} catch(Exception e){
			JOptionPane.showMessageDialog(this, "Erro ao tentar alterar registro","Erro",JOptionPane.ERROR_MESSAGE);
		}
		
		modelo.setValueAt(this.txt_codigo.getText(), linha, 0);
		modelo.setValueAt(this.txt_Nome.getText(), linha, 1);
		modelo.setValueAt(this.txt_endereco.getText(), linha, 2);
		modelo.setValueAt(this.txt_telefone.getText(), linha,3);
		
		this.limparCampos();
		
		JOptionPane.showMessageDialog(this, "Registro alterado","Aviso",JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void carregarDadosFormulario(int linha)
	{
		DefaultTableModel modelo=(DefaultTableModel)this.tbl_Fornecedor.getModel();
		
		String codigo = modelo.getValueAt(linha,0).toString();
		String nome = modelo.getValueAt(linha, 1).toString();
		String endereco = modelo.getValueAt(linha, 2).toString();
		String telefone = modelo.getValueAt(linha, 3).toString();
		String cnpj = modelo.getValueAt(linha, 4).toString();
		
		this.txt_codigo.setText(codigo);
		this.txt_Nome.setText(nome);
		this.txt_endereco.setText(endereco);
		this.txt_telefone.setText(telefone);
		this.txt_cnpj.setText(cnpj);
		
		this.btn_Incluir.setEnabled(false);
		this.btn_Alterar.setEnabled(true);
		this.btn_Excluir.setEnabled(true);

	}
	
	private void preencherTabela() {
		try{
			ArrayList<EFornecedor> listaFornecedor = nFornecedor.listar();
			DefaultTableModel modelo =(DefaultTableModel) this.tbl_Fornecedor.getModel();
			modelo.getDataVector().clear();
			if(!listaFornecedor.isEmpty())
			{
				for(EFornecedor eFornecedor : listaFornecedor)
					modelo.addRow(new Object[] {eFornecedor.getCodigo(),
							eFornecedor.getNome(),eFornecedor.getEndereco(),eFornecedor.getTelefone(),eFornecedor.getCnpj()});
			}
			
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, "Falha ao listar Fornecedor!","Erro de listagem",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent evento) {
		if(evento.getSource() == this.tbl_Fornecedor)
		{
			int linha = this.tbl_Fornecedor.getSelectedRow();
			this.carregarDadosFormulario(linha);
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
}
