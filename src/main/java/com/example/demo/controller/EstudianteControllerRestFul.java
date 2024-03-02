package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;

import com.example.demo.service.to.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;


import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

//METODOS ESTÁTICOS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//API: un api siempre viene determinado por un proyecto

//Servicio-controller: Clase Controller
@RestController//Servicio
//se tiene que crear una URL
@RequestMapping(path="/estudiantes")
@CrossOrigin//(value="http://localhost:8080")
public class EstudianteControllerRestFul {

    @Autowired
    private IEstudianteService estudianteService;
    
  
 
    //las capacidades vienen representadas por métdos de una clase
 
   //Path Variable

   
  

	 //BUSCAR POR CÉDULA
	//http://localhost:8088/API/v1.0/Matricula/profesores/${cedula}
	 @GetMapping(path="/{cedula}",produces = "application/json")
	 public ResponseEntity<EstudianteTO> buscarporCedula(@PathVariable String cedula){
		 
		 EstudianteTO pro = this.estudianteService.buscarPorCedula(cedula);
		 
		 return ResponseEntity.status(HttpStatus.OK).body(pro);
		 
	 }
	 
    
    
    ///--------------------------------------------------------
    @GetMapping(path="/hateoas",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstudianteTO>> consultarTodosHateoas() {
		List<EstudianteTO> lista = this.estudianteService.buscarTodosTO();
		
		
		for(EstudianteTO est: lista) {
			Link link= linkTo(methodOn(EstudianteControllerRestFul.class). consultarTodosHateoas())
					.withRel("Materias");//se coloca la capacidad donde se encuentra la clase
			est.add(link);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(lista); //todo lo que no es de ka data principal va en al cabecera
    }

    
    @GetMapping(path="/estudianteLigero",produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<List<EstudianteLigeroTO>> consultarTodosLigeroHateoas() {
   		List<EstudianteLigeroTO> lista = this.estudianteService.buscarTodosLigeroTO();
   		
   		
   		for(EstudianteLigeroTO est: lista) {
   			Link link= linkTo(methodOn(EstudianteControllerRestFul.class).consultarTodosLigeroHateoas())
   					.withSelfRel();//se coloca la capacidad donde se encuentra la clase
   			est.add(link);
   		}
   		
   		return ResponseEntity.status(HttpStatus.OK).body(lista); //todo lo que no es de ka data principal va en al cabecera
       }
    

    //http://localhost:8088/API/v1.0/Matricula/insertar
    @PostMapping(path="/insertar",consumes =MediaType.APPLICATION_JSON_VALUE)
    public void guardar(@RequestBody EstudianteTO estudiante) {
        this.estudianteService.guardar(estudiante);
    }

    
  

 
}