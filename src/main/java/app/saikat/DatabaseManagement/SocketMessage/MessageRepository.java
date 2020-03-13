package app.saikat.DatabaseManagement.SocketMessage;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import app.saikat.DatabaseManagement.BaseClasses.AbstractBaseRepository;

public class MessageRepository extends AbstractBaseRepository {

	protected MessageRepository(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

	public void saveMessage(MessageModel messageModel) {
		entityManager.persist(messageModel);
	}

	public MessageModel getMessageWithId(UUID id) {

		TypedQuery<MessageModel> getById = entityManager
				.createQuery("FROM MessageModel M WHERE M.id = :id", MessageModel.class);
		return getById.setParameter("id", id).getSingleResult();
	}

	public List<MessageModel> getAllMessagesWithSessionId(UUID sessionId) {

		TypedQuery<MessageModel> getBySessionId = entityManager
				.createQuery("FROM MessageModel M WHERE M.sessionId = :sessionId ORDER BY M.timestamp desc", MessageModel.class);
				return getBySessionId.setParameter("sessionId", sessionId).getResultList();
	}

}