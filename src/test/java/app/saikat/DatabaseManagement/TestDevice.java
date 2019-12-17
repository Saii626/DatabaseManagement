package app.saikat.DatabaseManagement;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import app.saikat.DIManagement.Exceptions.ClassNotUnderDIException;
import app.saikat.DIManagement.Interfaces.DIManager;
import app.saikat.DatabaseManagement.BaseClasses.RepositoryFactory;
import app.saikat.DatabaseManagement.Device.DeviceModel;
import app.saikat.DatabaseManagement.Device.DeviceModelRepo;
import app.saikat.DatabaseManagement.Device.DeviceType;

public class TestDevice {

	@Test
	public void testDeviceModel() throws ClassNotUnderDIException {

		DIManager manager = DIManager.newInstance();
		manager.initialize("app.saikat");

		DeviceModel model = new DeviceModel();
		model.setName("test");
		model.setDescription("test device description");
		model.setDeviceType(DeviceType.OTHER);
		model.setPassword("hello world");

		RepositoryFactory repositoryFactory = manager.getBeanOfType(RepositoryFactory.class).getProvider().get();

		try (DeviceModelRepo repo = repositoryFactory.getRepository(DeviceModelRepo.class)) {
			repo.saveDevice(model);
		}

		DeviceModel model2;
		try (DeviceModelRepo repo = repositoryFactory.getRepository(DeviceModelRepo.class)) {
			model2 = repo.getDeviceWithID(model.getId());
		}

		assertEquals("device equals", model, model2);

		try (DeviceModelRepo repo = repositoryFactory.getRepository(DeviceModelRepo.class)) {
			model.setData("data random");
			repo.updateDevice(model);
		}

	}
}