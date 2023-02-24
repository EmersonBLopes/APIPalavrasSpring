package br.com.alurachallengejogodaforca.jogodaforcaapi.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alurachallengejogodaforca.jogodaforcaapi.factory.ConnectionFactory;
import br.com.alurachallengejogodaforca.jogodaforcaapi.modelo.Palavra;

public class ConsultaController {
	
	private ConnectionFactory cf = new ConnectionFactory();
	
	public Palavra consultaSimples(int ID){
		
		Palavra palavra = null;

		try(Connection con = cf.criaConexao()){
			PreparedStatement pst = con.prepareStatement("SELECT ID,conteudo FROM palavras WHERE ID = ?");

			pst.setInt(1, ID);
			if(pst.execute()) {
				ResultSet rst = pst.getResultSet();
				while(rst.next()) {
					palavra = new Palavra(rst.getInt(1),rst.getString(2));
				}
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Conexão com o banco de dados falhou");
		}
		
		return palavra;
	}
	
	public int consultaNumeroDeLinhas(String nomeDaTabela) {
		
		try(Connection con = cf.criaConexao()){
			
			try(Statement stm = con.createStatement()){
				stm.execute(String.format("SELECT COUNT(*) FROM %s",nomeDaTabela));
				ResultSet rs = stm.getResultSet();
				
				while(rs.next()) {
					return rs.getInt(1);
				}
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Conexão com o banco de dados falhou");
		}

		return -1;
	}
	
	public int consultaUltimaLinha(String nomeDaTabela) {
		
		try(Connection con = cf.criaConexao()){
			
			try(Statement stm = con.createStatement()){
				stm.execute(String.format("SELECT id FROM %s ORDER BY id DESC LIMIT 1",nomeDaTabela));
				ResultSet rs = stm.getResultSet();
				
				while(rs.next()) {
					return rs.getInt(1);
				}
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Conexão com o banco de dados falhou");
		}

		return -1;
	}
}
