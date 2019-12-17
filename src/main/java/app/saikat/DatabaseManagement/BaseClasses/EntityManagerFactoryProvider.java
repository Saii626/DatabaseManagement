package app.saikat.DatabaseManagement.BaseClasses;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import app.saikat.Annotations.DIManagement.Provides;

class EntityManagerFactoryProvider {

	@Provides
	private static EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("postgresql");
	}

}