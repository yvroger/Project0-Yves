package com.revature.dto;

import java.util.Objects;

public class AddOrEditClientDTO {

	private String name;
	private String clientType;
	private int age;
	
	public AddOrEditClientDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "AddOrEditClientDTO [name=" + name + ", clientType=" + clientType + ", age=" + age + ", getName()="
				+ getName() + ", getClientType()=" + getClientType() + ", getAge()=" + getAge() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, clientType, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrEditClientDTO other = (AddOrEditClientDTO) obj;
		return age == other.age && Objects.equals(clientType, other.clientType) && Objects.equals(name, other.name);
	}
	
}
