package com.gft.ohMyDog.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class AutenticacaoDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;

}
