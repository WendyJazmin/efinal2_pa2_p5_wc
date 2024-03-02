package com.example.demo.service.to;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;



public class EstudianteTO extends RepresentationModel<EstudianteTO> implements Serializable{//TO transfer object

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4193325159666284431L;

	private Integer id;
	
	private String nombre;
	
	private String apellido;
	
	private String cedula;
	
	private String token;

	
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
 
	
	
}
