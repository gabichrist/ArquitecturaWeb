package factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	static EntityManagerFactory emf;
	static EntityManager em;
	
	public static EntityManagerFactory getInstance() {
		if (ConnectionFactory.emf == null || !ConnectionFactory.emf.isOpen()) 
			ConnectionFactory.emf = Persistence.createEntityManagerFactory("Entregable2");
		
		return ConnectionFactory.emf;
	}
	
	public static EntityManager connection() {
		if (ConnectionFactory.em == null || !ConnectionFactory.em.isOpen()) {			
			EntityManagerFactory entityManagerFactory = ConnectionFactory.getInstance();
			ConnectionFactory.em = entityManagerFactory.createEntityManager();
		}

		return ConnectionFactory.em;
	}
	
	public void close() {
		ConnectionFactory.em.close();
		ConnectionFactory.emf.close();
	}
}