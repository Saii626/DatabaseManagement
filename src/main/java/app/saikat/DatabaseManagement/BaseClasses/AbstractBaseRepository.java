package app.saikat.DatabaseManagement.BaseClasses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import app.saikat.LogManagement.Logger;
import app.saikat.LogManagement.LoggerFactory;

public abstract class AbstractBaseRepository implements AutoCloseable {

	public static <T extends AbstractBaseRepository> T getNewInstance(EntityManagerFactory entityManagerFactory) {
		throw new RuntimeException("Override and provide valid initialization");
	}

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// Local EntityManagerInstance
	protected EntityManager entityManager = null;
	// protected EntityTransaction transaction = null;

	protected AbstractBaseRepository(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	@Override
	public void close() {
		try {
			entityManager.getTransaction().commit();
			entityManager.clear();
			entityManager.close();
		} catch (Exception e) {
			logger.error("DB connection close error: ",e);
		}
	}

}