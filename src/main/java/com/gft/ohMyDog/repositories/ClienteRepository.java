package com.gft.ohMyDog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gft.ohMyDog.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Transactional(readOnly = true)
	Cliente findByNome(String nome);
}
