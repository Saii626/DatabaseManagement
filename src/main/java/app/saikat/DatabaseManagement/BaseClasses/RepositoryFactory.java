package app.saikat.DatabaseManagement.BaseClasses;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.saikat.DIManagement.Interfaces.DIBean;
import app.saikat.DIManagement.Interfaces.DIManager;

@Singleton
public class RepositoryFactory {

	private final Map<Class<?>, DIBean<?>> repositoryMap;
	private Logger logger = LogManager.getLogger(RepositoryFactory.class);

	public RepositoryFactory(DIManager manager) {
		Set<DIBean<?>> repositories = manager.getBeansWithType(AbstractBaseRepository.class);
		logger.info("Found {} repositories", repositories);

		Map<Class<?>, DIBean<?>> repoMap = new HashMap<>();
		repositories.stream().forEach(repo -> repoMap.put(repo.getProviderType().getRawType(), repo));

		repositoryMap = Collections.unmodifiableMap(repoMap);
	}



	@SuppressWarnings("unchecked")
	public <T extends AbstractBaseRepository> T getRepository(Class<T> cls) {

		DIBean<T> repoBean = (DIBean<T>) repositoryMap.get(cls);
		if (repoBean == null) {
			throw new RuntimeException("No such repository found");
		}

		return repoBean.getProvider().get();
	}

}