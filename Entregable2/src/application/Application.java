package application;

//import java.util.List;

import javax.persistence.EntityManager;

//import entity.Carrera;
//import entity.Estudiante;
import factory.ConnectionFactory;
//import service.ServicioCarrera;
//import service.ServicioEstudiante;

public class Application {

	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		EntityManager em = connectionFactory.connection();
		
		//ServicioEstudiante servicioEstudiante = new ServicioEstudiante();
	//	ServicioCarrera servicioCarrera = new ServicioCarrera();
		
		// Se crean y cargan carreras
		//servicioCarrera.altaCarrera("TUDAI");
		//servicioCarrera.altaCarrera("TUARI");
		//servicioCarrera.altaCarrera("SISTEMAS");		
				
		
		
		// a- Dar de alta estudiante		
		//Estudiante e1 = new Estudiante(245852, "Juan", "Perez" , 25, "M", "39526555", "Tandil");
		//Estudiante e2 = new Estudiante(268584, "Maria", "Gonzalez" , 22, "F", "42526555", "Mar del Plata");
		
		//servicioEstudiante.altaEstudiante(e1);
		//servicioEstudiante.altaEstudiante(e2);
		
		// b- Matricular un estudiante a una carrera
		//servicioestudiante.matricularEstudiante()
	
		// c- Recuperar un estudiante, y especificar algun criterio de ordenamiento simple
		//List<Estudiante> listaEstudiantesPorEdad = servicioEstudiante.listarEstudiantesOrdenadosporEdad();
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
		
		
		connectionFactory.close(em);
	
	}

}