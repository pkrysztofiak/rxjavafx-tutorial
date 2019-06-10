package com.github.pkrysztofiak.rxjavafxtutorial.tasks;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Task016 extends Application {

	private final Pane stackPane = new Pane();
	private final Scene scene = new Scene(stackPane);

	private final Observable<MouseEvent> mouseMovedObservable = JavaFxObservable.eventsOf(stackPane, MouseEvent.MOUSE_MOVED);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		//your code here
	}

	public void pinPoint(double x, double y) {
		Circle circle = new Circle(8, Color.BLUEVIOLET);
		circle.setTranslateX(x);
		circle.setTranslateY(y);
		stackPane.getChildren().add(circle);
	}
}