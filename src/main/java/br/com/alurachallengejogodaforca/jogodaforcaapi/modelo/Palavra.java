package br.com.alurachallengejogodaforca.jogodaforcaapi.modelo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"ID","conteudo"})
public class Palavra extends Modelo{
	
	public Palavra(long ID, String conteudo){
		super(ID, conteudo);
	}

	@Override 
	public boolean equals(Object obj) {
		Palavra palavra = (Palavra) obj;
		return this.getID() == palavra.getID();
	}
}
