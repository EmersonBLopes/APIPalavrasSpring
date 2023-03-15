package br.com.alurachallengejogodaforca.jogodaforcaapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alurachallengejogodaforca.jogodaforcaapi.controller.SolicitarController;
import br.com.alurachallengejogodaforca.jogodaforcaapi.factory.ConnectionFactory;

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
	public ResponseEntity<String> solicitar(@RequestBody String palavra) {
		
		if(palavra == "" || palavra.length() < 3 || !palavra.matches("[a-z]+")) {
			return new ResponseEntity<String>("Palavra inválida",HttpStatus.BAD_REQUEST);
		}
		
		SolicitarController solicitador = new SolicitarController(new ConnectionFactory(host, user, password).criaConexao());
		if(solicitador.adicionarPalavra(palavra)) {
			return new ResponseEntity<String>("Solicitação enviada",HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Palavra já solicitada",HttpStatus.ALREADY_REPORTED);
	}
	
	@CrossOrigin
	@PostMapping("/aceitar-palavra")
	public ResponseEntity<String> aceitarPalavra(@RequestBody String IDSolicitado){
		
		int ID = 0;
		
		try {
			ID = Integer.parseInt(IDSolicitado);
		}catch(NumberFormatException ex){
			System.out.println("erro "+ex.getMessage());
		}

		SolicitarController solicitador = new SolicitarController(new ConnectionFactory(host, user, password).criaConexao());
		
		ResponseEntity<String> resposta = solicitador.transferirPalavra(ID) ? new ResponseEntity<String>("Palavra aceita.",HttpStatus.ACCEPTED) : new ResponseEntity<String>("Erro ao aceitar palavra",HttpStatus.BAD_REQUEST);
		
		return resposta;
	}
}

