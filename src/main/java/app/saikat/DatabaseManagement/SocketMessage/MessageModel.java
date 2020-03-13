package app.saikat.DatabaseManagement.SocketMessage;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import app.saikat.DatabaseManagement.BaseClasses.AbstractBaseEntity;

@Entity
@Table(name = "messages")
public class MessageModel extends AbstractBaseEntity{

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, updatable = false)
	private UUID sessionId;

	@Column(name = "device_name", nullable = false, updatable = false)
	private String from;

	@Column(nullable = false, updatable = false)
	private LocalDateTime timestamp;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, updatable = false)
	private MessageDirection direction;

	@Column(nullable = false, length = 10000, updatable = false)
	private String messageString;

	public UUID getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public MessageDirection getDirection() {
		return this.direction;
	}

	public void setDirection(MessageDirection direction) {
		this.direction = direction;
	}

	public String getMessageString() {
		return this.messageString;
	}

	public void setMessageString(String messageString) {
		this.messageString = messageString;
	}

	public MessageModel sessionId(UUID sessionId) {
		this.sessionId = sessionId;
		return this;
	}

	public MessageModel from(String from) {
		this.from = from;
		return this;
	}

	public MessageModel timestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public MessageModel direction(MessageDirection direction) {
		this.direction = direction;
		return this;
	}

	public MessageModel messageString(String messageString) {
		this.messageString = messageString;
		return this;
	}

	
}