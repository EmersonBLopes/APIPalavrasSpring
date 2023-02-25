package br.com.alurachallengejogodaforca.jogodaforcaapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.random.RandomGenerator;

import br.com.alurachallengejogodaforca.jogodaforcaapi.controller.ConsultaController;
import br.com.alurachallengejogodaforca.jogodaforcaapi.modelo.Palavra;

@RestController
public class PalavrasAleatorias {
	
	public int sorteiaNumero(int min, int max) {
		return RandomGenerator.getDefault().nextInt(min,max+1);
	}
	
	@GetMapping("/palavras-aleatorias")
	public LinkedList<Palavra> palavra(@RequestParam(name = "numeroMaximo", defaultValue = "5") int numeroMaximo) {
		
		LinkedList<Palavra> listaDePalavras = new LinkedList<Palavra>();
		ConsultaController consulta = new ConsultaController();
		

		if(numeroMaximo > consulta.consultaNumeroDeLinhas("palavras")) numeroMaximo = 1;
		
		int numeroRandomico = sorteiaNumero(1, consulta.consultaUltimaLinha("palavras"));
		Palavra palavraGerada = consulta.consultaSimples(numeroRandomico);
		

		for(int i = 0; i < numeroMaximo; i++) {
			while(listaDePalavras.contains(palavraGerada) || palavraGerada == null){
				numeroRandomico = sorteiaNumero(1, consulta.consultaUltimaLinha("palavras"));
				palavraGerada = consulta.consultaSimples(numeroRandomico);
			}
		listaDePalavras.add(palavraGerada);
		}
		
		return listaDePalavras;
	}

}
