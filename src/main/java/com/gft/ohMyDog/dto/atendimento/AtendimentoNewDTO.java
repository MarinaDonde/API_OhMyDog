package com.gft.ohMyDog.dto.atendimento;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.ohMyDog.entities.Atendimento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data	
public class AtendimentoNewDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataConsulta = Calendar.getInstance().getTime();
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private String pesoAtual;
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private Boolean vacinasEmDia;
	
	@NotBlank(message = "Preenchimento obrigatório!")
	private String diagnostico;
	private String comentarios;
	private Long vetId;
	private Long tutorId;
	private Long dogId;
	
	//------------------------------------------------------------------------------------------------------------
	
	public AtendimentoNewDTO(Atendimento atend, Long vetId, Long tutorId, Long dogId) {
		this.dataConsulta = atend.getDataConsulta();
		this.pesoAtual = atend.getPesoAtual();
		this.vacinasEmDia = atend.getVacinasEmDia();
		this.diagnostico = atend.getDiagnostico();
		this.comentarios = atend.getComentarios();
		this.vetId = vetId;
		this.tutorId = tutorId;
		this.dogId = dogId;
	}
	
}
