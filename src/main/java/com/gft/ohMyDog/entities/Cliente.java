package com.gft.ohMyDog.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;

	@Column(unique = true)
	private String cpf;
		
	@ElementCollection
	@CollectionTable(name = "Telefone")
	private Set<String> telefones = new HashSet<>();
	
	@OneToMany(mappedBy = "tutor")
	private List<Cachorro> cachorros = new ArrayList<>();
		
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	@OneToMany(mappedBy = "tutor")
	private List<Atendimento> consultas = new ArrayList<>();

	//------------------------------------------------------------------------------------------------------------
	
	public Cliente(Long id) {
		this.id = id;
	}
	
	//Construtor utilizado para popular o Banco de Dados
	public Cliente(Long id, String nome, String email, String cpf) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}

	//Construtor utilizado para ConsultaClienteDTO
	public Cliente(Long id, String nome, String email, String cpf, Set<String> telefones, List<Cachorro> cachorros,
			List<Endereco> enderecos) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefones = telefones;
		this.cachorros = cachorros;
		this.enderecos = enderecos;
	}
	
	//Construtor utilizado para ClienteEndDTO
	public Cliente(Long id, String nome, String email, String cpf, Set<String> telefones, 
			List<Endereco> enderecos) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefones = telefones;
		this.enderecos = enderecos;
	}
}
