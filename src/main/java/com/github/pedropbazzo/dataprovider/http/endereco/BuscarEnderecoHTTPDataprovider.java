package com.github.pedropbazzo.dataprovider.http.endereco;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.pedropbazzo.dataprovider.endereco.entity.Endereco;
import com.github.pedropbazzo.gateway.endereco.BuscarEnderecoGateway;

@Component
public class BuscarEnderecoHTTPDataprovider implements BuscarEnderecoGateway {
	
	private static final String API_CORREIOS_URI = "https://api.postmon.com.br/v1/cep/";
	
	@ConditionalOnProperty(
			name = "feature.toggles.dataprovider.correios",
			havingValue = "true",
			matchIfMissing = false
	)
	@Override
	public Endereco buscarEndereco(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Endereco> response = 
				restTemplate.getForEntity(API_CORREIOS_URI.concat(cep), Endereco.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		}
		
		return null;
	}
	
}
