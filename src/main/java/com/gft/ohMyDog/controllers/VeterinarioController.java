package com.gft.ohMyDog.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gft.ohMyDog.dto.veterinario.VeterinarioDTO;
import com.gft.ohMyDog.entities.Veterinario;
import com.gft.ohMyDog.services.VeterinarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/vets")
public class VeterinarioController {
	
	private final VeterinarioService veterinarioService;
	
	public VeterinarioController(VeterinarioService veterinarioService) {
		this.veterinarioService = veterinarioService;
	}
	
	@GetMapping("/{id}") 
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Busca Veterinário por Id")
	public ResponseEntity<VeterinarioDTO> findById(@PathVariable Long id) {
		Veterinario vet = veterinarioService.findById(id);
		return ResponseEntity.ok(VeterinarioService.fromEntity(vet));
	}
	
	@GetMapping("/page")
	@PreAuthorize("hasAuthority('usuario') or hasAuthority('admin')")
	@ApiOperation(value = "Busca paginada de cachorros")
	public ResponseEntity<Page<VeterinarioDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Veterinario> list = veterinarioService.findPage(page, linesPerPage, orderBy, direction);
		Page<VeterinarioDTO> listDTO = list.map(vets -> new VeterinarioDTO(vets));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Adiciona um Veterinário")
	public ResponseEntity<VeterinarioDTO> insert(@Valid @RequestBody VeterinarioDTO dto) {
		Veterinario vet = veterinarioService.insert(VeterinarioService.fromDTO(dto));
		return ResponseEntity.ok(VeterinarioService.fromEntity(vet));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Atualiza o cadastro de um Veterinário por Id")
	public ResponseEntity<VeterinarioDTO> update(@Valid @RequestBody VeterinarioDTO dto, @PathVariable Long id) {
		Veterinario vet = VeterinarioService.fromDTO(dto);
		vet.setId(id);
		vet = veterinarioService.update(vet);
		return ResponseEntity.ok(VeterinarioService.fromEntity(vet));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Exclui um cadastro de Veterinário por Id")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		veterinarioService.delete(id);
		return ResponseEntity.noContent().build();				
	}

}
