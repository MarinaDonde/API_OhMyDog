package com.gft.ohMyDog.dto.veterinario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gft.ohMyDog.dto.EnderecoDTO;
import com.gft.ohMyDog.dto.atendimento.AtendimentoDTO;
import com.gft.ohMyDog.entities.Veterinario;
import com.gft.ohMyDog.enums.TipoPessoa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ConsultaVetDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String nome;
	private String email;
	private TipoPessoa tipoPessoa;
	private String numDocumento;
	private String especializacao;
	private Set<String> telefones = new HashSet<>();
	private List<EnderecoDTO> enderecos = new ArrayList<>();
	private List<AtendimentoDTO> consultas = new ArrayList<>();
	
	//------------------------------------------------------------------------------------------------------------

	public ConsultaVetDTO(Veterinario veterinario) {
		this.id = veterinario.getId();
		this.nome = veterinario.getNome();
		this.email = veterinario.getEmail();
		this.tipoPessoa = veterinario.getTipoPessoa();
		this.numDocumento = veterinario.getNumDocumento();
		this.especializacao = veterinario.getEspecializacao();
	}
}
