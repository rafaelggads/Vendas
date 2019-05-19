package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.EFornecedor;
import entidade.EUnidade;

public class PFornecedor {
	PreparedStatement pst;
	Connection con;

	public PFornecedor() {
		con = new conexao().getCon();
	}

	public int incluir(EFornecedor eFornecedor) throws Exception {
		try {
			String sql = "Insert into fornecedor(nome, telefone,cnpj, endereco) values (?,?,?,?)";
			pst = con.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, eFornecedor.getNome());
			pst.setString(2, eFornecedor.getTelefone());
			pst.setString(3, eFornecedor.getCnpj());
			pst.setString(4, eFornecedor.getEndereco());

			pst.execute();

			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next())
				return rs.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar inserir o registro");
		} finally {
			pst.close();
		}
	}

	public void alterar(EFornecedor eFornecedor) throws Exception {
		String sql = ("Update fornecedor set nome=?, telefone=?,cnpj=?, endereco=?  where id=?");
		pst = con.prepareStatement(sql);

		pst.setString(1, eFornecedor.getNome());
		pst.setString(2, eFornecedor.getTelefone());
		pst.setString(3, eFornecedor.getCnpj());
		pst.setString(4, eFornecedor.getEndereco());
		pst.setInt(5, eFornecedor.getCodigo());

		pst.executeUpdate();
		pst.close();
	}

	public void excluir(EFornecedor eFornecedor) throws SQLException {
		String sql = ("Delete from fornecedor where id=?");
		pst = con.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pst.setInt(1, eFornecedor.getCodigo());
		pst.execute();
		pst.close();
	}

	public EFornecedor consultar(EFornecedor eFornecedor) throws SQLException {
		String sql = "Select * from fornecedor where id=?";

		pst = con.prepareStatement(sql);
		pst.setInt(1, eFornecedor.getCodigo());

		ResultSet rs = pst.executeQuery();

		
		
		if (rs.next())
		{
			eFornecedor.setCodigo(rs.getInt("id"));
			
			if(rs.getObject("nome")!=null)
				eFornecedor.setNome(rs.getString("nome"));
			
			if(rs.getObject("telefone")!=null)
				eFornecedor.setTelefone(rs.getString("telefone"));
			
			if(rs.getObject("cnpj")!=null)
				eFornecedor.setCnpj(rs.getString("cnpj"));	
			
			if(rs.getObject("endereco")!=null)
				eFornecedor.setEndereco(rs.getString("endereco"));				
		}
		else eFornecedor = null;
		
		//EFornecedor eForn = new  EFornecedor(rs.getInt("id"), rs.getString("nome"),
				//rs.getString("telefone"), rs.getString("cnpj"),
				//rs.getString("endereco"));
		pst.close();
		return eFornecedor;
	}

	public ArrayList<EFornecedor> listar() throws Exception {
		try {

			String sql = "Select * from fornecedor";
			pst = con.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();

			if (rs != null)
				return EFornecedor.mapear(rs);
			else
				return null;
		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar listar os Fornecedores!");
		} finally {
			pst.close();
		}
	}

}
