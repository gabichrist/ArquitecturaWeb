package application;


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
		Estudiante e1 = new Estudiante(245852, "Juan", "Perez" , 25, "M", "39526555", "Tandil");
		Estudiante e2 = new Estudiante(268584, "Maria", "Gonzalez" , 22, "F", "42526555", "Mar del Plata");
		Estudiante e3 = new Estudiante(256852, "Esteban", "Lopez" , 21, "F", "43252221", "Tandil");
		Estudiante e4 = new Estudiante(256343, "Martin", "Rodriguez" , 20, "M", "44652221", "Tandil");
		Estudiante e5 = new Estudiante(256444, "Daiana", "Ramirez" , 21, "F", "40652221", "Balcarce");
		
		servicioEstudiante.altaEstudiante(e1);
		servicioEstudiante.altaEstudiante(e2);
		servicioEstudiante.altaEstudiante(e3);
		servicioEstudiante.altaEstudiante(e4);
		servicioEstudiante.altaEstudiante(e5);
//		// b- Matricular un estudiante a una carrera
		servicioInscripcion.matricularEstudiante(e1, c1);
		servicioInscripcion.matricularEstudiante(e1, c2);
		servicioInscripcion.matricularEstudiante(e1, c3);
		servicioInscripcion.matricularEstudiante(e2, c1);
		servicioInscripcion.matricularEstudiante(e4, c1);
		servicioInscripcion.matricularEstudiante(e5, c1);
	
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