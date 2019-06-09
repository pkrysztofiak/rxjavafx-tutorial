package com.github.pkrysztofiak.rxjavafxtutorial.examples.example037;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

	private final StringProperty nameProperty = new SimpleStringProperty();
	private final StringProperty surnameProperty = new SimpleStringProperty();
	private final ObjectProperty<Position> positionProperty = new SimpleObjectProperty<>();

	private Stats stats = new Stats();

	public Player(String name, String surname, Position position) {
		nameProperty.set(name);
		surnameProperty.set(surname);
		positionProperty.set(position);
	}

	public Player(Player player) {
		nameProperty.set(player.nameProperty.get());
		surnameProperty.set(player.surnameProperty.get());
		positionProperty.set(player.positionProperty.get());
		stats = new Stats(player.getStats());
	}

	public StringProperty nameProperty() {
		return nameProperty;
	}

	public StringProperty surnameProperty() {
		return surnameProperty;
	}

	public ObjectProperty<Position> positionProperty() {
		return positionProperty;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public Stats getStats() {
		return stats;
	}
}