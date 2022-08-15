package com.gft.ohMyDog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gft.ohMyDog.dto.veterinario.VeterinarioDTO;
import com.gft.ohMyDog.entities.Veterinario;
import com.gft.ohMyDog.exceptions.ObjectNotFoundException;
import com.gft.ohMyDog.repositories.VeterinarioRepository;

@Service
public class VeterinarioService {
	
	private final VeterinarioRepository veterinarioRepository;
	
	public VeterinarioService(VeterinarioRepository veterinarioRepository) {
		this.veterinarioRepository = veterinarioRepository;
	}
	
	public Veterinario findById(Long id) {
		Optional<Veterinario> obj = veterinarioRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Veterinário não encontrado! Id: " + id + ", Tipo: " + Veterinario.class.getName())); 
	}
	
	public List<Veterinario> findAll() {
		return veterinarioRepository.findAll();
	}

	public Veterinario insert(Veterinario vet) {
		vet.setId(null);
		return veterinarioRepository.save(vet);
	}

	public Veterinario update(Veterinario vet) {
		findById(vet.getId());
		return veterinarioRepository.save(vet);
	}

	public void delete(Long id) {
		findById(id);
		veterinarioRepository.deleteById(id);		
	}
	
	public Page<Veterinario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return veterinarioRepository.findAll(pageRequest);
	}
	
	public static Veterinario fromDTO(VeterinarioDTO dto) {
		return new Veterinario(null, dto.getNome(), dto.getEmail(), 
				dto.getTipoPessoa(), dto.getNumDocumento(), dto.getEspecializacao());
	}
	
	public static VeterinarioDTO fromEntity(Veterinario vet) {
		return new VeterinarioDTO(vet.getId(), vet.getNome(), vet.getEmail(),
				vet.getTipoPessoa(), vet.getNumDocumento(), vet.getEspecializacao());
	}

}
