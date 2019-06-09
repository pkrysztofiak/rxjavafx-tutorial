package com.github.pkrysztofiak.rxjavafxtutorial.solutions;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Solution010 extends Application {

	private final Label xDeltaLabel = new Label();
	private final Label yDeltaLabel = new Label();
	private final HBox hBox = new HBox(new Label("xDelta="), xDeltaLabel, new Label(", yDelta="), yDeltaLabel);
	private final StackPane stackPane = new StackPane(hBox);
	private final Scene scene = new Scene(stackPane, 600, 400);

	private final Observable<MouseEvent> mousePressedObservable = JavaFxObservable.eventsOf(stackPane, MouseEvent.MOUSE_PRESSED);
	private final Observable<MouseEvent> mouseDraggedObservable = JavaFxObservable.eventsOf(stackPane, MouseEvent.MOUSE_DRAGGED);
	private final Observable<MouseEvent> mouseReleasedObservable = JavaFxObservable.eventsOf(stackPane, MouseEvent.MOUSE_RELEASED);

	{
		hBox.setMouseTransparent(true);
		hBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		Observable<Double> pressedXObservable = mousePressedObservable.map(MouseEvent::getSceneX);
		Observable<Double> pressedYObservable = mousePressedObservable.map(MouseEvent::getSceneY);

		Observable<Double> draggedXObservable = mouseDraggedObservable.map(MouseEvent::getSceneX);
		Observable<Double> draggedYObservable = mouseDraggedObservable.map(MouseEvent::getSceneY);

		draggedXObservable.withLatestFrom(pressedXObservable, (draggedX, pressedX) -> draggedX - pressedX).map(String::valueOf).subscribe(xDeltaLabel::setText);
		draggedYObservable.withLatestFrom(pressedYObservable, (draggedY, pressedY) -> draggedY - pressedY).map(String::valueOf).subscribe(yDeltaLabel::setText);

		mouseReleasedObservable.subscribe(event -> {
			xDeltaLabel.setText("");
			yDeltaLabel.setText("");
		});
	}
}