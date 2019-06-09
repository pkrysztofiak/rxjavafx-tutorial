package com.github.pkrysztofiak.rxjavafxtutorial.tasks.task011;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {

	private final StringProperty nameProperty = new SimpleStringProperty();
	private final StringProperty surnameProperty = new SimpleStringProperty();
	private final StringProperty descriptionProperty = new SimpleStringProperty();

	public Employee(String name, String surname, String description) {
		nameProperty.set(name);
		surnameProperty.set(surname);
		descriptionProperty.set(description);
	}

	public String getName() {
		return nameProperty.get();
	}

	public String getSurname() {
		return surnameProperty.get();
	}

	public String getDescription() {
		return descriptionProperty.get();
	}

	public StringProperty descriptionProperty() {
		return descriptionProperty;
	}

	public void setDescription(String description) {
		descriptionProperty.set(description);
	}

	@Override
	public String toString() {
		return "Employee[name=" + nameProperty.get() + ", surname=" + surnameProperty.get() + ", description=" + descriptionProperty.get() + "]";
	}
}
