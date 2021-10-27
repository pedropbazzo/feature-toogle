package com.github.pedropbazzo.gateway.endereco;

import com.github.pedropbazzo.dataprovider.endereco.entity.Endereco;

public interface BuscarEnderecoGateway {
	public Endereco buscarEndereco(String cep);
}
