package br.com.alurachallengejogodaforca.jogodaforcaapi.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.alurachallengejogodaforca.jogodaforcaapi.ConnectionFactory;
import br.com.alurachallengejogodaforca.jogodaforcaapi.modelo.Palavra;

public class ConsultaController {
	
	public ArrayList<Palavra> consultaSimples(){
		
		ArrayList<Palavra> listaDePalavras = new ArrayList<Palavra>();
		
		ConnectionFactory cf = new ConnectionFactory();
		
		try(Connection con = cf.criaConexao()){
			PreparedStatement pst = con.prepareStatement("SELECT * FROM palavras");

			if(pst.execute()) {
				ResultSet rst = pst.getResultSet();
				while(rst.next()) {
					listaDePalavras.add(new Palavra(rst.getInt(1),rst.getString(2)));
				}
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Conexão com o banco de dados falhou");
		}
		
		return listaDePalavras;
	}
}
