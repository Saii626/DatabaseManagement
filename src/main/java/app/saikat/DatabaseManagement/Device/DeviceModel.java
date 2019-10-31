package app.saikat.DatabaseManagement.Device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import app.saikat.DatabaseManagement.BaseClasses.AbstractBaseEntity;

@Entity
@Table(name = "devices")
public class DeviceModel extends AbstractBaseEntity {

	private static final long serialVersionUID = 3264891224294811479L;
	
	@Column(nullable = false, length = 50, unique = true, updatable = false)
	private String name;

	@Column(length = 1000)
	private String description;

	@Column(unique = true)
	private String token;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private DeviceType deviceType;
	
	@Column(length = 1000)
	private String data;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DeviceType getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
