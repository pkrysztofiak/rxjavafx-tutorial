package com.github.pkrysztofiak.rxjavafxtutorial.real;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

	private final StringProperty nameProperty = new SimpleStringProperty();
	private final StringProperty surnameProperty = new SimpleStringProperty();
	private final IntegerProperty goalsProperty = new SimpleIntegerProperty();

	public Player(String name, String surname, int goals) {
		nameProperty.set(name);
		surnameProperty.set(surname);
		goalsProperty.set(goals);
	}

	public StringProperty nameProperty() {
		return nameProperty;
	}

	public StringProperty surnameProperty() {
		return surnameProperty;
	}

	public IntegerProperty goalsProperty() {
		return goalsProperty;
	}
}