package br.com.alurachallengejogodaforca.jogodaforcaapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alurachallengejogodaforca.jogodaforcaapi.controller.SolicitarController;

@RestController
public class SolicitarPalavra {
	
	@PostMapping("/solicitar")
	public void solicitar(@RequestParam(value = "palavra", defaultValue="") String palavra) {
		if(palavra == "" || palavra.length() < 3 || !palavra.matches("[a-z]+")) return;
		
		SolicitarController solicitador = new SolicitarController();
		solicitador.adicionarPalavra(palavra);
		
		return;
	}
}

