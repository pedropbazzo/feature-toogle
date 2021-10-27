package com.github.pedropbazzo.dataprovider.sql.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.pedropbazzo.dataprovider.endereco.entity.Endereco;

@Repository
public interface BuscarEnderecoSQLDataProvider extends JpaRepository<Endereco, Integer>{
	
	public Endereco findByCep(String cep);
	
}
