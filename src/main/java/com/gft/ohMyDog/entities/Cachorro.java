package com.gft.ohMyDog.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gft.ohMyDog.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cachorro implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String raca;
	private Genero sexo;
	private String peso;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date nascimento;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Cliente tutor;
	
	@OneToMany(mappedBy = "cachorro")
	private List<Atendimento> consultas = new ArrayList<>();
	
	//------------------------------------------------------------------------------------------------------------
	
	public Cachorro(Long id) {
		this.id = id;
	}
	
	//Construtor utilizado para popular o Banco de Dados
	public Cachorro(Long id, String nome, String raca, Genero sexo, String peso, Date nascimento, Cliente tutor) {
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.sexo = sexo;
		this.peso = peso;
		this.nascimento = nascimento;
		this.tutor = tutor;
	}
	
	//Construtor utilizado para CachorroDTO
	public Cachorro(Long id, String nome, String raca, Genero sexo, String peso, Date nascimento) {
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.sexo = sexo;
		this.peso = peso;
		this.nascimento = nascimento;
	}
}
