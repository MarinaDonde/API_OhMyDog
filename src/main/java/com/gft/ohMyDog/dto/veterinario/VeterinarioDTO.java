package com.gft.ohMyDog.dto.veterinario;

import java.io.Serializable;

import com.gft.ohMyDog.entities.Veterinario;
import com.gft.ohMyDog.enums.TipoPessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VeterinarioDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String nome;
	private String email;
	private TipoPessoa tipoPessoa;
	private String numDocumento;
	private String especializacao;
	
	//------------------------------------------------------------------------------------------------------------
	
	public VeterinarioDTO(Veterinario vet) {
		this.id = vet.getId();
		this.nome = vet.getNome();
		this.email = vet.getEmail();
		this.tipoPessoa = vet.getTipoPessoa();
		this.numDocumento = vet.getNumDocumento();
		this.especializacao = vet.getEspecializacao();
	}	
}
