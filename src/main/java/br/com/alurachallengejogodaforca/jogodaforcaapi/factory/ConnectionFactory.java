package br.com.alurachallengejogodaforca.jogodaforcaapi.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private InformacoesConfidenciais dados = new InformacoesConfidenciais();
	//private String user = "root";
	//private String password = "123456";
	
	public Connection criaConexao() throws SQLException{
		
		String URL = String.format("jdbc:mysql://%s/jogo_da_forca?user=%s&password=%s",this.dados.getHost(),this.dados.getUser(),this.dados.getPassword());
		return DriverManager.getConnection(URL);
	}
}
