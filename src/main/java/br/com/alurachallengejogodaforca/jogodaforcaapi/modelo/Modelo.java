package br.com.alurachallengejogodaforca.jogodaforcaapi.modelo;

public class Modelo {
	
	private long ID;
	private String conteudo;
	
	public Modelo(long ID,String conteudo){
		
		this.ID = ID;
		this.conteudo = conteudo;
	}
	
	public long getID() {
		return ID;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	
}
