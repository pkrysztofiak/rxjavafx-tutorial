package com.github.pkrysztofiak.rxjavafxtutorial.commons;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {

	private final StringProperty nameProperty = new SimpleStringProperty();
	private final Observable<String> nameObservable = JavaFxObservable.valuesOf(nameProperty);

	public Employee(String name) {
		nameProperty.set(name);
	}

	public String getName() {
		return nameProperty.get();
	}

	public void setName(String name) {
		nameProperty.set(name);
	}

	public StringProperty nameProperty() {
		return nameProperty;
	}

	public Observable<String> nameObservable() {
		return nameObservable;
	}
}