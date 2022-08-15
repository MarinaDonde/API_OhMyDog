package com.gft.ohMyDog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gft.ohMyDog.dto.cachorro.CachorroDTO;
import com.gft.ohMyDog.entities.Cachorro;
import com.gft.ohMyDog.exceptions.ObjectNotFoundException;
import com.gft.ohMyDog.repositories.CachorroRepository;

@Service
public class CachorroService {
	
	private final CachorroRepository cachorroRepository;
	
	public CachorroService(CachorroRepository cachorroRepository) {
		this.cachorroRepository = cachorroRepository;
	}
	
	public Cachorro findById(Long id) {
		Optional<Cachorro> cachorro = cachorroRepository.findById(id);

		return cachorro.orElseThrow(() -> new ObjectNotFoundException(
				"Cachorro não encontrado! Id: " + id + ", Tipo: " + Cachorro.class.getName())); 
	}
	
	public List<Cachorro> findAll() {
		return cachorroRepository.findAll();
	}

	public Cachorro insert(Cachorro cachorro) {
		cachorro.setId(null);
		return cachorroRepository.save(cachorro);
	}
	
	public Cachorro update(Cachorro cachorro) {
		findById(cachorro.getId());
		return cachorroRepository.save(cachorro);
	}

	public void delete(Long id) {
		findById(id);
		cachorroRepository.deleteById(id);		
	}
	
	public Page<Cachorro> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cachorroRepository.findAll(pageRequest);
	}
	
	public static Cachorro fromDTO(CachorroDTO dto) {
		return new Cachorro(null, dto.getNome(), dto.getRaca(),
				dto.getSexo(), dto.getPeso(), dto.getNascimento());
	}
	
	public static CachorroDTO fromEntity(Cachorro dog) {
		return new CachorroDTO(dog.getId(), dog.getNome(), dog.getRaca(),
				dog.getSexo(), dog.getPeso(), dog.getNascimento());
	}

}
