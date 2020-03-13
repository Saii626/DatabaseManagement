package app.saikat.DatabaseManagement.Device;

import java.util.UUID;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import app.saikat.DatabaseManagement.BaseClasses.AbstractBaseRepository;

public class DeviceRepository extends AbstractBaseRepository {

	protected DeviceRepository(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	public void saveDevice(DeviceModel model) {
		entityManager.persist(model);
	}

	public DeviceModel updateDevice(DeviceModel model) {
		return entityManager.merge(model);
	}

	public DeviceModel getDeviceWithID(UUID id) {
		TypedQuery<DeviceModel> getById = entityManager
				.createQuery("FROM DeviceModel M WHERE M.id = :id", DeviceModel.class);
		return getById.setParameter("id", id)
				.getSingleResult();
	}

	public DeviceModel getDeviceWithName(String name) {
		TypedQuery<DeviceModel> getById = entityManager
				.createQuery("FROM DeviceModel M WHERE M.name = :name", DeviceModel.class);
		return getById.setParameter("name", name)
				.getSingleResult();
	}
}