package com.gft.ohMyDog.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Atendimento implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataConsulta = Calendar.getInstance().getTime();	
	private String pesoAtual;
	private Boolean vacinasEmDia;
	private String diagnostico;
	private String comentarios;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vet_id")
	private Veterinario vet;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tutor_id")
	private Cliente tutor;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cachorro_id")
	private Cachorro cachorro;
	
	//------------------------------------------------------------------------------------------------------------

	public Atendimento(Long id, Date dataConsulta, String pesoAtual, Boolean vacinasEmDia, String diagnostico,
			String comentarios) {
		this.id = id;
		this.dataConsulta = dataConsulta;
		this.pesoAtual = pesoAtual;
		this.vacinasEmDia = vacinasEmDia;
		this.diagnostico = diagnostico;
		this.comentarios = comentarios;
	}
}
