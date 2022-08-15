package com.gft.ohMyDog.dto.cachorro;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.ohMyDog.entities.Cachorro;
import com.gft.ohMyDog.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CachorroDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private String nome;
	
	private String raca;
	
	@Enumerated(EnumType.STRING)
	private Genero sexo;
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private String peso;
	
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date nascimento;
	
	//------------------------------------------------------------------------------------------------------------
	
	public CachorroDTO(Cachorro cachorro) {
		this.id = cachorro.getId();
		this.nome = cachorro.getNome();
		this.raca = cachorro.getRaca();
		this.sexo = cachorro.getSexo();
		this.peso = cachorro.getPeso();
		this.nascimento = cachorro.getNascimento();
	}	
}
