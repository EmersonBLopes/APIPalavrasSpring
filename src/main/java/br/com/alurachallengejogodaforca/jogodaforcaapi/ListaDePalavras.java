package br.com.alurachallengejogodaforca.jogodaforcaapi;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alurachallengejogodaforca.jogodaforcaapi.controller.ConsultaController;
import br.com.alurachallengejogodaforca.jogodaforcaapi.factory.ConnectionFactory;
import br.com.alurachallengejogodaforca.jogodaforcaapi.modelo.Palavra;

@RestController
public class ListaDePalavras {
	
	@Value("${database.host}")
	String host;
	
	@Value("${database.user}")
	String user;
	
	@Value("${database.pass}")
	String password;
	
	@CrossOrigin
	@GetMapping("/palavras-aleatorias")
	public ResponseEntity<HashSet<Palavra>> gerarPalavrasAleatorias(@RequestParam(name = "numeroMaximo", defaultValue = "10") int numeroMaximo) {
		
		HashSet<Palavra> listaDePalavras = new HashSet<Palavra>();
		
		ConsultaController consulta = new ConsultaController(new ConnectionFactory(host, user, password).criaConexao());
		
		HashSet<Palavra> palavras = consulta.consultaTodas("palavras");

		if(numeroMaximo > consulta.consultaNumeroDeLinhas("palavras")) numeroMaximo = consulta.consultaNumeroDeLinhas("palavras");
		
		int i = 0;
		for (Palavra palavra : palavras) {
			
			if(i == numeroMaximo) break;
					
			listaDePalavras.add(new Palavra(palavra.getID(),palavra.getConteudo()));
			i++;
		}
		
		return new ResponseEntity<HashSet<Palavra>>(listaDePalavras, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/palavras-solicitadas")
	public ResponseEntity<HashSet<Palavra>> palavrasSolicitadas() {
		
		ConsultaController consulta = new ConsultaController(new ConnectionFactory(host, user, password).criaConexao());
		
		HashSet<Palavra> listaDePalavras = consulta.consultaTodas("palavras_solicitadas");

		
		return new ResponseEntity<HashSet<Palavra>>(listaDePalavras, HttpStatus.OK);
	}
}
