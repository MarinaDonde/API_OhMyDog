package com.gft.ohMyDog.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.ohMyDog.dto.cliente.ClienteDTO;
import com.gft.ohMyDog.dto.cliente.ClienteEndDTO;
import com.gft.ohMyDog.dto.cliente.ClienteNewDTO;
import com.gft.ohMyDog.dto.cliente.ConsultaClienteDTO;
import com.gft.ohMyDog.entities.Cachorro;
import com.gft.ohMyDog.entities.Cliente;
import com.gft.ohMyDog.entities.Endereco;
import com.gft.ohMyDog.exceptions.ObjectNotFoundException;
import com.gft.ohMyDog.repositories.CachorroRepository;
import com.gft.ohMyDog.repositories.ClienteRepository;
import com.gft.ohMyDog.repositories.EnderecoRepository;

@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	private final EnderecoRepository enderecoRepository;
	private final CachorroRepository cachorroRepository;
		
	public ClienteService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, CachorroRepository cachorroRepository) {
		this.clienteRepository = clienteRepository;
		this.enderecoRepository = enderecoRepository;
		this.cachorroRepository = cachorroRepository;
	}
	
	public Cliente findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		cachorroRepository.saveAll(cliente.getCachorros());
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		findById(cliente.getId());
		return clienteRepository.save(cliente);
	}

	public void delete(Long id) {
		findById(id);
		clienteRepository.deleteById(id);		
	}
	
	public static Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpf());
	}
	
	public static ClienteDTO fromEntity(Cliente cli) {
		return new ClienteDTO(cli.getId(), cli.getNome(), cli.getEmail(), cli.getCpf());
	}
	
	public static Cliente fromDTO(ClienteEndDTO dto) {
		return new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getTelefones(), dto.getEnderecos());
	}
	
	public static ClienteEndDTO fromSomePartOfEntity(Cliente cli) {
		return new ClienteEndDTO(cli.getId(), cli.getNome(), cli.getEmail(), cli.getCpf(), cli.getTelefones(),
				cli.getEnderecos());
	}
	
	public static Cliente fromDTO(ConsultaClienteDTO dto) {
		return new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getTelefones(),
				dto.getCachorros(), dto.getEnderecos());
	}
	
	public static ConsultaClienteDTO fromAllEntity(Cliente cli) {
		return new ConsultaClienteDTO(cli.getId(), cli.getNome(), cli.getEmail(), cli.getCpf(), cli.getTelefones(),
				cli.getCachorros(), cli.getEnderecos());
	}
		
	public static Cliente fromDTO(ClienteNewDTO dto) throws IOException {
		ApiCepService cepService = new ApiCepService();
		cepService.findAdressByCep(dto);
		Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpf());
		Endereco end = new Endereco(null, dto.getCep(), dto.getLogradouro(), dto.getBairro(), dto.getLocalidade(), 			
				 dto.getUf(), dto.getNumero(), cli, null);
		Cachorro dog = new Cachorro(null, dto.getNomeCao(), dto.getRaca(), dto.getSexo(), dto.getPeso(), dto.getNascimento(), cli);
		
		cli.getTelefones().add(dto.getTelefone1());
		if (dto.getTelefone2() != null ) {
			cli.getTelefones().add(dto.getTelefone2());
		}
		if (dto.getTelefone3() != null ) {
			cli.getTelefones().add(dto.getTelefone3());
		}
		
		cli.getEnderecos().add(end);
		cli.getCachorros().add(dog);
		return cli;
	}
}
