package br.com.alurachallengejogodaforca.jogodaforcaapi.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SolicitarController {
	
	private Connection con;
	
	public SolicitarController(Connection con) {
		this.con = con;
	}
	
	public boolean adicionarPalavra(String palavra) {
		
		try(PreparedStatement pst = con.prepareStatement("INSERT INTO palavras_solicitadas(conteudo)VALUES(?)");){
			pst.setString(1, palavra);
			pst.execute();
			return true;
		}catch(SQLException ex) {
			System.out.println("Erro de conexão: "+ex.getMessage());
		}
		return false;
	}
	
	/**
	 * 
	 * @param tabela nome da table que será acessada
	 * @param id da palavra que será removida
	 * @return true se for removida, caso contrário false
	 */
	public boolean removerPalavra(String tabela, int id) {
		String SQL = String.format("DELETE FROM %s WHERE id = ?",tabela);
		try(PreparedStatement pst = con.prepareStatement(SQL)){

			pst.setInt(1, id);
			pst.execute();
			return true;

		}catch(SQLException ex) {
			System.out.println("Erro de conexão: "+ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param id da palavra que dever ser transferida de tablela
	 *
	 * @return returna true se ocorrer a transferência, caso contrário false
	 */
	public boolean transferirPalvra(int id) {
		
		try(PreparedStatement PSTPalavraSolicitadas = con.prepareStatement("SELECT conteudo FROM palavras_solicitadas WHERE id = ?");){
			
			PSTPalavraSolicitadas.setInt(1,id);
			PSTPalavraSolicitadas.execute();
			
			PreparedStatement PSTTranferePalavra = con.prepareStatement("INSERT INTO palavras(conteudo)VALUES(?)"); 
			
			try(ResultSet RSPalavraSelecionada = PSTPalavraSolicitadas.getResultSet()){
				
				while(RSPalavraSelecionada.next()) {
					PSTTranferePalavra.setString(1, RSPalavraSelecionada.getString(1));
				}
				
				PSTTranferePalavra.execute();
				this.removerPalavra("palavras_solicitadas", id);
				
				return true;
			}
		}catch(SQLException ex) {
			System.out.println("Erro de conexão: "+ex.getMessage());
		}
		
		return false;
	}
}


