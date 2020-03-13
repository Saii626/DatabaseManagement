package app.saikat.DatabaseManagement;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import org.junit.Test;

import app.saikat.DIManagement.Exceptions.BeanNotFoundException;
import app.saikat.DIManagement.Interfaces.DIManager;
import app.saikat.DatabaseManagement.BaseClasses.RepositoryFactory;
import app.saikat.DatabaseManagement.SocketMessage.MessageDirection;
import app.saikat.DatabaseManagement.SocketMessage.MessageModel;
import app.saikat.DatabaseManagement.SocketMessage.MessageRepository;

public class TestMessage {

	@Test
	public void test() throws BeanNotFoundException {

		DIManager manager = DIManager.newInstance();
		manager.scan("app.saikat");

		UUID id1 = UUID.randomUUID();
		MessageModel msg1 = new MessageModel().sessionId(id1)
				.from("test Device")
				.timestamp(LocalDateTime.now())
				.direction(MessageDirection.FROM_SERVER)
				.messageString("Hello world");

		UUID id2 = UUID.randomUUID();
		MessageModel msg2 = new MessageModel().sessionId(id2)
				.from("test Device")
				.timestamp(LocalDateTime.now())
				.direction(MessageDirection.FROM_CLIENT)
				.messageString("Heisd adskj");

		MessageModel msg3 = new MessageModel().sessionId(id1)
				.from("test Device")
				.timestamp(LocalDateTime.now())
				.direction(MessageDirection.TO_CLIENT)
				.messageString("Hi. Identify...");
				
		RepositoryFactory repositoryFactory = manager.getBeanOfType(RepositoryFactory.class).getProvider().get();

		try (MessageRepository repo = repositoryFactory.getRepository(MessageRepository.class)) {
			repo.saveMessage(msg1);
			repo.saveMessage(msg2);
			repo.saveMessage(msg3);
		}

		try (MessageRepository repo = repositoryFactory.getRepository(MessageRepository.class)) {
			MessageModel message = repo.getMessageWithId(msg2.getId());
			assertEquals("msg2 save vs query", msg2, message);

			List<MessageModel> sessionMessages = repo.getAllMessagesWithSessionId(id1);
			assertEquals("Ordered list of session messages", Lists.newArrayList(msg3, msg1), sessionMessages);
		}



	}
}