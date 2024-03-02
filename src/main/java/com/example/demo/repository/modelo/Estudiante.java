package com.example.demo.repository.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="estudiante")
//@JsonIgnoreProperties(value="materias")
public class Estudiante {
	

	@Id
	@GeneratedValue(generator="seq_estudiante", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq_estudiante", sequenceName="seq_estudiante",allocationSize=1)
	@Column(name="estu_id")
	private Integer id;
	
	
	
	@Column(name="estu_nombre")
	private String nombre;
	
	@Column(name="estu_apellido")
	private String apellido;
	
	@Column(name="estu_cedula")
	private String cedula;

	//set y get
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	
	
	

}
