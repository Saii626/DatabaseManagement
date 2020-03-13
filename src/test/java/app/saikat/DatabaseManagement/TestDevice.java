package app.saikat.DatabaseManagement;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import app.saikat.DIManagement.Exceptions.BeanNotFoundException;
import app.saikat.DIManagement.Interfaces.DIManager;
import app.saikat.DatabaseManagement.BaseClasses.RepositoryFactory;
import app.saikat.DatabaseManagement.Device.DeviceModel;
import app.saikat.DatabaseManagement.Device.DeviceRepository;
import app.saikat.DatabaseManagement.Device.DeviceType;

public class TestDevice {

	@Test
	public void testDeviceModel() throws BeanNotFoundException {

		DIManager manager = DIManager.newInstance();
		manager.scan("app.saikat");

		DeviceModel model = new DeviceModel();
		model.setName("test");
		model.setDescription("test device description");
		model.setDeviceType(DeviceType.OTHER);
		model.setPassword("hello world");

		RepositoryFactory repositoryFactory = manager.getBeanOfType(RepositoryFactory.class).getProvider().get();

		try (DeviceRepository repo = repositoryFactory.getRepository(DeviceRepository.class)) {
			repo.saveDevice(model);
		}

		DeviceModel model2;
		try (DeviceRepository repo = repositoryFactory.getRepository(DeviceRepository.class)) {
			model2 = repo.getDeviceWithID(model.getId());
		}

		assertEquals("device equals", model, model2);

		try (DeviceRepository repo = repositoryFactory.getRepository(DeviceRepository.class)) {
			model.setData("data random");
			repo.updateDevice(model);
		}
	}
}