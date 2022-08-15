package com.gft.ohMyDog.dto.cliente;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.gft.ohMyDog.entities.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private String nome;
	
	@Email
	@Column(unique = true)
	@NotBlank(message = "Preenchimento obrigatório!")
	private String email;
	
	@CPF
	@Column(unique = true)
	@NotBlank(message = "Preenchimento obrigatório!")
	private String cpf;
	
	//------------------------------------------------------------------------------------------------------------
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
	}
}
