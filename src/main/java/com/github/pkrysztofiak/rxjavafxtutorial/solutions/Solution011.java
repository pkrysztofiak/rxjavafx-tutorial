package com.github.pkrysztofiak.rxjavafxtutorial.solutions;


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

public class Solution011 extends Application {

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
	private final Observable<Double> mousePressedXObservable = JavaFxObservable.eventsOf(circle, MouseEvent.MOUSE_PRESSED).map(MouseEvent::getSceneX);
	private final Observable<Double> mousePressedYObservable = JavaFxObservable.eventsOf(circle, MouseEvent.MOUSE_PRESSED).map(MouseEvent::getSceneY);

	private final Observable<MouseEvent> mouseDraggedObservable = JavaFxObservable.eventsOf(circle, MouseEvent.MOUSE_DRAGGED);

	{
		getChildren().add(circle);
	}

}