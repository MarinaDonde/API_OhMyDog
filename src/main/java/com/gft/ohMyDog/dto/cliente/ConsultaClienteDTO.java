package com.gft.ohMyDog.dto.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gft.ohMyDog.entities.Cachorro;
import com.gft.ohMyDog.entities.Cliente;
import com.gft.ohMyDog.entities.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaClienteDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private Set<String> telefones = new HashSet<>();
	private List<Cachorro> cachorros = new ArrayList<>();
	private List<Endereco> enderecos = new ArrayList<>();
	
	//------------------------------------------------------------------------------------------------------------
	
	public ConsultaClienteDTO(Cliente cli) {
		this.id = cli.getId();
		this.nome = cli.getNome();
		this.email = cli.getEmail();
		this.cpf = cli.getCpf();
		this.telefones = cli.getTelefones();
		this.cachorros = cli.getCachorros();
		this.enderecos = cli.getEnderecos();
	}	
}
