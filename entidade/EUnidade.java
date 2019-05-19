package entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EUnidade {
	private int id;
	private String sigla;
	private String descricao;
	
	public EUnidade(){}
	public EUnidade (String sigla, String descricao){
		this.sigla = sigla;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	public static ArrayList<EUnidade> mapear (ResultSet rs) throws SQLException {
		ArrayList<EUnidade> listaUnidade = new ArrayList<EUnidade>();
		while(rs.next()){			
			EUnidade e = new EUnidade(rs.getString("sigla"), rs.getString("descricao"));
			listaUnidade.add(e);
		}
		return listaUnidade;
	}
	
}