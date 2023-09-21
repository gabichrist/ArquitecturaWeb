package application;

import javax.persistence.EntityManager;

import factory.ConnectionFactory;
//import service.ServicioCarrera;
//import service.ServicioEstudiante;

public class Application {

	public static void main(String[] args) {
		
			
		ConnectionFactory connectionFactory = new ConnectionFactory();
		EntityManager em = connectionFactory.connection();
		
		
		em.getTransaction().begin();
		
		//ServicioEstudiante servicioEstudiante = new ServicioEstudiante();
		//ServicioCarrera servicioCarrera = new ServicioCarrera();
				
		// Se crean y cargan estudiantes en la base de datos
		//servicioEstudiante.altaEstudiante(null);
	//	Estudiante e1 = new Estudiante(245852, "Juan", "Perez" , 25, "M", "39526555", "Tandil");
		//Estudiante e2 = new Estudiante(245953, "Maria", "Rodriguez" , 31, "F", "36222544", "Tandil");
		//em.persist(e1);
		//em.persist(e2);
		em.getTransaction().commit();
	
	}

}