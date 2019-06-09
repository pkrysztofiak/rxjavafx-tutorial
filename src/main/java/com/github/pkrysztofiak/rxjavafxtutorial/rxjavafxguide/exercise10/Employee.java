package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise10;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;

public class Employee {

	private final ReadOnlyStringWrapper nameProperty = new ReadOnlyStringWrapper();
	private final IntegerProperty daysOffProperty = new SimpleIntegerProperty();

	public Employee(String name, int daysOff) {
		nameProperty.set(name);
	}

	public ReadOnlyStringProperty nameProperty() {
		return nameProperty.getReadOnlyProperty();
	}

	public IntegerProperty daysOffProperty() {
		return daysOffProperty;
	}
}
