package com.gft.ohMyDog.controllers;

import java.io.IOException;
import java.net.URI;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.ohMyDog.dto.cliente.ClienteDTO;
import com.gft.ohMyDog.dto.cliente.ClienteNewDTO;
import com.gft.ohMyDog.dto.cliente.ConsultaClienteDTO;
import com.gft.ohMyDog.entities.Cliente;
import com.gft.ohMyDog.services.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Busca Cliente por Id")
	public ResponseEntity<ConsultaClienteDTO> findById(@PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok(ClienteService.fromAllEntity(cliente));
	}

	@GetMapping("/page")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Busca paginada de clientes")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(cliente -> new ClienteDTO(cliente));
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Adiciona um Cliente")
	public ResponseEntity<ClienteNewDTO> insert(@Valid @RequestBody ClienteNewDTO dto) throws IOException {
		Cliente cliente = clienteService.insert(ClienteService.fromDTO(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Atualiza o cadastro de um Cliente por Id")
	public ResponseEntity<ClienteDTO> update(@Valid @RequestBody ClienteDTO dto, @PathVariable Long id) {
		Cliente cliente = ClienteService.fromDTO(dto);
		cliente.setId(id);
		cliente = clienteService.update(cliente);
		return ResponseEntity.ok(ClienteService.fromEntity(cliente));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value = "Exclui um cadastro de Cliente por Id")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
