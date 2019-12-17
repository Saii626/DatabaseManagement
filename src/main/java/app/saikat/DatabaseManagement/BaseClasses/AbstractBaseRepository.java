package app.saikat.DatabaseManagement.BaseClasses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.saikat.Annotations.DIManagement.ScanSubClass;

@ScanSubClass(autoManage = true)
public abstract class AbstractBaseRepository implements AutoCloseable {

	protected Logger logger = LogManager.getLogger(this.getClass());

	protected EntityManager entityManager = null;

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