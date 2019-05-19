package entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EProduto {
	private int codigo;
	private String nome;
	//private String unidade;
	
	private EUnidade eUnidade;
	private EFornecedor eFornecedor; 

	public EProduto(int codigo, String nome/*, String unidade*/, EUnidade eUnidade, EFornecedor eFornecedor) {
		this.codigo = codigo;
		this.nome = nome;
		
		this.setEUnidade(eUnidade);
		this.eFornecedor = eFornecedor;
		//this.unidade = unidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/*public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}*/

	public static ArrayList<EProduto> mapear (ResultSet rs) throws SQLException {
		ArrayList<EProduto> listaProduto = new ArrayList<EProduto>();
		
		while(rs.next()){
			EUnidade eUnidade = null;
			EFornecedor eFornecedor = null;
			
			if (rs.getObject("idUnidade") != null){
				eUnidade = new EUnidade();
				eUnidade.setId(rs.getInt("idUnidade"));
			}
						
			if (rs.getObject("idFornecedor") != null){
				eFornecedor = new EFornecedor();
				eFornecedor.setCodigo(rs.getInt("idFornecedor"));
			}
						
			//EProduto e = new EProduto(rs.getInt("id"), rs.getString("nome"), rs.getString("unidade"));
			EProduto e = new EProduto(rs.getInt("id"), rs.getString("nome"), eUnidade, eFornecedor);
			listaProduto.add(e);
		}
		return listaProduto;
	}

	public EUnidade getEUnidade() {
		return eUnidade;
	}

	public void setEUnidade(EUnidade eUnidade) {
		this.eUnidade = eUnidade;
	}

	public EFornecedor getEfornecedor() {
		return eFornecedor;
	}

	public void setEfornecedor(EFornecedor eFornecedor) {
		this.eFornecedor = eFornecedor;
	}
}
