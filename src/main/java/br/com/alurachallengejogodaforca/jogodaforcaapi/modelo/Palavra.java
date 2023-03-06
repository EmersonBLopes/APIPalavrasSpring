package br.com.alurachallengejogodaforca.jogodaforcaapi.modelo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"ID","conteudo"})
public class Palavra{
	private long ID;
	private String conteudo;
	
	public Palavra(long ID, String conteudo){
		this.ID = ID;
		this.conteudo = conteudo;
	}

	public long getID() {
		return ID;
	}

	public String getConteudo() {
		return conteudo;
	}
	
	@Override 
	public boolean equals(Object obj) {
		Palavra palavra = (Palavra) obj;
		return this.ID == palavra.getID();
	}
}
