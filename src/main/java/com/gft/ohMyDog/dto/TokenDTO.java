package com.gft.ohMyDog.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TokenDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private String token;

}
