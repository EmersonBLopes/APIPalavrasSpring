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
	
	@GetMapping("/palavras-aleatorias")
	public LinkedList<Palavra> palavra(@RequestParam(name = "numeroMaximo", defaultValue = "5") int numeroMaximo) {
		
		LinkedList<Palavra> listaDePalavras = new LinkedList<Palavra>();
		ConsultaController consulta = new ConsultaController();
		
		int numeroRandomico = RandomGenerator.getDefault().nextInt(1,consulta.consultaNumeroDeLinhas("palavras"));
		Palavra palavraGerada = consulta.consultaSimples(numeroRandomico);
		

		for(int i = 0; i < numeroMaximo; i++) {
			while(listaDePalavras.contains(palavraGerada)){
				numeroRandomico = RandomGenerator.getDefault().nextInt(1,consulta.consultaNumeroDeLinhas("palavras"));
				palavraGerada = consulta.consultaSimples(numeroRandomico);
			}
			listaDePalavras.add(palavraGerada);
		}
		
		return listaDePalavras;
	}

}
