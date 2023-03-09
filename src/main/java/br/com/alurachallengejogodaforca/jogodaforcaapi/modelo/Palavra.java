package br.com.alurachallengejogodaforca.jogodaforcaapi.modelo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"ID","conteudo","categoria"})
public class Palavra{
	private long ID;
	private String conteudo;
	private String categoria;
	
	public Palavra(long ID, String conteudo, String categoria){
		this.ID = ID;
		this.conteudo = conteudo;
		this.categoria = categoria;
	}

	public long getID() {
		return ID;
	}

	public String getConteudo() {
		return conteudo;
	}
	
	public String getCategoria() {
		return categoria;
	}
	@Override 
	public boolean equals(Object obj) {
		Palavra palavra = (Palavra) obj;
		return this.ID == palavra.getID();
	}
}
