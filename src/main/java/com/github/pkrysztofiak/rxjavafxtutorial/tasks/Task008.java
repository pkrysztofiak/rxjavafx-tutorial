package com.github.pkrysztofiak.rxjavafxtutorial.tasks;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Task008 extends Application {

	private final Button decrementButton = new Button("-");
	private final Button incrementButton = new Button("+");
	private final Label label = new Label();
	private final HBox hBox = new HBox(decrementButton, incrementButton, label);
	private final Scene scene = new Scene(hBox);

	private final Observable<MouseEvent> decrementButtonClickedObservable = JavaFxObservable.eventsOf(decrementButton, MouseEvent.MOUSE_CLICKED);
	private final Observable<MouseEvent> incrementButtonClickedObservable = JavaFxObservable.eventsOf(incrementButton, MouseEvent.MOUSE_CLICKED);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		//your code here
	}
}