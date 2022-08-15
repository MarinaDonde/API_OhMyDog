package com.gft.ohMyDog.validations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.gft.ohMyDog.entities.Veterinario;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Veterinario>{
	
	@Override
	public List<Class<?>> getValidationGroups(Veterinario veterinario) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(Veterinario.class);
		
		if (isPessoaSelecionada(veterinario)) {
			groups.add(veterinario.getTipoPessoa().getGroup());
		}
		return groups;
	}
	
	protected boolean isPessoaSelecionada(Veterinario veterinario) {
		return veterinario != null && veterinario.getTipoPessoa() != null;
	}

}
