package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {

	private final StringProperty nameProperty = new SimpleStringProperty();
	private final StringProperty surnameProperty = new SimpleStringProperty();

	public Employee(String name, String surname) {
		nameProperty.set(name);
		surnameProperty.set(surname);
	}

	public StringProperty nameProperty() {
		return nameProperty;
	}

	public StringProperty surnameProperty() {
		return surnameProperty;
	}

	public String getSurename() {
		return surnameProperty.get();
	}
}