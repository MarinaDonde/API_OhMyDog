package com.gft.ohMyDog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gft.ohMyDog.dto.atendimento.AtendimentoDTO;
import com.gft.ohMyDog.dto.atendimento.AtendimentoNewDTO;
import com.gft.ohMyDog.entities.Atendimento;
import com.gft.ohMyDog.entities.Cachorro;
import com.gft.ohMyDog.entities.Cliente;
import com.gft.ohMyDog.entities.Veterinario;
import com.gft.ohMyDog.exceptions.ObjectNotFoundException;
import com.gft.ohMyDog.repositories.AtendimentoRepository;

@Service
public class AtendimentoService {
	
	private final AtendimentoRepository atendimentoRepository;
	private final VeterinarioService veterinarioService;
	private final ClienteService clienteService;
	private final CachorroService cachorroService;
	
	public AtendimentoService(AtendimentoRepository atendimentoRepository, VeterinarioService veterinarioService,
			ClienteService clienteService, CachorroService cachorroService) {
		this.atendimentoRepository = atendimentoRepository;
		this.veterinarioService = veterinarioService;
		this.clienteService = clienteService;
		this.cachorroService = cachorroService;
	}
	
	public Atendimento findById(Long id) {
		Optional<Atendimento> atendimento = atendimentoRepository.findById(id);

		return atendimento.orElseThrow(() -> new ObjectNotFoundException(
				"Atendimento não encontrado! Id: " + id + ", Tipo: " + Atendimento.class.getName())); 
	}
	
	public List<Atendimento> findAll() {
		return atendimentoRepository.findAll();
	}

	public Atendimento insert(Atendimento atend) {
		Veterinario vet = veterinarioService.findById(atend.getVet().getId());
		Cliente tutor = clienteService.findById(atend.getTutor().getId());
		Cachorro dog = cachorroService.findById(atend.getCachorro().getId());
		atend.setVet(vet);
		atend.setTutor(tutor);
		atend.setCachorro(dog);
		return atendimentoRepository.save(atend);
	}
	
	public Atendimento update(Atendimento atendimento) {
		findById(atendimento.getId());
		return atendimentoRepository.save(atendimento);
	}
	
	public static Atendimento fromDTO(AtendimentoDTO dto) {
		return new Atendimento(null, dto.getDataConsulta(), dto.getPesoAtual(), dto.getVacinasEmDia(), dto.getDiagnostico(),
				dto.getComentarios(), VeterinarioService.fromDTO(dto.getVet()), ClienteService.fromDTO(dto.getTutor()), 
				CachorroService.fromDTO(dto.getCachorro()));
	}
	
	public static AtendimentoDTO fromEntity(Atendimento atend) {
		return new AtendimentoDTO(atend.getId(), atend.getDataConsulta(), atend.getPesoAtual(), atend.getVacinasEmDia(), atend.getDiagnostico(),
				atend.getComentarios(), VeterinarioService.fromEntity(atend.getVet()), ClienteService.fromSomePartOfEntity(atend.getTutor()), 
				CachorroService.fromEntity(atend.getCachorro()));
	}
	
	public static Atendimento fromDTO(AtendimentoNewDTO dto) {
		return new Atendimento(dto.getId(), dto.getDataConsulta(), dto.getPesoAtual(), dto.getVacinasEmDia(), dto.getDiagnostico(),
				dto.getComentarios(), new Veterinario(dto.getVetId()), new Cliente(dto.getTutorId()), new Cachorro(dto.getDogId()));				
	}
}
