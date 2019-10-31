package app.saikat.DatabaseManagement;

import static org.junit.Assert.assertEquals;

import javax.persistence.Entity;

import org.junit.Test;

import app.saikat.DIManagement.DIManager;
import app.saikat.DIManagement.Configurations.ClassAnnotationConfig;
import app.saikat.DIManagement.Configurations.ScanConfig;
import app.saikat.DatabaseManagement.BaseClasses.RepositoryFactory;
import app.saikat.DatabaseManagement.Device.DeviceModel;
import app.saikat.DatabaseManagement.Device.DeviceModelRepo;
import app.saikat.DatabaseManagement.Device.DeviceType;

public class TestDevice {

	@Test
	public void testDeviceModel() {

		DIManager.initialize(ScanConfig.newBuilder()
				.addAnnotationConfig(ClassAnnotationConfig.getBuilder()
						.forAnnotation(Entity.class)
						.autoBuild(false)
						.checkDependency(false)
						.build())
				.addPackagesToScan("app.saikat")
				.build());
		DeviceModel model = new DeviceModel();
		model.setName("test");
		model.setDescription("test device description");
		model.setDeviceType(DeviceType.OTHER);
		model.setPassword("hello world");

		try (DeviceModelRepo repo = RepositoryFactory.getRepository(DeviceModelRepo.class)) {
			repo.saveDevice(model);
		}

		DeviceModel model2;
		try (DeviceModelRepo repo = RepositoryFactory.getRepository(DeviceModelRepo.class)) {
			model2 = repo.getDeviceWithID(model.getId());
		}

		assertEquals("device equals", model, model2);

		try (DeviceModelRepo repo = RepositoryFactory.getRepository(DeviceModelRepo.class)) {
			model.setData("data random");
			repo.updateDevice(model);
		}

	}
}