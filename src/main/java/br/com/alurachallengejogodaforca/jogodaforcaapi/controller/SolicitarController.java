package br.com.alurachallengejogodaforca.jogodaforcaapi.controller;

import br.com.alurachallengejogodaforca.jogodaforcaapi.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SolicitarController {
	
	public boolean adicionarPalavra(String palavra) {
		
		ConnectionFactory cf = new ConnectionFactory();
		try(Connection con = cf.criaConexao()){
			PreparedStatement pst = con.prepareStatement("INSERT INTO palavras_solicitadas(conteudo)VALUES(?)");
			pst.setString(1, palavra);
			return pst.execute();
		}catch(SQLException ex) {
			System.out.println("Erro de conexão: "+ex.getMessage());
		}
		return false;
	}
}
