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

import com.gft.ohMyDog.dto.cachorro.CachorroDTO;
import com.gft.ohMyDog.entities.Cachorro;
import com.gft.ohMyDog.services.CachorroService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/cachorros")
public class CachorroController {
	
	private final CachorroService cachorroService;
	
	public CachorroController(CachorroService cachorroService) {
		this.cachorroService = cachorroService;
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Busca Cachorro por Id")	 
	public ResponseEntity<CachorroDTO> findById(@PathVariable Long id) {
		Cachorro dog = cachorroService.findById(id);
		return ResponseEntity.ok(CachorroService.fromEntity(dog));
	}
	
	@GetMapping("/page")
	@PreAuthorize("hasAuthority('usuario') or hasAuthority('admin')")
	@ApiOperation(value = "Busca paginada de cachorros")
	public ResponseEntity<Page<CachorroDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cachorro> list = cachorroService.findPage(page, linesPerPage, orderBy, direction);
		Page<CachorroDTO> listDTO = list.map(dogs -> new CachorroDTO(dogs));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Adiciona um Cachorro")
	public ResponseEntity<CachorroDTO> insert(@Valid @RequestBody CachorroDTO dto) {
		Cachorro dog = cachorroService.insert(CachorroService.fromDTO(dto));
		return ResponseEntity.ok(CachorroService.fromEntity(dog));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Atualiza o cadastro de um Cachorro por Id")
	public ResponseEntity<CachorroDTO> update(@Valid @RequestBody CachorroDTO dto, @PathVariable Long id) {
		Cachorro dog = CachorroService.fromDTO(dto);
		dog.setId(id);
		dog = cachorroService.update(dog);
		return ResponseEntity.ok(CachorroService.fromEntity(dog));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Exclui o cadastro de Cachorro por Id")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cachorroService.delete(id);
		return ResponseEntity.noContent().build();				
	}
	
}
