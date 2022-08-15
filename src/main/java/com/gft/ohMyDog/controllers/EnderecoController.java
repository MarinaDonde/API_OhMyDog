package com.gft.ohMyDog.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.ohMyDog.dto.EnderecoDTO;
import com.gft.ohMyDog.entities.Endereco;
import com.gft.ohMyDog.services.ApiCepService;
import com.gft.ohMyDog.services.EnderecoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/enderecos")
public class EnderecoController {

	private final EnderecoService enderecoService;

	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Adiciona um Endereço")
	public ResponseEntity<EnderecoDTO> insert(@Valid @RequestBody EnderecoDTO dto) throws IOException {
		ApiCepService cepService = new ApiCepService();
		cepService.findAdressByCep(dto);
		Endereco end = enderecoService.insert(EnderecoService.fromDTO(dto));
		return ResponseEntity.ok(EnderecoService.fromEntity(end));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Atualiza o cadastro de um Endereço por Id")
	public ResponseEntity<EnderecoDTO> update(@RequestBody EnderecoDTO dto, @PathVariable Long id) {
		Endereco end = EnderecoService.fromDTO(dto);
		end.setId(id);
		end = enderecoService.update(end);
		return ResponseEntity.ok(EnderecoService.fromEntity(end));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Exclui um cadastro de Endereço por Id")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		enderecoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
