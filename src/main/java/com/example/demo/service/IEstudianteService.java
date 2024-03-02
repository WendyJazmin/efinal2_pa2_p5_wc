package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;

public interface IEstudianteService {

	//Capa relacionado al negocio
	public void guardar(EstudianteTO estudiante);
	
	public void actualizar(EstudianteTO estudiante);

	public void actualizarParcial(String apellido, String nombre, Integer id);
	
	public Estudiante buscar(Integer id);
	

	public EstudianteTO buscarto(Integer id);
	
	
	public void borrar(Integer id);

	public List<Estudiante> buscarTodos(String genero);

	public List<EstudianteTO> buscarTodosTO();
	
	//--------------------
	
	public List<EstudianteLigeroTO> buscarTodosLigeroTO();
	
	public EstudianteTO buscarPorCedula(String cedula);

	
}
