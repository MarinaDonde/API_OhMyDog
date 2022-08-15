package com.gft.ohMyDog.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gft.ohMyDog.dto.EnderecoDTO;
import com.gft.ohMyDog.entities.Endereco;
import com.gft.ohMyDog.exceptions.ObjectNotFoundException;
import com.gft.ohMyDog.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	private final EnderecoRepository enderecoRepository;
	
	public EnderecoService(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}
	
	public Endereco findById(Long id) {
		Optional<Endereco> obj = enderecoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Endereço não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName())); 
	}

	public Endereco insert(Endereco end) {
		end.setId(null);
		return enderecoRepository.save(end);
	}
	
	public Endereco update(Endereco end) {
		findById(end.getId());
		return enderecoRepository.save(end);
	}

	public void delete(Long id) {
		findById(id);
		enderecoRepository.deleteById(id);	
	}
	
	public static Endereco fromDTO(EnderecoDTO dto) {
		return new Endereco(null, dto.getCep(), dto.getLogradouro(), dto.getBairro(), dto.getLocalidade(), 			
				 dto.getUf(), dto.getNumero());
	}
	
	public static EnderecoDTO fromEntity(Endereco end) {
		return new EnderecoDTO(end.getId(), end.getCep(), end.getLogradouro(), end.getBairro(), end.getLocalidade(), 			
				end.getUf(), end.getNumero());
	}	
}
