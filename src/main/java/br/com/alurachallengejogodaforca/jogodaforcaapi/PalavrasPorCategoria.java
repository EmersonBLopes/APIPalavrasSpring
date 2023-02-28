package br.com.alurachallengejogodaforca.jogodaforcaapi;

import java.util.LinkedList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alurachallengejogodaforca.jogodaforcaapi.modelo.Palavra;

@RestController
public class PalavrasPorCategoria {
	
	@GetMapping("/palavras-categoria")
	public LinkedList<Palavra> gerarPalavrasPorCategoria(@RequestParam(value ="categorias") String[] categorias){
			LinkedList<Palavra> listaDePalavras = new LinkedList<Palavra>();
				
			return listaDePalavras;
	}
}
