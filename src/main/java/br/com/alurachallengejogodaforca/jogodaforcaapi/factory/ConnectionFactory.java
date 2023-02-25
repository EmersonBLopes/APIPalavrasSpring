package br.com.alurachallengejogodaforca.jogodaforcaapi.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private String host;
	private String user;
	private String password;
	
	public ConnectionFactory(String host, String user, String password) {
		this.host = host;
		this.user = user;
		this.password = password;
	}
	
	public Connection criaConexao(){
		String URL = String.format("jdbc:mysql:%suser=%s&password=%s",this.host,this.user,this.password);
		try {
			return DriverManager.getConnection(URL);
		}catch(SQLException ex) {
			System.out.println("Erro de conexão com o banco de dados:"+ex.getMessage());
			return null;
		}
	}
}
