	package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Estudiante;

@Repository
@Transactional
public class EstudianteRepositoryImpl implements IEstudianteRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(Estudiante estudiante) {
		
		this.entityManager.persist(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
	
		this.entityManager.merge(estudiante);
	}

	@Override
	public void actualizarParcial(String apellido, String nombre, Integer id) {

		Query query=this.entityManager
				.createQuery("UPDATE Estudiante e SET e.nombre =:valor1, e.apellido =:valor2 WHERE e.id =: valor3");
		query.setParameter("valor1", nombre);
		query.setParameter("valor2", apellido);
		query.setParameter("valor3", id);
		
		query.executeUpdate();//executeUpdate, retorna un número, es la cantidad de registros
		//return this.entityManager.find(Estudiante.class,id);
	}

	@Override
	public Estudiante seleccionar(Integer id) {
		
		return this.entityManager.find(Estudiante.class,id);
	}

	@Override
	public void eliminar(Integer id) {
	
		this.entityManager.remove(this.seleccionar(id));
	}

	@Override
	public List<Estudiante> buscarTodos(String genero) {

		TypedQuery<Estudiante> myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.genero=:variable", Estudiante.class);
		myQuery.setParameter("variable", genero);
		return myQuery.getResultList();
	}
	
	@Override
	public Estudiante buscarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Estudiante> myQuery = this.entityManager
				.createQuery("SELECT e FROM Estudiante e WHERE e.cedula=:datoCedula ", Estudiante.class);
		myQuery.setParameter("datoCedula", cedula);
		return myQuery.getSingleResult();
	}
	

}
