package com.gft.ohMyDog.dto.atendimento;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.ohMyDog.dto.cachorro.CachorroDTO;
import com.gft.ohMyDog.dto.cliente.ClienteEndDTO;
import com.gft.ohMyDog.dto.veterinario.VeterinarioDTO;
import com.gft.ohMyDog.entities.Atendimento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data	
public class AtendimentoDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataConsulta = Calendar.getInstance().getTime();
	private String pesoAtual;
	private Boolean vacinasEmDia;
	private String diagnostico;
	private String comentarios;
	private VeterinarioDTO vet;
	private ClienteEndDTO tutor;
	private CachorroDTO cachorro;
	
	//-----------------------------------------------------------------------------------------------------------

	public AtendimentoDTO(Atendimento atend) {
		this.id = atend.getId();
		this.dataConsulta = atend.getDataConsulta();
		this.vacinasEmDia = atend.getVacinasEmDia();
		this.diagnostico = atend.getDiagnostico();
		this.comentarios = atend.getComentarios();
	}
}
