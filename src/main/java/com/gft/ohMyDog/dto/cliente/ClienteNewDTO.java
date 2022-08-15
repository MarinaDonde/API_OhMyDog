package com.gft.ohMyDog.dto.cliente;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.ohMyDog.enums.Genero;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ClienteNewDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private String nome;	
	
	@CPF
	@NotBlank(message = "Preenchimento obrigatório!")
	private String cpf;
	
	@Email
	@NotBlank(message = "Preenchimento obrigatório!")
	private String email;
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private String cep;
	
	//os 4 atributos abaixo serão preenchidos automaticamente através da API externa de CEP
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;

	
	private String numero;
		
	@NotBlank(message = "Preenchimento obrigatório!")
	private String telefone1;
	
	private String telefone2;
	
	private String telefone3;
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private String nomeCao;
	
	private String raca;
	
	private Genero sexo;
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private String peso;
	
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date nascimento;

}
