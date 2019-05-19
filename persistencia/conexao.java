package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
	private Connection con;
	
	public conexao() {		
		String url = "jdbc:mysql://localhost:3306/vendas";
		String usuario = "root";
		String senha = "1234";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, usuario, senha);
		} catch (ClassNotFoundException e) {
			System.out.println("Classe não encontrada");
		}

		catch (SQLException e) {
			System.out.println("Problemas com o BD" + e);
		}
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
}
