package com.gft.ohMyDog.enums;

import com.gft.ohMyDog.validations.CnpjGroup;
import com.gft.ohMyDog.validations.CpfGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoPessoa {
	
	CPF("Física", "CPF", "000.000.000-00", CpfGroup.class),
	CNPJ("Jurídica", "CNPJ", "00.000.000/000-00", CnpjGroup.class);

	private final String descricao;
	private final String documento;
	private final String mascara;
	private final Class<?> group;
	
}
