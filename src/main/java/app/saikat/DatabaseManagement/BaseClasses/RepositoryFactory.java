package app.saikat.DatabaseManagement.BaseClasses;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import app.saikat.DatabaseManagement.Device.DeviceModelRepo;

public class RepositoryFactory {

	private final static EntityManagerFactory entityManagerFactory;

	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("postgresql");
	}

	@SuppressWarnings("unchecked")
	public static <T extends AbstractBaseRepository>  T getRepository(Class<T> cls) {

		if (entityManagerFactory == null) {
			throw new RuntimeException("Set entityManagerFactory before calling this method");
		}
		
		if (cls.equals(DeviceModelRepo.class)) {
			return (T) DeviceModelRepo.getNewInstance(entityManagerFactory);
		} else {
			throw new RuntimeException("Don't know how to create " +cls.getSimpleName()+" repository");
		}
	}
	
}