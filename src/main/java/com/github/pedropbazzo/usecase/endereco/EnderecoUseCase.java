package com.github.pedropbazzo.usecase.endereco;

import java.io.NotActiveException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pedropbazzo.dataprovider.endereco.entity.Endereco;
import com.github.pedropbazzo.dataprovider.sql.endereco.BuscarEnderecoSQLDataProvider;
import com.github.pedropbazzo.gateway.endereco.BuscarEnderecoGateway;
import com.github.pedropbazzo.toggle.config.FeatureTogglesConfig;

@Service
public class EnderecoUseCase {
	
	private static final String FEATURE_TOGGLE_CORREIOS = "dataprovider.correios";
	private static final String FEATURE_TOGGLE_DATABASE_H2 = "dataprovider.database-h2";
	
	private BuscarEnderecoGateway buscarEnderecoGateway;
	private BuscarEnderecoSQLDataProvider buscarEnderecoDataProvider;
	
	private FeatureTogglesConfig featureToggle;
	
	
	@Autowired
	public EnderecoUseCase(BuscarEnderecoGateway gateway, FeatureTogglesConfig featureToggle, BuscarEnderecoSQLDataProvider buscarEnderecoDataProvider) {
		this.featureToggle = featureToggle;
		this.buscarEnderecoGateway = gateway;
		this.buscarEnderecoDataProvider = buscarEnderecoDataProvider;
	}
	
	
	public Endereco buscarEndereco(String cep) throws NotActiveException {
		if(featureToggle.isEnabled(FEATURE_TOGGLE_CORREIOS)) {
			return buscarEnderecoGateway.buscarEndereco(cep);
		}
		
		if(featureToggle.isEnabled(FEATURE_TOGGLE_DATABASE_H2)) {
			return buscarEnderecoDataProvider.findByCep(cep);
		}
		
		throw new NotActiveException("Feature de buscarEndereco está desligada");
	}
	
}
