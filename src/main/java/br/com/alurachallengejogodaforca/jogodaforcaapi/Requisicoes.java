package br.com.alurachallengejogodaforca.jogodaforcaapi;

import java.util.HashSet;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alurachallengejogodaforca.jogodaforcaapi.controller.ConsultaController;
import br.com.alurachallengejogodaforca.jogodaforcaapi.factory.ConnectionFactory;
import br.com.alurachallengejogodaforca.jogodaforcaapi.modelo.Modelo;
import br.com.alurachallengejogodaforca.jogodaforcaapi.modelo.Palavra;

@RestController
public class Requisicoes {
	
	@Value("${database.host}")
	String host;
	
	@Value("${database.user}")
	String user;
	
	@Value("${database.pass}")
	String password;
	
	@CrossOrigin
	@GetMapping("/palavras-aleatorias")
	public ResponseEntity<HashSet<Palavra>> gerarPalavrasAleatorias(@RequestParam(name = "numeroMaximo", defaultValue = "10") int numeroMaximo) {
		
		ConsultaController consulta = new ConsultaController(new ConnectionFactory(host, user, password).criaConexao());
		HashSet<Palavra> listaDePalavras = new HashSet<Palavra>();
		
		HashSet<Modelo> palavras = consulta.consultaTabela("palavras");

		if(numeroMaximo > consulta.consultaNumeroDeLinhas("palavras")) numeroMaximo = consulta.consultaNumeroDeLinhas("palavras");
		
		int i = 0;
		for (Modelo palavra : palavras) {
			
			if(i == numeroMaximo) break;
					
			listaDePalavras.add(new Palavra(palavra.getID(),palavra.getConteudo()));
			i++;
		}
		
		return new ResponseEntity<HashSet<Palavra>>(listaDePalavras, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/palavras-solicitadas")
	public ResponseEntity<HashSet<Modelo>> palavrasSolicitadas() {
		
		ConsultaController consulta = new ConsultaController(new ConnectionFactory(host, user, password).criaConexao());
		
		HashSet<Modelo> listaDePalavras = consulta.consultaTabela("palavras_solicitadas");

		
		return new ResponseEntity<HashSet<Modelo>>(listaDePalavras, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/categorias")
	public ResponseEntity<LinkedHashSet<Modelo>> categorias(){
		
		ConsultaController consulta = new ConsultaController(new ConnectionFactory(host, user, password).criaConexao());
		
		LinkedHashSet<Modelo> listaDeCategorias = consulta.consultaTabela("categorias");		
		
		return new ResponseEntity<LinkedHashSet<Modelo>>(listaDeCategorias, HttpStatus.OK);
	}
}
