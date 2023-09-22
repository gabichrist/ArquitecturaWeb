package application;

import javax.persistence.EntityManager;

import entity.Carrera;
import entity.Estudiante;
import entity.Inscripcion;
import entity.InscripcionId;
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
				
		// Se crean y cargan estudiantes en la base de datos si no existen
		//servicioEstudiante.altaEstudiante(null);
		Estudiante e1 = em.find(Estudiante.class, 245852);
		if (e1 == null) {
			e1 = new Estudiante(245852, "Juan", "Perez" , 25, "M", "39526555", "Tandil");
			em.persist(e1);			
		}
		Estudiante e2 = em.find(Estudiante.class, 245953);
		if (e2 == null) {
			e2 = new Estudiante(245953, "Maria", "Rodriguez" , 31, "F", "36222544", "Tandil");
			em.persist(e2);			
		}

		Carrera c1 = em.find(Carrera.class, 1);
		if (c1 == null) {
			c1 = new Carrera(1, "sistemas");
			em.persist(c1);
		}
		Carrera c2 = em.find(Carrera.class, 2);
		if (c2 == null) {
			c2 = new Carrera(2, "tudai");
			em.persist(c2);
		}
		Inscripcion i1 = em.find(Inscripcion.class, new InscripcionId(e1, c1));
		if (i1 == null) {
			i1 = new Inscripcion(e1, c1);
			em.persist(i1);
		}
		Inscripcion i2 = em.find(Inscripcion.class, new InscripcionId(e1, c2));
		if (i2 == null) {
			i1 = new Inscripcion(e1, c2);
			em.persist(i1);
		}
		em.getTransaction().commit();
	
	}

}