package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {

	private final StringProperty nameProperty = new SimpleStringProperty();
	private final StringProperty surnameProperty = new SimpleStringProperty();
	private final ObjectProperty<Position> positionProperty = new SimpleObjectProperty<>();

	public Employee(String name, String surname, Position position) {
		nameProperty.set(name);
		surnameProperty.set(surname);
		positionProperty.set(position);
	}

	//TODO sprawdzić czy mogę bindować read-only, jeżel bym taki zwrócił
	public StringProperty nameProperty() {
		return nameProperty;
	}

	public StringProperty surnameProperty() {
		return surnameProperty;
	}

	public ObjectProperty<Position> positionProperty() {
		return positionProperty;
	}
}