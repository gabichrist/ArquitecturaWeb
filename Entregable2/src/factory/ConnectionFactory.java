package factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	private EntityManagerFactory emf;
	
	public EntityManager connection() {
		this.emf = Persistence.createEntityManagerFactory("Entregable2");
		EntityManager em = emf.createEntityManager();
		System.out.println(emf);
		return em;
	}
	
	public void close(EntityManager em) {
		em.close();
		this.emf.close();
	}
}