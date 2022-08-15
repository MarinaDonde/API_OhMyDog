package com.gft.ohMyDog.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.gft.ohMyDog.enums.TipoPessoa;
import com.gft.ohMyDog.validations.ClienteGroupSequenceProvider;
import com.gft.ohMyDog.validations.CnpjGroup;
import com.gft.ohMyDog.validations.CpfGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
public class Veterinario implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nome;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;
	
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	private String numDocumento;
	
	private String especializacao;

	@ElementCollection
	@CollectionTable(name = "TelefoneVet")
	private Set<String> telefones = new HashSet<>();
	
	@OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	@OneToMany(mappedBy = "vet")
	private List<Atendimento> consultas = new ArrayList<>();
	
	//------------------------------------------------------------------------------------------------------------

	public Veterinario(Long id) {
		this.id = id;
	}

	//Construtor utilizado para VeterinarioDTO
	public Veterinario(Long id, String nome, String email, TipoPessoa tipoPessoa, 
			String numDocumento, String especializacao) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.tipoPessoa = tipoPessoa;
		this.numDocumento = numDocumento;
		this.especializacao = especializacao;
	}
}
