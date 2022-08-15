package com.gft.ohMyDog.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private String numero;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "vet_id")
	private Veterinario veterinario;
	
	//------------------------------------------------------------------------------------------------------------
	
	//Construtor utilizado na Classe ClienteNewDTO
	public Endereco(Long id, String cep, String numero, Cliente cliente, Veterinario veterinario) {
		this.id = id;
		this.cep = cep;	
		this.numero = numero;
		this.cliente = cliente;
		this.veterinario = veterinario;		
	}
	
	//Construtor utilizado no EnderecoDTO
	public Endereco(Long id, String cep, String logradouro, String bairro, String localidade,
			String uf, String numero) {
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.numero = numero;
	}
}
