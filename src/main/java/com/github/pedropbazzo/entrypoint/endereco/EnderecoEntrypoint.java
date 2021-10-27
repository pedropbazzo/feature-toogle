package com.github.pedropbazzo.entrypoint.endereco;

import java.io.NotActiveException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pedropbazzo.dataprovider.endereco.entity.Endereco;
import com.github.pedropbazzo.usecase.endereco.EnderecoUseCase;

@RestController
@RequestMapping("/superloja/v1/enderecos")
public class EnderecoEntrypoint {
	
	private EnderecoUseCase usecase;
	
	@Autowired
	public EnderecoEntrypoint(EnderecoUseCase dataProvider) {
		this.usecase = dataProvider;
	}

	@GetMapping("/{cep}")
	public ResponseEntity<Endereco> buscarEndereco(@PathVariable String cep) throws NotActiveException {
		System.out.println(cep);
		return new ResponseEntity<Endereco>(usecase.buscarEndereco(cep), HttpStatus.OK);
	}
	
}
