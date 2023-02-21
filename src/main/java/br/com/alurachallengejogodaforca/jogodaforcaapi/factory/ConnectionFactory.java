package br.com.alurachallengejogodaforca.jogodaforcaapi.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private String user = "root";
	private String password = "123456";
	
	public Connection criaConexao() throws SQLException{
		
		String URL = String.format("jdbc:mysql://localhost/jogo_da_forca?user=%s&password=%s",this.user,this.password);
		return DriverManager.getConnection(URL);
	}
}
