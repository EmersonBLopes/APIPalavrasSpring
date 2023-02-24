package br.com.alurachallengejogodaforca.jogodaforcaapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Teste {

	@GetMapping("/teste")
	public String teste() {
		return "Teste railway";
	}
}
