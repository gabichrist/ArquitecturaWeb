package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Estudiante;

public class Application {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entregable2");
		EntityManager em = emf.createEntityManager();
		
		// Se crean y cargan estudiantes en la base de datos
		em.getTransaction().begin();
		Estudiante e1 = new Estudiante(245852, "Juan", "Perez" , 25, "M", "39526555", "Tandil");
		Estudiante e2 = new Estudiante(245953, "Maria", "Rodriguez" , 31, "F", "36222544", "Tandil");
		em.persist(e1);
		em.persist(e2);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}