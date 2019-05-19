package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.EUnidade;

public class PUnidade {
	PreparedStatement pst = null;
	Connection conn;

	public PUnidade() {
		conn = new conexao().getCon();
	}

	public int incluir(EUnidade eUnidade) throws Exception {
		
		try {
			String sql = "INSERT INTO Unidade (sigla, descricao) VALUES (?,?)";
			
			pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, eUnidade.getSigla().toString());
			pst.setString(2,eUnidade.getDescricao().toString());
						
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

	public void excluir(EUnidade eUnidade) throws SQLException {
		String sql = "DELETE FROM Unidade WHERE id = ?";
		pst = conn.prepareStatement(sql);
		
		pst.setInt(1, eUnidade.getId());
		
		pst.executeUpdate();
		pst.close();
	}

	public void alterar(EUnidade eUnidade) throws SQLException {
				
		String sql = "UPDATE Unidade SET sigla = ?, descricao = ? WHERE id = ?"; 
		
		pst = conn.prepareStatement(sql);
		
		pst.setString(1, eUnidade.getSigla().toString());
		pst.setString(2,eUnidade.getDescricao().toString());
		pst.setInt(4, eUnidade.getId());
		
		pst.executeUpdate();
		pst.close();
	}
	
	public EUnidade consultar(EUnidade eUnidade) throws SQLException {
		String sql = "SELECT * FROM UNIDADE WHERE id = ?";
		pst = conn.prepareStatement(sql);
		
		pst.setInt(1, eUnidade.getId());
		
		ResultSet rs = pst.executeQuery();
		
		if (rs.next()){
			eUnidade.setId(rs.getInt("id"));
			eUnidade.setSigla(rs.getString("sigla"));
			eUnidade.setDescricao(rs.getString("descricao"));							
		}
		else eUnidade = null;
		
		pst.close();
		
		return eUnidade;
	}

	public ArrayList<EUnidade> listar() throws Exception {
		try {
			String sql = "SELECT * FROM UNIDADE";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			if (rs != null)
				return EUnidade.mapear(rs);
			else
				return null;
		} catch (SQLException e) {
			throw new SQLException("Erro ao tentar listar as unidades!");
		} finally {
			pst.close();
		}

	}
}
