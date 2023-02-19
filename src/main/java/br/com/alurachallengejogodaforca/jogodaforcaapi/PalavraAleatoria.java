package br.com.alurachallengejogodaforca.jogodaforcaapi;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alurachallengejogodaforca.jogodaforcaapi.controller.ConsultaController;
import br.com.alurachallengejogodaforca.jogodaforcaapi.modelo.Palavra;

@RestController
public class PalavraAleatoria {
	
	@GetMapping("/palavra-aleatoria")
	public ArrayList<Palavra> palavra() {
		ConsultaController consulta = new ConsultaController();
		
		return consulta.consultaSimples();
	}

}
