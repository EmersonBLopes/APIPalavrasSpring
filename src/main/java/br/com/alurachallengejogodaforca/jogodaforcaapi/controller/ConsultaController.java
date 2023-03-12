package br.com.alurachallengejogodaforca.jogodaforcaapi.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.alurachallengejogodaforca.jogodaforcaapi.modelo.Modelo;
import br.com.alurachallengejogodaforca.jogodaforcaapi.modelo.Palavra;

public class ConsultaController {
	
	private Connection con;
	
	public ConsultaController(Connection con) {
		this.con = con;
	}
	
	/**
	 * 
	 * @param ID da palavra que será requisitada
	 * @param tabela a qual a palavra será procurada
	 * @return retorna a palavra solicitada como uma instância da classe Palavra
	 */
	public Palavra consultaPalavra(int ID,String tabela){

		Palavra palavra = null;
		String query = String.format("SELECT ID,conteudo FROM %s WHERE ID = ?",tabela);

		try(PreparedStatement pst = this.con.prepareStatement(query)){
			
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
	
	/**
	 * 
	 * @param tabela a qual será consulada as informações 
	 * @param ordenar se true as informações serão retornadas em ordem senão serão retornadas aleatóriamente
	 * @return Set<Modelo> com as informações da tabela
	 */
	public Set<Modelo> consultaTabela(String tabela, boolean ordenar) {
		
		String query = String.format("SELECT * FROM %s",tabela);
		
		Set<Modelo> listaDeResultados = ordenar ? new LinkedHashSet<>() : new HashSet<>();
		
		try(Statement stm = con.createStatement()){
			
			stm.execute(query);
			ResultSet rst = stm.getResultSet();
			
			while(rst.next()) {
				listaDeResultados.add(new Modelo(rst.getInt(1),rst.getString(2)));
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
			System.out.println("Conexão com o banco de dados falhou");
		}
		
		return listaDeResultados;
	}
	
	/**
	 * 
	 * @param palavra que será consultada sua existência
	 * @param tabela que será consultada
	 * @return retorna true se a palavra existir senão false
	 */
	public boolean consultaExiste(String palavra, String tabela) {
		String query = String.format("SELECT id FROM %s WHERE conteudo = ?",tabela);
		
		try(PreparedStatement pst = con.prepareStatement(query)){
			pst.setString(1, palavra);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			return rs.next();
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Conexão com o banco de dados falhou");
		}
		return false;
	}
	
	public Palavra consultaPorCategoria(String categoriaID[]) {
		Palavra palavra = null;
		
		return palavra;
	}
	
	/**
	 * 
	 * @param tabela tabela que será consultada
	 * @return se a tabela existir retornara o número de linhas caso contrário retorna -1
	 */
	public int consultaNumeroDeLinhas(String tabela) {
		
		try(Statement stm = this.con.createStatement()){
			stm.execute(String.format("SELECT COUNT(*) FROM %s",tabela));
			ResultSet rs = stm.getResultSet();
				
			while(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Conexão com o banco de dados falhou");
		}

		return -1;
	}
	
	/**
	 * 
	 * @param tabela que será consultada
	 * @return	retorna o ID do último elemento caso contrário retorna -1
	 */
	public int consultaUltimaLinha(String tabela) {
			
		try(Statement stm = this.con.createStatement()){
			stm.execute(String.format("SELECT id FROM %s ORDER BY id DESC LIMIT 1",tabela));
			ResultSet rs = stm.getResultSet();
				
			while(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Conexão com o banco de dados falhou");
		}

		return -1;
	}
}
