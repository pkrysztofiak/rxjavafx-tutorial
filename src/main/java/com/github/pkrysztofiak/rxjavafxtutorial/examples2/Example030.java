package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Example030 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		User john = new User("John");
		User lucy = new User("Lucy");

		ObservableList<User> users = FXCollections.observableArrayList(user -> new ObservableValue[] {user.nameProperty()});
		users.addAll(john, lucy);

		JavaFxObservable.updatesOf(users).subscribe(System.out::println);

		john.setName("Johnny");
		lucy.setName("Lucinda");
	}
}

class User {

	private final StringProperty nameProperty = new SimpleStringProperty();

	public User(String name) {
		nameProperty.set(name);
	}

	public void setName(String name) {
		nameProperty.set(name);
	}

	public StringProperty nameProperty() {
		return nameProperty;
	}

	@Override
	public String toString() {
		return "User[name=" + nameProperty.get() + "]";
	}
}