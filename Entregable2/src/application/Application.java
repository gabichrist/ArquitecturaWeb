package application;


import java.util.Arrays;
import java.util.List;
import entity.Carrera;
import entity.Estudiante;
import service.ServicioCarrera;
import service.ServicioEstudiante;
import service.ServicioInscripcion;

public class Application {

	public static void main(String[] args) {
		
		
		ServicioEstudiante servicioEstudiante = new ServicioEstudiante();
		ServicioCarrera servicioCarrera = new ServicioCarrera();
		ServicioInscripcion servicioInscripcion = new ServicioInscripcion();
		
		// Se crean y cargan carreras
		Carrera c1 = servicioCarrera.altaCarrera("TUDAI");
		Carrera c2 = servicioCarrera.altaCarrera("TUARI");
		Carrera c3 = servicioCarrera.altaCarrera("SISTEMAS");		

//		// a- Dar de alta estudiante
		List<Estudiante> estudiantes = Arrays.asList(
			new Estudiante(245852, "Juan", "Perez" , 25, "M", "39526555", "Tandil"),
			new Estudiante(268584, "Maria", "Gonzalez" , 22, "F", "42526555", "Mar del Plata"),
			new Estudiante(256852, "Esteban", "Lopez" , 21, "F", "43252221", "Tandil"),
			new Estudiante(256343, "Martin", "Rodriguez" , 20, "M", "44652222", "Tandil"),
			new Estudiante(256444, "Martina", "Cejas" , 21, "F", "40652223", "Balcarce"),
			new Estudiante(256445, "Paz", "Almada" , 23, "F", "40652224", "Tandil"),
			new Estudiante(256446, "Alberto", "Flores" , 25, "M", "40652225", "Balcarce"),
			new Estudiante(256447, "Eduardo", "Martinez" , 27, "M", "40652226", "Tandil"),
			new Estudiante(256448, "Maria Jose", "Rodriguez" , 29, "F", "40652227", "Tandil"),
			new Estudiante(256449, "Laura", "Miranda" , 31, "F", "40652228", "Tandil"),
			new Estudiante(256450, "Perdro", "Beltrame" , 33, "M", "40652229", "Tandil"),
			new Estudiante(256451, "Juan", "De Las Casas" , 35, "M", "40652210", "Mar del Plata"),
			new Estudiante(256452, "Juana", "Ramirez" , 37, "F", "40652211", "Mar del Plata"),
			new Estudiante(256453, "Daiana", "Cejas" , 39, "F", "40652212", "Balcarce"),
			new Estudiante(256454, "Armando", "Del Rio" , 41, "M", "40652213", "Tandil"),
			new Estudiante(256455, "Esteban", "Lopez" , 43, "M", "40652214", "Necochea"),
			new Estudiante(256456, "Constanza", "Fernandez" , 45, "F", "40652215", "Tandil"),
			new Estudiante(256457, "Cecilia", "Martinez" , 47, "F", "40652216", "Tandil")
		);
		estudiantes.forEach(estudiante -> servicioEstudiante.altaEstudiante(estudiante));

//		// b- Matricular un estudiante a una carrera
		for (int i = 0; i <= 15; i++) {
			servicioInscripcion.matricularEstudiante(estudiantes.get(i), c1);			
		}
		for (int i = 12; i <= 18; i++) {
			servicioInscripcion.matricularEstudiante(estudiantes.get(0), c2);
		}
		for (int i = 10; i <= 16; i++) {
		}
		servicioInscripcion.matricularEstudiante(estudiantes.get(0), c3);
		
		servicioInscripcion.graduarEstudiante(estudiantes.get(0), c1);
		servicioInscripcion.graduarEstudiante(estudiantes.get(4), c1);
		servicioInscripcion.graduarEstudiante(estudiantes.get(7), c1);
		servicioInscripcion.graduarEstudiante(estudiantes.get(9), c1);
	
		// c- Recuperar un estudiante, y especificar algun criterio de ordenamiento simple
		servicioEstudiante.listarEstudiantesOrdenadosporEdad();
		//List<Estudiante> listaEstudiantesOrdenado = servicioEstudiante.listarEstudiantesOrdenados("DESC");
		
		// d- Recuperar un estudiante, en base a su numero de libreta universitaria
		int libreta = 268584;
		servicioEstudiante.obtenerEstudianteporLibreta(libreta);
		
		
		// e- Recuperar todos los estudiantes, en base a su genero
		servicioEstudiante.listarEstudiantesporGenero("M");
		servicioEstudiante.listarEstudiantesporGenero("F");

		// f- Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
		servicioCarrera.listarCarrerasOrdenadasporInscriptosDTO();
		
		// g- Recuperar los estudiantes de una determinada carrera, filtrando por ciudad de residencia.		
		servicioEstudiante.listarEstudiantesporCarrerayCiudad(c1, "Tandil");
		
		//ejercicio 3
		servicioCarrera.generarReporteCarrera();
	}

}