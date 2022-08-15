package com.gft.ohMyDog.controllers;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.ohMyDog.dto.atendimento.AtendimentoDTO;
import com.gft.ohMyDog.dto.atendimento.AtendimentoNewDTO;
import com.gft.ohMyDog.entities.Atendimento;
import com.gft.ohMyDog.services.AtendimentoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/atendimentos")
public class AtendimentoController {
	
	private final AtendimentoService atendimentoService;
	
	public AtendimentoController(AtendimentoService atendimentoService) {
		this.atendimentoService = atendimentoService;
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Busca Atendimento por Id")	 
	public ResponseEntity<AtendimentoDTO> findById(@PathVariable Long id) {
		Atendimento atend = atendimentoService.findById(id);
		return ResponseEntity.ok(AtendimentoService.fromEntity(atend));
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Busca todos os Atendimentos")
	public ResponseEntity<Stream<AtendimentoDTO>> findAll() {
		List<Atendimento> list = atendimentoService.findAll();
		Stream<AtendimentoDTO> listDTO = list.stream().map(AtendimentoService::fromEntity);
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Adiciona um Atendimento")
	public ResponseEntity<AtendimentoDTO> insert(@RequestBody AtendimentoNewDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(AtendimentoService.fromEntity(atendimentoService.insert(AtendimentoService.fromDTO(dto))));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Atualiza o cadastro de um Atendimento por Id")
	public ResponseEntity<AtendimentoDTO> update(@Valid @RequestBody AtendimentoDTO dto, @PathVariable Long id) {
		Atendimento atend = AtendimentoService.fromDTO(dto);
		atend.setId(id);
		atend = atendimentoService.update(atend);
		return ResponseEntity.ok(AtendimentoService.fromEntity(atend));
	}

}
