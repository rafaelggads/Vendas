package entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EFornecedor {
	
	private int codigo;
	private String nome;
	private String endereco;
	private String Telefone;
	private String cnpj;
	
	public EFornecedor()
	{}
	
	public EFornecedor(int codigo,String nome,String endereco,String telefone,String cnpj)
	{
		this.codigo =codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.Telefone = telefone;
		this.cnpj =cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
		this.nome= nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}
	
	public static ArrayList<EFornecedor> mapear(ResultSet rs) throws SQLException{
		ArrayList<EFornecedor> listaFornecedor = new ArrayList<EFornecedor>();
		while(rs.next()){
			EFornecedor e = new EFornecedor(rs.getInt("id"),
					rs.getString("nome"),
					rs.getString("endereco"),
					rs.getString("telefone"),
					rs.getString("CNPJ"));
			listaFornecedor.add(e);
		}
		return listaFornecedor;
	}

	public void setId(int int1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
