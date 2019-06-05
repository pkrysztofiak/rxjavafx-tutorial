package com.github.pkrysztofiak.rxjavafxtutorial.tasks;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Task004 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Label label = new Label();
		Button button = new Button("+");
		HBox hBox = new HBox(label, button);
		stage.setScene(new Scene(hBox));
		stage.show();

		Observable<ActionEvent> buttonActionObservable = JavaFxObservable.actionEventsOf(button);
	}
}