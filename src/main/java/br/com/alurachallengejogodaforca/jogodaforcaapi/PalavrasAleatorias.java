package br.com.alurachallengejogodaforca.jogodaforcaapi;

import java.util.LinkedList;
import java.util.random.RandomGenerator;

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
public class PalavrasAleatorias {
	
	@Value("${database.host}")
	String host;
	
	@Value("${database.user}")
	String user;
	
	@Value("${database.pass}")
	String password;
	

	public int sorteiaNumero(int min, int max) {
		return RandomGenerator.getDefault().nextInt(min,max+1);
	}
	
	@CrossOrigin
	@GetMapping("/palavras-aleatorias")
	public ResponseEntity<LinkedList<Palavra>> gerarPalavrasAleatorias(@RequestParam(name = "numeroMaximo", defaultValue = "5") int numeroMaximo) {
		
		LinkedList<Palavra> listaDePalavras = new LinkedList<Palavra>();
		ConsultaController consulta = new ConsultaController(new ConnectionFactory(host, user, password).criaConexao());
		

		if(numeroMaximo > consulta.consultaNumeroDeLinhas("palavras")) numeroMaximo = consulta.consultaNumeroDeLinhas("palavras");
		
		int numeroRandomico = sorteiaNumero(1, consulta.consultaUltimaLinha("palavras"));
		Palavra palavraGerada = consulta.consulta(numeroRandomico,"palavras");
		

		for(int i = 0; i < numeroMaximo; i++) {
			while(listaDePalavras.contains(palavraGerada) || palavraGerada == null){
				numeroRandomico = sorteiaNumero(1, consulta.consultaUltimaLinha("palavras"));
				palavraGerada = consulta.consulta(numeroRandomico,"palavras");
			}
		listaDePalavras.add(palavraGerada);
		}
		return new ResponseEntity<LinkedList<Palavra>>(listaDePalavras, HttpStatus.OK);
	}

}
