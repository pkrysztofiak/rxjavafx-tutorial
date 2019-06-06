package com.github.pkrysztofiak.rxjavafxtutorial.tasks;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Task11 extends Application {

	private final CircleVertex circleVertex = new CircleVertex();
	private final Pane pane = new Pane(circleVertex);
	private final Scene scene = new Scene(pane, 600, 400);

	{
		circleVertex.setTranslateX(100);
		circleVertex.setTranslateY(100);
	}


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();
	}
}

class CircleVertex extends Group {

	private final Circle circle = new Circle(8, Color.BLUEVIOLET);
	private final Observable<MouseEvent> mousePressedObservable = JavaFxObservable.eventsOf(circle, MouseEvent.MOUSE_PRESSED);
	private final Observable<Double> mousePressedXObservable = mousePressedObservable.map(MouseEvent::getSceneX);
	private final Observable<Double> mousePressedYObservable = mousePressedObservable.map(MouseEvent::getSceneY);

	private final Observable<MouseEvent> mouseDraggedObservable = JavaFxObservable.eventsOf(circle, MouseEvent.MOUSE_DRAGGED);
	private final Observable<Double> mouseDraggedXObservable = mouseDraggedObservable.map(MouseEvent::getSceneX);
	private final Observable<Double> mouseDraggedYObservable = mouseDraggedObservable.map(MouseEvent::getSceneY);

	private final Observable<Double> translateXObservable = JavaFxObservable.valuesOf(circle.translateXProperty()).map(Number::doubleValue);
	private final Observable<Double> translateYObservable = JavaFxObservable.valuesOf(circle.translateYProperty()).map(Number::doubleValue);

	{
		getChildren().add(circle);
	}

	public CircleVertex() {
		//your code here
	}
}