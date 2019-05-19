package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import entidade.EFornecedor;
import entidade.EProduto;
import entidade.EUnidade;

public class PProduto {
	PreparedStatement pst = null;
	Connection conn;

	public PProduto() {
		conn = new conexao().getCon();
	}

	public int incluir(EProduto eProduto) throws Exception {
		
		try {
			String sql = "INSERT INTO Produto (nome, idUnidade, idFornecedor) VALUES (?,?,?)";
			
			pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, eProduto.getNome().toString());
			//pst.setString(2, eProduto.getUnidade().toString());
			
			if(eProduto.getEUnidade().getId() > 0)
				pst.setInt(2,eProduto.getEUnidade().getId());
			else
				pst.setNull(2, Types.INTEGER);
			
			if (eProduto.getEfornecedor().getCodigo() > 0)
				pst.setInt(3, eProduto.getEfornecedor().getCodigo());
			else
				pst.setNull(3,Types.INTEGER);
									
			pst.execute();
			
			ResultSet rs = pst.getGeneratedKeys();
			
			if(rs.next())
				return rs.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao tentar inserir registro!");
		} finally {
			pst.close();
		}
	}

	public void excluir(EProduto eProduto) throws SQLException {
		String sql = "DELETE FROM Produto WHERE id = ?";
		pst = conn.prepareStatement(sql);
		
		pst.setInt(1, eProduto.getCodigo());
		
		pst.executeUpdate();
		pst.close();
	}

	public void alterar(EProduto eProduto) throws SQLException {
				
		String sql = "UPDATE Produto SET nome = ?, idUnidade = ?, idFornecedor = ? WHERE id = ?"; 
		
		pst = conn.prepareStatement(sql);
		
		pst.setString(1, eProduto.getNome());
		
		if(eProduto.getEUnidade()!= null)
			pst.setInt(2, eProduto.getEUnidade().getId());
		else
			pst.setNull(2, Types.INTEGER);
		
		if(eProduto.getEfornecedor() != null)
			pst.setInt(3, eProduto.getEfornecedor().getCodigo());
		else
			pst.setNull(3, Types.INTEGER);
		
		pst.setInt(4, eProduto.getCodigo());
		
		pst.executeUpdate();
		pst.close();
	}
	
	public EProduto consultar(EProduto eProduto) throws SQLException {
		String sql = "SELECT * FROM PRODUTO WHERE id = ?";
		pst = conn.prepareStatement(sql);
		
		pst.setInt(1, eProduto.getCodigo());
		
		ResultSet rs = pst.executeQuery();		
		
		pst.close();
		
		if (rs.getObject("id") != null){
			eProduto.setNome(rs.getString("nome"));
			
			if(rs.getObject("idUnidade") != null){
				EUnidade eUnidade = new EUnidade();
				eUnidade.setId(rs.getInt("idUnidade"));
				eProduto.setEUnidade(eUnidade);
			}
			
			if(rs.getObject("idFornecedor") != null){				
				EFornecedor eFornecedor = new EFornecedor();
				eFornecedor.setCodigo(rs.getInt("idFornecedor"));
				eProduto.setEfornecedor(eFornecedor);
			}				
		}
		else eProduto = null;
		
		return eProduto;

	}

	public ArrayList<EProduto> listar() throws Exception {
		try {
			String sql = "SELECT * FROM PRODUTO";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);

			if (rs != null)
				return EProduto.mapear(rs);
			else
				return null;
		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar listar os produtos!");
		} finally {
			pst.close();
		}

	}
}
