package application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Carrera;
import entity.Estudiante;
import entity.Inscripcion;
import entity.InscripcionId;
import service.ServicioCarrera;
import service.ServicioEstudiante;
import service.ServicioInscripcion;
import factory.ConnectionFactory;

public class Application {

	public static void main(String[] args) {
		
		EntityManager em = ConnectionFactory.connection();
		
		ServicioEstudiante servicioEstudiante = new ServicioEstudiante();
		ServicioCarrera servicioCarrera = new ServicioCarrera();
		ServicioInscripcion servicioInscripcion = new ServicioInscripcion();
		
		// Se crean y cargan carreras
		Carrera c1 = servicioCarrera.altaCarrera("TUDAI");
		Carrera c2 = servicioCarrera.altaCarrera("TUARI");
		Carrera c3 = servicioCarrera.altaCarrera("SISTEMAS");		
				
		// a- Dar de alta estudiante		
		Estudiante e1 = new Estudiante(245852, "Juan", "Perez" , 25, "M", "39526555", "Tandil");
		Estudiante e2 = new Estudiante(268584, "Maria", "Gonzalez" , 22, "F", "42526555", "Mar del Plata");
		Estudiante e3 = new Estudiante(256852, "Esteban", "Lopez" , 21, "F", "43252221", "Tandil");
		servicioEstudiante.altaEstudiante(e1);
		servicioEstudiante.altaEstudiante(e2);
		servicioEstudiante.altaEstudiante(e3);
		
		// b- Matricular un estudiante a una carrera
		servicioInscripcion.matricularEstudiante(e1, c1);
		servicioInscripcion.matricularEstudiante(e1, c2);
		servicioInscripcion.matricularEstudiante(e1, c3);
		servicioInscripcion.matricularEstudiante(e2, c1);
	
		// c- Recuperar un estudiante, y especificar algun criterio de ordenamiento simple
		List<Estudiante> listaEstudiantesPorEdad = servicioEstudiante.listarEstudiantesOrdenadosporEdad();
		//List<Estudiante> listaEstudiantesOrdenado = servicioEstudiante.listarEstudiantesOrdenados("DESC");
		
		// d- Recuperar un estudiante, en base a su numero de libreta universitaria
		//Estudiante estudiantePorLibreta = servicioEstudiante.obtenerEstudianteporLibreta(268584);
		//System.out.println(estudiantePorLibreta.toString());
		
		// e- Recuperar todos los estudiantes, en base a su genero
		//List<Estudiante> listaEstudiantesMasculinos = servicioEstudiante.listarEstudiantesporGenero("M");
		//	List<Estudiante> listaEstudiantesFemeninos = servicioEstudiante.listarEstudiantesporGenero("F");

		// f- Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
		//List<Carrera> listaCarrerasOrdenadasPorinscriptos = servicioCarrera.listarCarrerasOrdenadasporInscriptos();
		
		// g- Recuperar los estudiantes de una determinada carrera, filtrando por ciudad de residencia.		
		
		//List<Estudiante> estudiantesPorCarreraYCiudad = servicioEstudiante.listarEstudiantesporCarrerayCiudad(carrera, "Tandil");
		
		

		// CÓDIGO DE PRUEBA PARA VERIFICAR QUE FUNCIONA LA BD
		// em.getTransaction().begin();
//		Estudiante e1 = em.find(Estudiante.class, 245852);
//		if (e1 == null) {
//			e1 = new Estudiante(245852, "Juan", "Perez" , 25, "M", "39526555", "Tandil");
//			em.persist(e1);			
//		}
//		Estudiante e2 = em.find(Estudiante.class, 245953);
//		if (e2 == null) {
//			e2 = new Estudiante(245953, "Maria", "Rodriguez" , 31, "F", "36222544", "Tandil");
//			em.persist(e2);			
//		}

		// Carrera c1 = em.find(Carrera.class, 1);
		// if (c1 == null) {
		// 	c1 = new Carrera(1, "sistemas");
		// 	em.persist(c1);
		// }
		// Carrera c2 = em.find(Carrera.class, 2);
		// if (c2 == null) {
		// 	c2 = new Carrera(2, "tudai");
		// 	em.persist(c2);
		// }
		// Inscripcion i1 = em.find(Inscripcion.class, new InscripcionId(e1, c1));
		// if (i1 == null) {
		// 	i1 = new Inscripcion(e1, c1);
		// 	em.persist(i1);
		// }
		// Inscripcion i2 = em.find(Inscripcion.class, new InscripcionId(e1, c2));
		// if (i2 == null) {
		// 	i1 = new Inscripcion(e1, c2);
		// 	em.persist(i1);
		// }
		// em.getTransaction().commit();
	
	}

}