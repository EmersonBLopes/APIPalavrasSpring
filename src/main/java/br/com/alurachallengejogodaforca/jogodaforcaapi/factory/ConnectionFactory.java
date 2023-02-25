package br.com.alurachallengejogodaforca.jogodaforcaapi.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

public class ConnectionFactory {
	
	@Value("${database.host}")
	private String host;
	@Value("${database.user}")
	private String user;
	@Value("${database.pass}")
	private String password;
	
	public Connection criaConexao() throws SQLException{
		
		String URL = String.format("jdbc:mysql:%suser=%s&password=%s"+this.host+this.user+this.password);
		return DriverManager.getConnection(URL);
	}
}
