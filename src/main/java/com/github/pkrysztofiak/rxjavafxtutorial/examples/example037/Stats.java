package com.github.pkrysztofiak.rxjavafxtutorial.examples.example037;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Stats {

	private final IntegerProperty goalsProperty = new SimpleIntegerProperty();

	public Stats() {

	}

	public Stats(Stats stats) {
		goalsProperty.set(stats.goalsProperty.getValue());
	}

	public IntegerProperty goalsProperty() {
		return goalsProperty;
	}
}