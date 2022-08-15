package com.gft.ohMyDog.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.gft.ohMyDog.entities.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private String cep;
	
	//os 4 atributos abaixo serão preenchidos automaticamente através da API externa de CEP
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	
	private String numero;
	
	//------------------------------------------------------------------------------------------------------------

	public EnderecoDTO(Endereco endereco) {		
		this.id = endereco.getId();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.bairro = endereco.getBairro();
		this.localidade = endereco.getLocalidade();
		this.uf = endereco.getUf();
		this.numero = endereco.getNumero();
	}
}
