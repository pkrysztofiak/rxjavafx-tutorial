package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.net.URL;
import java.util.Scanner;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Example058 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Button button = new Button("Send request");
		TextArea textArea = new TextArea();
		textArea.setWrapText(true);
		VBox vBox = new VBox(textArea, button);
		Scene scene = new Scene(vBox);
		stage.setScene(scene);
		stage.show();

		JavaFxObservable.actionEventsOf(button)
		.observeOn(Schedulers.io())
		.map(actionEvent -> request("https://api.github.com/users/pkrysztofiak"))
		.observeOn(JavaFxScheduler.platform())
		.subscribe(textArea::setText);
	}

	public String request(String path) {
		try (Scanner scanner = new Scanner(new URL(path).openStream(), "UTF-8");) {
			return scanner.useDelimiter("\\A").next();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}