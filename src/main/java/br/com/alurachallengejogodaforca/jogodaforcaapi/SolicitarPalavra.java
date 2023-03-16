package br.com.alurachallengejogodaforca.jogodaforcaapi;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alurachallengejogodaforca.jogodaforcaapi.controller.ConsultaController;
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
		
		Connection con = new ConnectionFactory(host, user, password).criaConexao();
		
		ConsultaController consultador = new ConsultaController(con);
		
		if(palavra == "" || palavra.length() < 3 || !palavra.matches("[a-z]+")) {
			return new ResponseEntity<String>("Palavra inválida",HttpStatus.BAD_REQUEST);
		}
		
		SolicitarController solicitador = new SolicitarController(con);
		if(solicitador.adicionarPalavra(palavra)) {
			return new ResponseEntity<String>("Solicitação enviada",HttpStatus.OK);
		}
		
		if(consultador.consultaExiste(palavra, "lista_negra")) return new ResponseEntity<String>("Palavra proibida",HttpStatus.ALREADY_REPORTED);
		
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
		
		ResponseEntity<String> resposta = solicitador.transferirPalavra("palavras",ID) ? new ResponseEntity<String>("Palavra aceita.",HttpStatus.ACCEPTED) : new ResponseEntity<String>("Erro ao aceitar palavra",HttpStatus.BAD_REQUEST);
		
		return resposta;
	}
	
	@CrossOrigin
	@PostMapping("/remover-palavra")
	public ResponseEntity<String> removerPalavra(@RequestBody String IDSolicitado){
		
		int ID = 0;
		
		try {
			ID = Integer.parseInt(IDSolicitado);
		}catch(NumberFormatException ex){
			System.out.println("erro "+ex.getMessage());
		}

		SolicitarController solicitador = new SolicitarController(new ConnectionFactory(host, user, password).criaConexao());
		
		ResponseEntity<String> resposta = solicitador.removerPalavra("palavras_solicitadas",ID) ? new ResponseEntity<String>("Palavra removida.",HttpStatus.ACCEPTED) : new ResponseEntity<String>("Erro ao remover palavra",HttpStatus.BAD_REQUEST);
		
		return resposta;
	}
	
	@CrossOrigin
	@PostMapping("/bloquear-palavra")
	public ResponseEntity<String> bloquearPalavra(@RequestBody String IDSolicitado){
		
		int ID = 0;
		
		try {
			ID = Integer.parseInt(IDSolicitado);
		}catch(NumberFormatException ex){
			System.out.println("erro "+ex.getMessage());
		}

		SolicitarController solicitador = new SolicitarController(new ConnectionFactory(host, user, password).criaConexao());
		
		ResponseEntity<String> resposta = solicitador.transferirPalavra("lista_negra",ID) ? new ResponseEntity<String>("Palavra bloqueada.",HttpStatus.ACCEPTED) : new ResponseEntity<String>("Erro ao aceitar palavra",HttpStatus.BAD_REQUEST);
		
		return resposta;
	}
}

