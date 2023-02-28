package br.com.alurachallengejogodaforca.jogodaforcaapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.alurachallengejogodaforca.jogodaforcaapi.factory.ConnectionFactory;

import br.com.alurachallengejogodaforca.jogodaforcaapi.controller.SolicitarController;

@RestController
public class SolicitarPalavra {
	
	@Value("${database.host}")
	String host;
	
	@Value("${database.user}")
	String user;
	
	@Value("${database.pass}")
	String password;
	
	@CrossOrigin
	@PostMapping("/solicitar")
	public void solicitar(@RequestParam(value = "palavra", defaultValue="") String palavra) {
		
		if(palavra == "" || palavra.length() < 3 || !palavra.matches("[a-z]+")) return;
		
		SolicitarController solicitador = new SolicitarController(new ConnectionFactory(host, user, password).criaConexao());
		solicitador.adicionarPalavra(palavra);
		
		return;
	}
}

