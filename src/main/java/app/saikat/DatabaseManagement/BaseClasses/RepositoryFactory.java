package app.saikat.DatabaseManagement.BaseClasses;

import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Provider;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.saikat.DIManagement.Interfaces.DIManager;

@Singleton
public class RepositoryFactory {

	private final Map<Class<?>, Provider<?>> providerMap;
	private Logger logger = LogManager.getLogger(RepositoryFactory.class);

	public RepositoryFactory(DIManager manager) {
		logger.debug("superclassbeans: {}", manager.getBeansOfSuperClass(AbstractBaseRepository.class));
		providerMap = manager.getBeansOfSuperClass(AbstractBaseRepository.class).parallelStream()
				.collect(Collectors.toMap(b -> b.getProviderType(), b -> b.getProvider()));
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractBaseRepository> T getRepository(Class<T> cls) {

		Provider<T> repoProvider = (Provider<T>) providerMap.get(cls);
		if (repoProvider == null) {
			throw new RuntimeException("No such repository found");
		}

		return repoProvider.get();
	}

}